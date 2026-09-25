package peneiras_app.service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import peneiras_app.dto.GetPeneirasDTO;
import peneiras_app.entity.enums.Uniform;
import peneiras_app.repository.GetPeneiraProjection;
import peneiras_app.repository.PeneiraRepository;

import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Service
public class GetPeneiraByClubeIdService {

    private final PeneiraRepository peneiraRepository;
    private final ObjectMapper objectMapper;

    public GetPeneiraByClubeIdService(PeneiraRepository peneiraRepository, ObjectMapper objectMapper) {
        this.peneiraRepository = peneiraRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional(readOnly = true)
    public List<GetPeneirasDTO> execute(UUID clubeId) {

        List<GetPeneiraProjection> items
                = peneiraRepository.findByClubeIdComUniformes(clubeId);

        return items.stream()
                .map(item -> new GetPeneirasDTO(
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
                ))
                .toList();
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