package peneiras_app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peneiras_app.dto.PeneiraDTO;
import peneiras_app.entity.enums.Uniform;
import peneiras_app.repository.PeneiraRepository;
import peneiras_app.repository.GetPeneiraProjection;
import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import peneiras_app.dto.PeneiraResponseDTO;

@Service
public class GetPeneiraByPeneiraIdService {

    private final PeneiraRepository peneiraRepository;
    private final ObjectMapper objectMapper;

    public GetPeneiraByPeneiraIdService(PeneiraRepository peneiraRepository, ObjectMapper objectMapper) {
        this.peneiraRepository = peneiraRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional(readOnly = true)
    public PeneiraResponseDTO execute(UUID id) {
        GetPeneiraProjection item = peneiraRepository.findByPeneiraIdComUniformes(id)
                .orElseThrow(() -> new RuntimeException("Peneira não encontrada"));

        Set<Uniform> uniforms = parseUniforms(item.getUniforms());

        return new PeneiraResponseDTO(
                item.getId(),
                item.getCategory(),
                item.getModality(),
                item.getDate(),
                item.getHour(),
                uniforms,
                item.getDocuments(),
                item.getAbout()
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
