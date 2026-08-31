package peneiras_app.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import peneiras_app.dto.PeneiraCreateDTO;
import peneiras_app.dto.PeneiraResponseDTO;
import peneiras_app.dto.PeneiraUpdateDTO;
import peneiras_app.dto.PeneiraUpdateResponseDTO;
import peneiras_app.entity.Peneira;
import peneiras_app.service.CreatePeneiraService;
import peneiras_app.service.EditPeneiraService;
import peneiras_app.service.GetPeneiraService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/peneiras")
public class PeneiraController {

    private final CreatePeneiraService createPeneiraService;
    private final GetPeneiraService getPeneirasService;
    private final EditPeneiraService editPeneiraService;

    public PeneiraController(
            CreatePeneiraService createPeneiraService,
            GetPeneiraService getPeneirasService,
            EditPeneiraService editPeneiraService
    ) {
        this.createPeneiraService = createPeneiraService;
        this.getPeneirasService = getPeneirasService;
        this.editPeneiraService = editPeneiraService;
    }

    @PostMapping
    public ResponseEntity<PeneiraResponseDTO> create(
            @Valid @RequestBody PeneiraCreateDTO dto
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
    public ResponseEntity<List<PeneiraResponseDTO>> getAll() {

        return ResponseEntity.ok(
                getPeneirasService.getAll()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeneiraUpdateResponseDTO> edit(
            @PathVariable UUID id,
            @Valid @RequestBody PeneiraUpdateDTO dto,
            Authentication authentication
    ) {

        UUID clubeId = (UUID) authentication.getPrincipal();

        PeneiraUpdateResponseDTO response =
                editPeneiraService.execute(id, clubeId, dto);

        return ResponseEntity.ok(response);
    }
}