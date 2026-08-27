package peneiras_app.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import peneiras_app.dto.EnderecoDTO;
import peneiras_app.dto.ViaCepResponseDTO;
import peneiras_app.entity.Endereco;
import peneiras_app.entity.Player;
import peneiras_app.entity.Clube;
import peneiras_app.repository.EnderecoRepository;
import peneiras_app.repository.PlayerRepository;
import peneiras_app.repository.ClubeRepository;

import java.util.UUID;

@Service
public class EnderecoService {

    private final PlayerRepository playerRepository;
    private final ClubeRepository clubeRepository;
    private final EnderecoRepository enderecoRepository;
    private final ViaCepService viaCepService;

    public EnderecoService(
            PlayerRepository playerRepository,
            ClubeRepository clubeRepository,
            EnderecoRepository enderecoRepository,
            ViaCepService viaCepService
    ) {
        this.playerRepository = playerRepository;
        this.clubeRepository = clubeRepository;
        this.enderecoRepository = enderecoRepository;
        this.viaCepService = viaCepService;
    }

    public Endereco cadastrarEndereco(EnderecoDTO dto) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        UUID userId = (UUID) authentication.getPrincipal();

        Player player = playerRepository
                .findById(userId)
                .orElse(null);

        Clube clube = null;

        if (player == null) {
            clube = clubeRepository
                    .findById(userId)
                    .orElse(null);
        }

        if (player == null && clube == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        ViaCepResponseDTO viaCep =
                viaCepService.buscarCep(dto.getCep());

        if (viaCep == null || viaCep.isErro()) {
            throw new RuntimeException("CEP não encontrado");
        }

        Endereco endereco = new Endereco(
                viaCep.getLogradouro(),
                viaCep.getBairro(),
                dto.getNumero(),
                viaCep.getCep(),
                viaCep.getLocalidade(),
                viaCep.getUf(),
                dto.getComplemento()
        );

        endereco = enderecoRepository.save(endereco);

        if (player != null) {
            player.setAddress(endereco);
            playerRepository.save(player);
        }

        if (clube != null) {
            clube.setAddress(endereco);
            clubeRepository.save(clube);
        }

        return endereco;
    }
}