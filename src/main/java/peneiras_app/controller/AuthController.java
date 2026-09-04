package peneiras_app.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peneiras_app.dto.AuthDTO;
import peneiras_app.dto.AuthResponseDTO;
import peneiras_app.dto.ForgotPasswordDTO;
import peneiras_app.dto.ResetPasswordDTO;
import peneiras_app.dto.VerifyCodeDTO;
import peneiras_app.service.AuthService;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(
            @Valid @RequestBody AuthDTO dto
    ) {

        return ResponseEntity.ok(
                authService.login(dto)
        );
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(
            @RequestBody ForgotPasswordDTO dto
    ) {

        authService.forgotPassword(
                dto.email()
        );

        return ResponseEntity.ok(
                Map.of(
                        "message",
                        "Código enviado para o email"
                )
        );
    }


    @PostMapping("/verify-code")
    public ResponseEntity<?> verifyCode(
            @RequestBody VerifyCodeDTO dto
    ) {

        authService.verifyResetCode(
                dto.email(),
                dto.code()
        );

        return ResponseEntity.ok(
                Map.of(
                        "message",
                        "Código válido"
                )
        );
    }


    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(
            @RequestBody ResetPasswordDTO dto
    ) {

        authService.resetPassword(
                dto.email(),
                dto.code(),
                dto.newPassword()
        );

        return ResponseEntity.ok(
                Map.of(
                        "message",
                        "Senha alterada com sucesso"
                )
        );
    }
}