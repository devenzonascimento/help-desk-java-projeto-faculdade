package project.DTOS.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChangePasswordRequest(
        @NotBlank(message = "Email is mandatory")
        @Email(message = "Email must be valid")
        String email,

        @NotBlank(message = "Current password is mandatory")
        @Size(min = 6, message = "Password must be at least 6 characters long")
        String currentPassword,

        @NotBlank(message = "New password is mandatory")
        @Size(min = 6, message = "Password must be at least 6 characters long")
        String newPassword
) {
}
