package project.DTOS.Attendance;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public record ReturnToAttendanceRequest(
    @NotNull(message = "Ticket ID is mandatory.")
    Long ticketId,

    @NotNull(message = "requester ID is mandatory.")
    Long requesterId,

    @NotBlank(message = "Description is mandatory.")
    String description
) {
}
