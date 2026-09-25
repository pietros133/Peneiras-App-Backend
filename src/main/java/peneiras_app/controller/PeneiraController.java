package peneiras_app.controller;

import java.util.List;
import java.util.UUID;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import peneiras_app.dto.PeneiraResponseDTO;
import peneiras_app.dto.PeneiraDTO;
import peneiras_app.dto.GetPeneirasDTO;
import peneiras_app.dto.MessageResponseDTO;

import peneiras_app.entity.Peneira;
import peneiras_app.service.CreatePeneiraService;
import peneiras_app.service.EditPeneiraService;
import peneiras_app.service.GetPeneiraByClubeIdService;
import peneiras_app.service.GetPeneiraService;
import peneiras_app.service.GetPeneiraByPeneiraIdService;

@RestController
@RequestMapping("/peneiras")
public class PeneiraController {

    @Autowired
    private CreatePeneiraService createPeneiraService;

    @Autowired
    private GetPeneiraService getPeneirasService;

    @Autowired
    private EditPeneiraService editPeneiraService;

    @Autowired
    private GetPeneiraByPeneiraIdService getPeneiraByPeneiraIdService;

    @Autowired
    private GetPeneiraByClubeIdService getPeneiraByClubeIdService;

    @PostMapping
    public ResponseEntity<PeneiraResponseDTO> create(
            @Valid @RequestBody PeneiraDTO dto
    ) {

        Peneira peneira = createPeneiraService.create(dto);

        PeneiraResponseDTO response = new PeneiraResponseDTO(
                peneira.getId(),
                peneira.getCategory(),
                peneira.getModality(),
                peneira.getDate(),
                peneira.getHour(),
                peneira.getUniforms(),
                peneira.getDocuments(),
                peneira.getAbout()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<GetPeneirasDTO>> getAll() {

        return ResponseEntity.ok(
                getPeneirasService.getAll()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponseDTO> edit(
            @PathVariable UUID id,
            @Valid @RequestBody PeneiraDTO dto,
            Authentication authentication
    ) {

        UUID clubeId = (UUID) authentication.getPrincipal();

        editPeneiraService.execute(id, clubeId, dto);

        return ResponseEntity.ok(new MessageResponseDTO("Peneira atualizada com sucesso!"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeneiraResponseDTO> getPeneiraDetails(
            @PathVariable UUID id
    ) {

        PeneiraResponseDTO response
                = getPeneiraByPeneiraIdService.execute(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/clube")
    public ResponseEntity<List<GetPeneirasDTO>> getPeneirasByClubeId(
            Authentication authentication
    ) {

        boolean isClube = authentication.getAuthorities()
                .stream()
                .anyMatch(
                        authority -> authority.getAuthority().equals("ROLE_CLUBE")
                );

        if (!isClube) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        UUID clubeId = (UUID) authentication.getPrincipal();

        List<GetPeneirasDTO> response
                = getPeneiraByClubeIdService.execute(clubeId);

        return ResponseEntity.ok(response);
    }
}
