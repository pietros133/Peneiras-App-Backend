package peneiras_app.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import peneiras_app.dto.ClubeResponseDTO;
import peneiras_app.dto.ClubeUpdateDTO;
import peneiras_app.service.EditClubeService;

import java.util.UUID;

@RestController
@RequestMapping("/clubes")
public class ClubeController {

    private final EditClubeService editClubeService;

    public ClubeController(EditClubeService editClubeService) {
        this.editClubeService = editClubeService;
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
}