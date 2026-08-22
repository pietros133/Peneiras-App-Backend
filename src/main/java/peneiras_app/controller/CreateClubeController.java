package peneiras_app.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peneiras_app.dto.ClubeCreateDTO;
import peneiras_app.entity.Clube;
import peneiras_app.service.CreateClubeService;

@RestController
@RequestMapping("/api/auth/clube/register")
public class CreateClubeController {

    private final CreateClubeService clubeService;

    public CreateClubeController(CreateClubeService clubeService) {
        this.clubeService = clubeService;
    }

    @PostMapping
    public ResponseEntity<Clube> create(
            @Valid @RequestBody ClubeCreateDTO dto
    ) {
        Clube clube = clubeService.create(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clube);
    }
}