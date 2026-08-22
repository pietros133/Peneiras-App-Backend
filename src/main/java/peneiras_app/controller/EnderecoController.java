package peneiras_app.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import peneiras_app.dto.EnderecoDTO;
import peneiras_app.entity.Endereco;
import peneiras_app.service.EnderecoService;

@RestController
@RequestMapping("/users/me/address")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<Endereco> cadastrarEndereco(
            @Valid @RequestBody EnderecoDTO dto
    ) {

        Endereco endereco = enderecoService.cadastrarEndereco(dto);

        return ResponseEntity.ok(endereco);
    }
}