package project.DTOS.Attendance;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FinalizeAttendanceRequest(
        @NotNull(message = "Ticket ID is mandatory.")
        Long ticketId,

        @NotNull(message = "User ID is mandatory.")
        Long userId,

        @NotBlank(message = "Description is mandatory.")
        String description
) {
}
