package project.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.DTOS.Ticket.OpenTicketRequest;
import project.Entities.Team;
import project.Entities.Ticket;
import project.Entities.User;
import project.Enums.AttendanceStatus;
import project.Repositories.TeamRepository;
import project.Repositories.TicketRepository;
import project.Repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    public Ticket openTicket(OpenTicketRequest request) {
        User requester = userRepository.findById(request.userRequesterId()).orElse((null));

        if (requester == null) {
            throw new RuntimeException("User not found.");
        }

        Team team = teamRepository.findById(request.teamId()).orElse(null);

        if (team == null) {
            throw new RuntimeException("Team not found.");
        }

        boolean isUserBelongToTeam = requester.getTeams().stream().anyMatch(
            t -> Objects.equals(t.getId(), team.getId())
        );

        if (!isUserBelongToTeam) {
            throw new RuntimeException("User does not belong to team.");
        }

        Ticket ticketToOpen = new Ticket();

        ticketToOpen.setRequester(requester);
        ticketToOpen.setTeam(team);
        ticketToOpen.setTitle(request.title());
        ticketToOpen.setDescription(request.description());
        ticketToOpen.setStartDate(LocalDateTime.now());
        ticketToOpen.setLastStatus(AttendanceStatus.AWAITING);

        return ticketRepository.save(ticketToOpen);
    }

    public List<Ticket> findAllOpenTicketsByTeam(Long teamId) {
        List<Ticket> openTickets = ticketRepository.findByTeamIdAndEndDateIsNull(teamId);

        return openTickets;
    }

    public List<Ticket> findAllOpenAndNotCompletedTickets() {
        List<Ticket> openTickets = ticketRepository.findByEndDateIsNull();

        return openTickets
            .stream()
            .filter(ticket -> ticket.getLastStatus() != AttendanceStatus.COMPLETED)
            .toList();
    }
}
