package peneiras_app.dto;

public class AuthResponseDTO {

    private String message;
    private String token;

    public AuthResponseDTO(String message, String token) {
        this.message = message;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }
}