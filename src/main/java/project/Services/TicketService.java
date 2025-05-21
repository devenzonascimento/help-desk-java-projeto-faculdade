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
            return null; // TODO: Retornar erro de usuario não encontrado
        }

        Team team = teamRepository.findById(request.teamId()).orElse(null);

        if (team == null) {
            return null; // TODO: Retornar erro de equipe não encontrada
        }

        boolean isUserBelongToTeam = requester.getTeams().stream().anyMatch(
            t -> Objects.equals(t.getId(), team.getId())
        );

        if (!isUserBelongToTeam) {
            return null; // TODO: Retornar erro de que o usuario não pertence a equipe escolhida
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
}
