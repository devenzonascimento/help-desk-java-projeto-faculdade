package project.DTOS.User;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record SaveTeamsRequest(
        @NotNull(message = "UserId is Mandatory")
        Long userId,

        @NotNull(message = "teamsIds is mandatory")
        List<@NotNull(message = "Each teamId must be a number") Long> teamsIds
) {
}
