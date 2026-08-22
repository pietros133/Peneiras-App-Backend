package peneiras_app.service;

import org.springframework.stereotype.Service;
import peneiras_app.dto.PlayerCreateDTO;
import peneiras_app.entity.Player;
import peneiras_app.repository.PlayerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Service
public class CreatePlayerService {

    private final PlayerRepository playerRepository;
    private final PasswordEncoder passwordEncoder;

    public CreatePlayerService(
            PlayerRepository playerRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.playerRepository = playerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Player create(PlayerCreateDTO dto) {

        if (playerRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("E-mail ja cadastrado");
        }

        LocalDate minimumBirthDate = LocalDate.now().minusYears(10);

        if (dto.getBirthDate().isAfter(minimumBirthDate)) {
            throw new RuntimeException("O usuario deve ter no minimo 10 anos");
        }

        if (dto.getHeightCm() < 50 || dto.getHeightCm() > 250) {
            throw new RuntimeException("Altura invalida");
        }

        Player player = new Player();

        player.setName(dto.getName());
        player.setEmail(dto.getEmail());
        player.setPassword(passwordEncoder.encode(dto.getPassword()));
        player.setBirthDate(dto.getBirthDate());
        player.setPosition(dto.getPosition());
        player.setDominantFoot(dto.getDominantFoot());
        player.setHeightCm(dto.getHeightCm());

        return playerRepository.save(player);
    }
}