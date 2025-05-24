package project.DTOS.Ticket;

import project.Entities.Ticket;
import project.Enums.AttendanceStatus;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record TicketDTO(
        Long ticketId,
        Long requesterId,
        String requesterName,
        Long teamId,
        String teamName,
        String title,
        String description,
        String internalReport,
        AttendanceStatus lastStatus,
        LocalDateTime startDate,
        LocalDateTime endDate
) implements Serializable {

    @Serial
    private static final long serialVersionUID = -4297989842874219L;

    public static TicketDTO fromTicket(Ticket ticket) {
        if (ticket == null) {
            return null;
        }

        return new TicketDTO(
                ticket.getId(),
                ticket.getRequester().getId(),
                ticket.getRequester().getName(),
                ticket.getTeam().getId(),
                ticket.getTeam().getName(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getInternalReport(),
                ticket.getLastStatus(),
                ticket.getStartDate(),
                ticket.getEndDate()
        );
    }

    public static List<TicketDTO> fromTickets(List<Ticket> tickets) {
        List<TicketDTO> ticketsDTO = new ArrayList<>();

        if (tickets != null && !tickets.isEmpty()) {
            ticketsDTO.addAll(tickets.stream().map(TicketDTO::fromTicket).toList());
        }

        return ticketsDTO;
    }
}
