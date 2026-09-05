package peneiras_app.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import peneiras_app.dto.PlayerResponseDTO;
import peneiras_app.dto.PlayerUpdateDTO;
import peneiras_app.service.EditPlayerService;
import peneiras_app.service.UserPhotoService;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final EditPlayerService editPlayerService;
    private final UserPhotoService userPhotoService;

    public PlayerController(
            EditPlayerService editPlayerService,
            UserPhotoService userPhotoService
    ) {
        this.editPlayerService = editPlayerService;
        this.userPhotoService = userPhotoService;
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

    @PostMapping("/me/photo")
    public ResponseEntity<?> uploadPhoto(
            @RequestParam("photo") MultipartFile photo,
            Authentication authentication
    ) throws IOException {

        UUID playerId = (UUID) authentication.getPrincipal();

        String imageUrl =
                userPhotoService.uploadPlayerPhoto(playerId, photo);

        return ResponseEntity.ok(imageUrl);
    }
}