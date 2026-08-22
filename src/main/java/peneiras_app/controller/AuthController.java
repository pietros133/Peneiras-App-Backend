package peneiras_app.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peneiras_app.dto.AuthDTO;
import peneiras_app.dto.AuthResponseDTO;
import peneiras_app.service.AuthService;

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
}