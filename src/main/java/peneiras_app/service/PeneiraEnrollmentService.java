package peneiras_app.service;

import org.springframework.stereotype.Service;
import peneiras_app.entity.Peneira;
import peneiras_app.entity.PeneiraEnrollment;
import peneiras_app.entity.Player;
import peneiras_app.repository.PeneiraEnrollmentRepository;
import peneiras_app.repository.PeneiraRepository;
import peneiras_app.repository.PlayerRepository;

import java.util.UUID;

@Service
public class PeneiraEnrollmentService {

    private final PeneiraEnrollmentRepository peneiraEnrollmentRepository;
    private final PlayerRepository playerRepository;
    private final PeneiraRepository peneiraRepository;

    public PeneiraEnrollmentService(
            PeneiraEnrollmentRepository peneiraEnrollmentRepository,
            PlayerRepository playerRepository,
            PeneiraRepository peneiraRepository) {

        this.peneiraEnrollmentRepository = peneiraEnrollmentRepository;
        this.playerRepository = playerRepository;
        this.peneiraRepository = peneiraRepository;
    }

    public void enroll(UUID playerId, UUID peneiraId) {

        Player player = playerRepository.findById(playerId)
                .orElseThrow(() ->
                        new RuntimeException("Player não encontrado"));

        Peneira peneira = peneiraRepository.findById(peneiraId)
                .orElseThrow(() ->
                        new RuntimeException("Peneira não encontrada"));

        boolean alreadyEnrolled =
                peneiraEnrollmentRepository
                        .existsByPlayerIdAndPeneiraId(
                                player.getId(),
                                peneiraId
                        );

        if (alreadyEnrolled) {
            throw new RuntimeException(
                    "Player já está inscrito nesta peneira"
            );
        }

        PeneiraEnrollment enrollment =
                new PeneiraEnrollment(player, peneira);

        peneiraEnrollmentRepository.save(enrollment);
    }
}