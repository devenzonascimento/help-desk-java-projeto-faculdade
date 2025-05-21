package project.DTOS.Ticket;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OpenTicketRequest(
    @NotNull(message = "User Requester ID is mandatory")
    Long userRequesterId,

    @NotNull(message = "Team ID is mandatory")
    Long teamId,

    @NotBlank(message = "Title is mandatory")
    String title,

    @NotBlank(message = "Description is mandatory")
    String description
) {
}
