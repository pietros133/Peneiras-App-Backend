package peneiras_app.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peneiras_app.dto.AuthDTO;
import peneiras_app.dto.AuthResponseDTO;
import peneiras_app.entity.Clube;
import peneiras_app.entity.Player;
import peneiras_app.repository.ClubeRepository;
import peneiras_app.repository.PlayerRepository;
import peneiras_app.security.JwtService;

@Service
public class AuthService {

    private final PlayerRepository playerRepository;
    private final ClubeRepository clubeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            PlayerRepository playerRepository,
            ClubeRepository clubeRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.playerRepository = playerRepository;
        this.clubeRepository = clubeRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponseDTO login(AuthDTO dto) {

        // 1. Procura o email entre os Players
        Player player = playerRepository
                .findByEmail(dto.getEmail())
                .orElse(null);

        // 2. Se encontrou um Player
        if (player != null) {

            // Confere a senha
            if (!passwordEncoder.matches(
                    dto.getPassword(),
                    player.getPassword()
            )) {
                throw new RuntimeException("Email ou senha inválidos");
            }

            // Gera o JWT usando o ID do Player
            String token = jwtService.generateToken(
                    player.getId()
            );

            return new AuthResponseDTO(
                    "Login realizado com sucesso",
                    token
            );
        }

        // 3. Se não encontrou Player, procura entre os Clubes
        Clube clube = clubeRepository
                .findByEmail(dto.getEmail())
                .orElse(null);

        // 4. Se encontrou um Clube
        if (clube != null) {

            // Confere a senha
            if (!passwordEncoder.matches(
                    dto.getPassword(),
                    clube.getPassword()
            )) {
                throw new RuntimeException("Email ou senha inválidos");
            }

            // Gera o JWT usando o ID do Clube
            String token = jwtService.generateToken(
                    clube.getId()
            );

            return new AuthResponseDTO(
                    "Login realizado com sucesso",
                    token
            );
        }

        // 5. Email não existe em nenhuma das entidades
        throw new RuntimeException("Email ou senha inválidos");
    }
}