package peneiras_app.service;

import org.springframework.stereotype.Service;
import peneiras_app.dto.PeneiraDTO;
import peneiras_app.entity.Peneira;
import peneiras_app.repository.PeneiraRepository;

import java.util.UUID;

@Service
public class EditPeneiraService {

    private final PeneiraRepository peneiraRepository;

    public EditPeneiraService(PeneiraRepository peneiraRepository) {
        this.peneiraRepository = peneiraRepository;
    }

    public void execute(
            UUID peneiraId,
            UUID clubeId,
            PeneiraDTO dto
    ) {

        Peneira peneira = peneiraRepository.findById(peneiraId)
                .orElseThrow(() -> new RuntimeException("Peneira não encontrada"));

        if (!peneira.getClube().getId().equals(clubeId)) {
            throw new RuntimeException("Clube não autorizado a editar esta peneira");
        }

        peneira.setCategory(dto.category());
        peneira.setModality(dto.modality());
        peneira.setDate(dto.date());
        peneira.setHour(dto.hour());
        peneira.setUniforms(dto.uniforms());
        peneira.setDocuments(dto.documents());
        peneira.setAbout(dto.about());

        peneiraRepository.save(peneira);

    }
}
