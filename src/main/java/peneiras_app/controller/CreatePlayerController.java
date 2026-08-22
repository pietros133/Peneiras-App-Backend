package peneiras_app.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peneiras_app.dto.PlayerCreateDTO;
import peneiras_app.entity.Player;
import peneiras_app.service.CreatePlayerService;

@RestController
@RequestMapping("auth/register")
public class CreatePlayerController {

    private final CreatePlayerService createPlayerService;

    public CreatePlayerController(CreatePlayerService createPlayerService) {
        this.createPlayerService = createPlayerService;
    }

    @PostMapping
    public ResponseEntity<Player> create(@Valid @RequestBody PlayerCreateDTO dto) {
        Player player = createPlayerService.create(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(player);
    }
}
