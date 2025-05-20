package project.DTOS.User;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record SaveTeamsRequest(
        @NotNull(message = "UserId is Mandatory")
        @Positive(message = "UserId must be greater than zero")
        Long userId,

        List<Long> teamsIds
) {
}
