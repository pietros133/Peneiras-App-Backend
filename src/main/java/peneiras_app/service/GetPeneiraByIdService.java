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

@Service
public class GetPeneiraByIdService {

    private final PeneiraRepository peneiraRepository;
    private final ObjectMapper objectMapper;

    public GetPeneiraByIdService(PeneiraRepository peneiraRepository, ObjectMapper objectMapper) {
        this.peneiraRepository = peneiraRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional(readOnly = true)
    public PeneiraDTO execute(UUID id) {
        GetPeneiraProjection item = peneiraRepository.findByIdComUniformes(id)
                .orElseThrow(() -> new RuntimeException("Peneira não encontrada"));

        Set<Uniform> uniforms = parseUniforms(item.getUniforms());

        return new PeneiraDTO(
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