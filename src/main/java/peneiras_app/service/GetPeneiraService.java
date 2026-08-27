package peneiras_app.service;

import org.springframework.stereotype.Service;
import peneiras_app.dto.PeneiraResponseDTO;
import peneiras_app.entity.Peneira;
import peneiras_app.repository.PeneiraRepository;

import java.util.List;

@Service
public class GetPeneiraService {

    private final PeneiraRepository peneiraRepository;

    public GetPeneiraService(PeneiraRepository peneiraRepository) {
        this.peneiraRepository = peneiraRepository;
    }

    public List<PeneiraResponseDTO> getAll() {

        List<Peneira> peneiras = peneiraRepository.findAll();

        return peneiras.stream()
                .map(peneira -> new PeneiraResponseDTO(
                        peneira.getId(),
                        peneira.getCategory(),
                        peneira.getModality(),
                        peneira.getDate(),
                        peneira.getHour(),
                        peneira.getUniforms(),
                        peneira.getDocuments(),
                        peneira.getAbout()
                ))
                .toList();
    }
}