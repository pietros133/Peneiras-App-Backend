package peneiras_app.service;

import org.springframework.stereotype.Service;
import peneiras_app.dto.PlayerDataDTO;
import peneiras_app.dto.PlayerResponseDTO;
import peneiras_app.dto.PlayerUpdateDTO;
import peneiras_app.entity.Player;
import peneiras_app.repository.PlayerRepository;

import java.util.UUID;

@Service
public class EditPlayerService {

    private final PlayerRepository playerRepository;

    public EditPlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public PlayerResponseDTO execute(UUID playerId, PlayerUpdateDTO dto) {

        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player não encontrado"));

        if (dto.getName() != null) {
            player.setName(dto.getName());
        }

        if (dto.getEmail() != null) {
            player.setEmail(dto.getEmail());
        }

        if (dto.getBirthDate() != null) {
            player.setBirthDate(dto.getBirthDate());
        }

        if (dto.getPosition() != null) {
            player.setPosition(dto.getPosition());
        }

        if (dto.getDominantFoot() != null) {
            player.setDominantFoot(dto.getDominantFoot());
        }

        if (dto.getHeightCm() != null) {
            player.setHeightCm(dto.getHeightCm());
        }

        if (dto.getUserImg() != null) {
            player.setUserImg(dto.getUserImg());
        }

        Player updatedPlayer = playerRepository.save(player);

        PlayerDataDTO playerData = new PlayerDataDTO(
                updatedPlayer.getName(),
                updatedPlayer.getEmail(),
                updatedPlayer.getBirthDate(),
                updatedPlayer.getPosition(),
                updatedPlayer.getDominantFoot(),
                updatedPlayer.getHeightCm(),
                updatedPlayer.getUserImg()
        );

        return new PlayerResponseDTO(
                "Player atualizado com sucesso",
                playerData
        );
    }
}