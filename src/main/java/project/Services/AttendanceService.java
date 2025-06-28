package project.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.DTOS.Attendance.*;
import project.Entities.*;
import project.Enums.AttendanceStatus;
import project.Repositories.*;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttendanceService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserTeamRepository userTeamRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Transactional
    public Attendance startAttendance(StartAttendanceRequest request) {

        Ticket ticket = ticketRepository.findById(request.ticketId()).orElse(null);
        // O ticket existe?
        if (ticket == null) {
            throw new RuntimeException("Failure to start attendance, the informed ticket does not exist.");
        }

        // O ticket está em espera para ser iniciado?
        if (!ticket.getLastStatus().equals(AttendanceStatus.AWAITING)) {
            throw new RuntimeException("Failure to start attendance, the informed ticket is not in awaiting.");
        }

        User attendant = userRepository.findById(request.attendantId()).orElse(null);
        // O atendente existe?
        if (attendant == null) {
            throw new RuntimeException("Failure to start attendance, attendant not found.");
        }

        // O usuário que vai iniciar o atendimento é o proprio solicitante?
        if (ticket.getRequester().equals(attendant)) {
            throw new RuntimeException("Failure to start attendance, requester cannot start your own attendance.");
        }

        UserTeam foundAttendantTeam = findAttendantTeam(ticket, attendant.getId());

        ticket.setLastStatus(AttendanceStatus.STARTED);
        ticketRepository.save(ticket);

        Attendance attendance = new Attendance();

        attendance.setTicket(ticket);
        attendance.setDate(LocalDateTime.now());
        attendance.setStatus(AttendanceStatus.STARTED);
        attendance.setDescription(request.description());
        attendance.setUserTeam(foundAttendantTeam);

        return attendanceRepository.save(attendance);
    }

    @Transactional
    public void transferToAnotherTeam(TransferToAnotherTeamRequest request) {
        Ticket ticket = ticketRepository.findById(request.ticketId()).orElse(null);
        // O ticket existe?
        if (ticket == null) {
            throw new RuntimeException("Failure to transfer ticket, the informed ticket does not exist.");
        }

        User attendant = userRepository.findById(request.attendantId()).orElse(null);
        // O atendente existe?
        if (attendant == null) {
            throw new RuntimeException("Failure to transfer ticket, attendant not found.");
        }

        UserTeam foundUserTeam = findAttendantTeam(ticket, attendant.getId());

        Team team = teamRepository.findById(request.teamId()).orElse(null);
        // A equipe existe?
        if (team == null) {
            throw new RuntimeException("Failure to transfer ticket, team not found.");
        }

        String auditMessage = "Transfering ticket"
                + String.format(" FROM TEAM [ID: %d] [NAME: %s]", ticket.getTeam().getId(), ticket.getTeam().getName())
                + String.format(" TO TEAM [ID: %d] [NAME: %s]", team.getId(), team.getName())
                + String.format(" BY ATTENDANT [ID: %d] [NAME: %s]", attendant.getId(), attendant.getName());

        ticket.setTeam(team);
        ticket.setInternalReport(auditMessage);
        ticketRepository.save(ticket);

        Attendance attendance = new Attendance();
        attendance.setTicket(ticket);
        attendance.setDate(LocalDateTime.now());
        attendance.setStatus(AttendanceStatus.FORWARDED);
        attendance.setDescription(auditMessage);
        attendance.setUserTeam(foundUserTeam);
        attendanceRepository.save(attendance);
    }

    @Transactional
    public void forwardToRequester(ForwardToRequesterRequest request) {
        Ticket ticket = ticketRepository.findById(request.ticketId()).orElse(null);
        // O ticket existe?
        if (ticket == null) {
            throw new RuntimeException("Failure to forward ticket, the informed ticket does not exist.");
        }

        // O ticket não foi iniciado ou devolvido pelo solicitante?
        if (!ticket.lastStatusContains(List.of(AttendanceStatus.STARTED, AttendanceStatus.RETURNED))) {
            throw new RuntimeException("Failure to forward ticket, the informed ticket is not available to forward.");
        }

        User attendant = userRepository.findById(request.attendantId()).orElse(null);
        // O atendente existe?
        if (attendant == null) {
            throw new RuntimeException("Failure to forward ticket, attendant not found.");
        }

        // O usuário que vai encaminhar o atendimento é o proprio solicitante?
        if (ticket.getRequester().equals(attendant)) {
            throw new RuntimeException("Failure to forward ticket, requester cannot forward your own attendance.");
        }

        UserTeam foundUserTeam = findAttendantTeam(ticket, attendant.getId());

        ticket.setLastStatus(AttendanceStatus.FORWARDED);
        ticketRepository.save(ticket);

        Attendance attendance = new Attendance();
        attendance.setTicket(ticket);
        attendance.setDate(LocalDateTime.now());
        attendance.setStatus(AttendanceStatus.FORWARDED);
        attendance.setDescription(request.description());
        attendance.setUserTeam(foundUserTeam);
        attendanceRepository.save(attendance);
    }

    @Transactional
    public void returnToAttendance(ReturnToAttendanceRequest request) {
        Ticket ticket = ticketRepository.findById(request.ticketId()).orElse(null);
        // O ticket existe?
        if (ticket == null) {
            throw new RuntimeException("Failure to return ticket, the informed ticket does not exist.");
        }

        // O ticket ainda não foi devolvido ao solicitante?
        if (!ticket.lastStatusContains(List.of(AttendanceStatus.FORWARDED))) {
            throw new RuntimeException("Failure to return ticket, the informed ticket is not returned to requester yet.");
        }

        User requester = userRepository.findById(request.requesterId()).orElse(null);
        // O solicitante existe?
        if (requester == null) {
            throw new RuntimeException("Failure to return ticket, requester not found.");
        }

        // O usuário que vai devolver para o atendimento não é o proprio solicitante?
        if (!ticket.getRequester().equals(requester)) {
            throw new RuntimeException("Failure to return ticket, only the requester can return to the attendance.");
        }

        ticket.setLastStatus(AttendanceStatus.RETURNED);
        ticketRepository.save(ticket);

        Attendance attendance = new Attendance();
        attendance.setTicket(ticket);
        attendance.setDate(LocalDateTime.now());
        attendance.setStatus(AttendanceStatus.RETURNED);
        attendance.setDescription(request.description());
        attendance.setUserTeam(null);
        attendanceRepository.save(attendance);
    }

    @Transactional
    public void finalizeToAttendance(FinalizeAttendanceRequest request) {
        Ticket ticket = ticketRepository.findById(request.ticketId()).orElse(null);
        // O ticket existe?
        if (ticket == null) {
            throw new RuntimeException("Failure to finalize ticket, the informed ticket does not exist.");
        }

        // O ticket ainda não foi devolvido ao solicitante e nem retornado para o atendente?
        if (!ticket.lastStatusContains(List.of(AttendanceStatus.FORWARDED, AttendanceStatus.RETURNED))) {
            throw new RuntimeException("Failure to finalize ticket, the informed ticket is not forwarded to requester or not returned to attendant yet.");
        }

        // Se o ticket foi encaminhado, ele deveria estar com o solicitante, caso contrario dispara erro
        if (ticket.lastStatusContains(List.of(AttendanceStatus.FORWARDED))) {
            User requester = userRepository.findById(request.userId()).orElse(null);

            // O solicitante existe?
            if (requester == null) {
                throw new RuntimeException("Failure to finalize ticket, requester not found.");
            }

            // O usuário que vai finalizar o atendimento não é o proprio solicitante?
            if (!ticket.getRequester().equals(requester)) {
                throw new RuntimeException("Failure to finalize ticket, only the requester can finalize the ticket.");
            }
        }

        UserTeam userTeamWhoFinalizeTicket = null;

        // Se o ticket foi retornado, ele deveria estar com o atendente, caso contrario dispara erro
        if (ticket.lastStatusContains(List.of(AttendanceStatus.RETURNED))) {
            User attendant = userRepository.findById(request.userId()).orElse(null);

            // O atendente existe?
            if (attendant == null) {
                throw new RuntimeException("Failure to finalize ticket, attendant not found.");
            }

            // O usuário que vai finalizar o atendimento é o proprio solicitante?
            if (ticket.getRequester().equals(attendant)) {
                throw new RuntimeException("Failure to finalize ticket, requester cannot finalize the ticket.");
            }

            userTeamWhoFinalizeTicket = findAttendantTeam(ticket, attendant.getId());
        }


        LocalDateTime now = LocalDateTime.now();
        ticket.setEndDate(now);
        ticket.setLastStatus(AttendanceStatus.COMPLETED);
        ticketRepository.save(ticket);

        Attendance attendance = new Attendance();
        attendance.setTicket(ticket);
        attendance.setDate(now);
        attendance.setDescription(request.description());
        attendance.setStatus(AttendanceStatus.COMPLETED);
        attendance.setUserTeam(userTeamWhoFinalizeTicket);
        attendanceRepository.save(attendance);
    }

    private UserTeam findAttendantTeam(Ticket ticket, Long attendantId) {
        List<UserTeam> usersTeams = userTeamRepository.findByUserId(attendantId);

        UserTeam foundUserTeam = usersTeams.stream()
                .filter(ut -> ut.getTeam().equals(ticket.getTeam()) && ut.getActive())
                .findFirst()
                .orElse(null);

        // O atendente participa do time informado?
        if (foundUserTeam == null) {
            throw new RuntimeException("Failure, the attendant does not belong to team.");
        }

        return foundUserTeam;
    }
}
