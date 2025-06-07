package project.DTOS.Attendance;

import jakarta.validation.constraints.NotNull;

public record TransferToAnotherTeamRequest(
    @NotNull(message = "Ticket ID is mandatory.")
    Long ticketId,

    @NotNull(message = "Attendant ID is mandatory.")
    Long attendantId,

    @NotNull(message = "Team ID is mandatory.")
    Long teamId
) {
}
