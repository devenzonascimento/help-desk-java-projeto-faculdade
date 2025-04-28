package project.DTOS.User;

import jakarta.validation.constraints.*;

public record CreateUserRequest(
        Long id,

        @NotBlank(message = "Name is mandatory")
        String name,

        @NotBlank(message = "Email is mandatory")
        @Email(message = "Email must be valid")
        String email,

        @NotBlank(message = "Password is mandatory")
        @Size(min = 6, message = "Password must be at least 6 characters long")
        String password,

        String position,

        String telephone,

        @NotNull(message = "ProfileId is Mandatory")
        @Positive(message = "ProfileId must be greater than zero")
        Long profileId
) {

}
