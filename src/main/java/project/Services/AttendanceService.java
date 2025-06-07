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
}
