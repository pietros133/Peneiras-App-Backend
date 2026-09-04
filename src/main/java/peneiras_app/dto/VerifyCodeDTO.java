package peneiras_app.dto;

public record VerifyCodeDTO(
        String email,
        String code
) {
}