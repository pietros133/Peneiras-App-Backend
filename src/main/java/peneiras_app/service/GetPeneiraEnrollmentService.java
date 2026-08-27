package peneiras_app.service;

import org.springframework.stereotype.Service;
import peneiras_app.dto.PeneiraEnrollmentResponseDTO;
import peneiras_app.dto.PeneiraResponseDTO;
import peneiras_app.entity.PeneiraEnrollment;
import peneiras_app.entity.Player;
import peneiras_app.repository.PeneiraEnrollmentRepository;
import peneiras_app.repository.PlayerRepository;

import java.util.List;
import java.util.UUID;

@Service
public class GetPeneiraEnrollmentService {

    private final PeneiraEnrollmentRepository peneiraEnrollmentRepository;
    private final PlayerRepository playerRepository;

    public GetPeneiraEnrollmentService(
            PeneiraEnrollmentRepository peneiraEnrollmentRepository,
            PlayerRepository playerRepository) {

        this.peneiraEnrollmentRepository = peneiraEnrollmentRepository;
        this.playerRepository = playerRepository;
    }

    public List<PeneiraEnrollmentResponseDTO> getAll(UUID playerId) {

        Player player = playerRepository.findById(playerId)
                .orElseThrow(() ->
                        new RuntimeException("Player não encontrado"));

        List<PeneiraEnrollment> peneiraEnrollments =
                peneiraEnrollmentRepository.findByPlayer(player);

        return peneiraEnrollments.stream()
                .map(peneiraEnrollment ->
                        new PeneiraEnrollmentResponseDTO(
                                peneiraEnrollment.getId(),

                                new PeneiraResponseDTO(
                                        peneiraEnrollment.getPeneira().getId(),
                                        peneiraEnrollment.getPeneira().getCategory(),
                                        peneiraEnrollment.getPeneira().getModality(),
                                        peneiraEnrollment.getPeneira().getDate(),
                                        peneiraEnrollment.getPeneira().getHour(),
                                        peneiraEnrollment.getPeneira().getUniforms(),
                                        peneiraEnrollment.getPeneira().getDocuments(),
                                        peneiraEnrollment.getPeneira().getAbout()
                                ),

                                peneiraEnrollment.getEnrolledAt()
                        )
                )
                .toList();
    }
}