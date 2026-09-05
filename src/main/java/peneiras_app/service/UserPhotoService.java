package peneiras_app.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import peneiras_app.entity.Clube;
import peneiras_app.entity.Player;
import peneiras_app.repository.ClubeRepository;
import peneiras_app.repository.PlayerRepository;

import java.io.IOException;
import java.util.UUID;

@Service
public class UserPhotoService {

    private final PlayerRepository playerRepository;
    private final ClubeRepository clubeRepository;
    private final CloudinaryService cloudinaryService;

    public UserPhotoService(
            PlayerRepository playerRepository,
            ClubeRepository clubeRepository,
            CloudinaryService cloudinaryService
    ) {
        this.playerRepository = playerRepository;
        this.clubeRepository = clubeRepository;
        this.cloudinaryService = cloudinaryService;
    }

    public String uploadPlayerPhoto(UUID playerId, MultipartFile photo) throws IOException {

        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player não encontrado"));

        String imageUrl = cloudinaryService.uploadImage(photo);

        player.setUserImg(imageUrl);

        playerRepository.save(player);

        return imageUrl;
    }

    public String uploadClubePhoto(UUID clubeId, MultipartFile photo) throws IOException {

        Clube clube = clubeRepository.findById(clubeId)
                .orElseThrow(() -> new RuntimeException("Clube não encontrado"));

        String imageUrl = cloudinaryService.uploadImage(photo);

        clube.setClubeImg(imageUrl);

        clubeRepository.save(clube);

        return imageUrl;
    }
}