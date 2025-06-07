package project.DTOS.Attendance;

import jakarta.validation.constraints.NotNull;

public record FinalizeAttendanceRequest(
        @NotNull(message = "Ticket ID is mandatory.")
        Long ticketId,

        @NotNull(message = "requester ID is mandatory.")
        Long requesterId
) {
}
