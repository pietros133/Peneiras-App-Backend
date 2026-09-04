package peneiras_app.dto;

public record ResetPasswordDTO(
        String email,
        String code,
        String newPassword
) {
}