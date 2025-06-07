package project.DTOS.Attendance;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StartAttendanceRequest(
    @NotNull(message = "Ticket ID is mandatory.")
    Long ticketId,

    @NotNull(message = "Attendant ID is mandatory.")
    Long attendantId,

    @NotBlank(message = "Description is mandatory.")
    String description
) {
}
