package peneiras_app.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import peneiras_app.dto.PlayerResponseDTO;
import peneiras_app.dto.PlayerUpdateDTO;
import peneiras_app.service.EditPlayerService;

import java.util.UUID;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final EditPlayerService editPlayerService;

    public PlayerController(EditPlayerService editPlayerService) {
        this.editPlayerService = editPlayerService;
    }

    @PutMapping("/me")
    public ResponseEntity<PlayerResponseDTO> editPlayer(
            Authentication authentication,
            @Valid @RequestBody PlayerUpdateDTO dto
    ) {

        UUID userId = (UUID) authentication.getPrincipal();

        PlayerResponseDTO response =
                editPlayerService.execute(userId, dto);

        return ResponseEntity.ok(response);
    }
}