package project.DTOS.Attendance;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public record ForwardToRequesterRequest(
    @NotNull(message = "Ticket ID is mandatory.")
    Long ticketId,

    @NotNull(message = "Attendant ID is mandatory.")
    Long attendantId,

    @NotBlank(message = "Description is mandatory.")
    String description
) {
}
