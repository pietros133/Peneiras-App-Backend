package peneiras_app.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import peneiras_app.dto.GetPeneirasDTO;
import peneiras_app.dto.PeneiraEnrollmentResponseDTO;
import peneiras_app.entity.PeneiraEnrollment;
import peneiras_app.entity.Player;
import peneiras_app.entity.enums.Uniform;
import peneiras_app.repository.GetPeneiraProjection;
import peneiras_app.repository.PeneiraEnrollmentRepository;
import peneiras_app.repository.PeneiraRepository;
import peneiras_app.repository.PlayerRepository;

import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Service
public class GetPeneiraEnrollmentService {

    private final PeneiraEnrollmentRepository peneiraEnrollmentRepository;
    private final PlayerRepository playerRepository;
    private final PeneiraRepository peneiraRepository;
    private final ObjectMapper objectMapper;

    public GetPeneiraEnrollmentService(
            PeneiraEnrollmentRepository peneiraEnrollmentRepository,
            PlayerRepository playerRepository,
            PeneiraRepository peneiraRepository,
            ObjectMapper objectMapper) {

        this.peneiraEnrollmentRepository = peneiraEnrollmentRepository;
        this.playerRepository = playerRepository;
        this.peneiraRepository = peneiraRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional(readOnly = true)
    public List<PeneiraEnrollmentResponseDTO> getAll(UUID playerId) {

        Player player = playerRepository.findById(playerId)
                .orElseThrow(()
                        -> new RuntimeException("Player não encontrado"));

        List<PeneiraEnrollment> peneiraEnrollments
                = peneiraEnrollmentRepository.findByPlayer(player);

        if (peneiraEnrollments.isEmpty()) {
            return Collections.emptyList();
        }

        Set<UUID> peneiraIds = peneiraEnrollments.stream()
                .map(enrollment -> enrollment.getPeneira().getId())
                .collect(Collectors.toSet());

        Map<UUID, GetPeneirasDTO> peneirasPorId = peneiraRepository
                .findByIdsComClubeEUniformes(peneiraIds)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toMap(GetPeneirasDTO::getId, Function.identity()));

        return peneiraEnrollments.stream()
                .filter(enrollment -> peneirasPorId.containsKey(enrollment.getPeneira().getId()))
                .map(enrollment
                        -> new PeneiraEnrollmentResponseDTO(
                        enrollment.getId(),
                        peneirasPorId.get(enrollment.getPeneira().getId()),
                        enrollment.getEnrolledAt()
                )
                )
                .toList();
    }

    private GetPeneirasDTO toDto(GetPeneiraProjection item) {
        return new GetPeneirasDTO(
                item.getId(),
                item.getCategory(),
                item.getModality(),
                item.getDate(),
                item.getHour(),
                parseUniforms(item.getUniforms()),
                item.getDocuments(),
                item.getAbout(),
                item.getClubeNome(),
                item.getClubeImagem(),
                item.getEndereco()
        );
    }

    private Set<Uniform> parseUniforms(String jsonUniforms) {
        if (jsonUniforms == null || jsonUniforms.isBlank()) {
            return Collections.emptySet();
        }
        try {
            List<String> list = objectMapper.readValue(jsonUniforms, new TypeReference<>() {
            });
            return list.stream().map(Uniform::valueOf).collect(Collectors.toSet());
        } catch (JacksonException e) {
            return Collections.emptySet();
        }
    }
}
