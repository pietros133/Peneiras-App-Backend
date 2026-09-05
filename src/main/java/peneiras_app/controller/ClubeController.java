package peneiras_app.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import peneiras_app.dto.ClubeResponseDTO;
import peneiras_app.dto.ClubeUpdateDTO;
import peneiras_app.service.EditClubeService;
import peneiras_app.service.UserPhotoService;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/clubes")
public class ClubeController {

    private final EditClubeService editClubeService;
    private final UserPhotoService userPhotoService;

    public ClubeController(
            EditClubeService editClubeService,
            UserPhotoService userPhotoService
    ) {
        this.editClubeService = editClubeService;
        this.userPhotoService = userPhotoService;
    }

    @PutMapping("/me")
    public ResponseEntity<ClubeResponseDTO> editClube(
            Authentication authentication,
            @Valid @RequestBody ClubeUpdateDTO dto
    ) {

        UUID userId = (UUID) authentication.getPrincipal();

        ClubeResponseDTO response =
                editClubeService.execute(userId, dto);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/me/photo")
    public ResponseEntity<?> uploadPhoto(
            @RequestParam("photo") MultipartFile photo,
            Authentication authentication
    ) throws IOException {

        UUID clubeId = (UUID) authentication.getPrincipal();

        String imageUrl =
                userPhotoService.uploadClubePhoto(clubeId, photo);

        return ResponseEntity.ok(imageUrl);
    }
}