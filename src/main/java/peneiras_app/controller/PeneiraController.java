package peneiras_app.controller;

import java.util.List;
import java.util.UUID;
import jakarta.validation.Valid;

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
import peneiras_app.service.GetPeneiraService;
import peneiras_app.service.GetPeneiraByIdService;

@RestController
@RequestMapping("/peneiras")
public class PeneiraController {

    private final CreatePeneiraService createPeneiraService;
    private final GetPeneiraService getPeneirasService;
    private final EditPeneiraService editPeneiraService;
    private final GetPeneiraByIdService getPeneiraByIdService;

    public PeneiraController(
            CreatePeneiraService createPeneiraService,
            GetPeneiraService getPeneirasService,
            EditPeneiraService editPeneiraService,
            GetPeneiraByIdService getPeneiraByIdService
    ) {
        this.createPeneiraService = createPeneiraService;
        this.getPeneirasService = getPeneirasService;
        this.editPeneiraService = editPeneiraService;
        this.getPeneiraByIdService = getPeneiraByIdService;
    }

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
    public ResponseEntity<PeneiraDTO> getPeneiraDetails(
            @PathVariable UUID id
    ) {

        PeneiraDTO response
                = getPeneiraByIdService.execute(id);

        return ResponseEntity.ok(response);
    }
}
