package peneiras_app.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import peneiras_app.entity.Clube;
import peneiras_app.entity.Peneira;
import peneiras_app.repository.ClubeRepository;
import peneiras_app.repository.PeneiraRepository;

import java.util.UUID;
import peneiras_app.dto.PeneiraDTO;

@Service
public class CreatePeneiraService {

    private final ClubeRepository clubeRepository;
    private final PeneiraRepository peneiraRepository;

    public CreatePeneiraService(
            ClubeRepository clubeRepository,
            PeneiraRepository peneiraRepository
    ) {
        this.clubeRepository = clubeRepository;
        this.peneiraRepository = peneiraRepository;
    }

    public Peneira create(PeneiraDTO dto) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        UUID clubeId = (UUID) authentication.getPrincipal();

        Clube clube = clubeRepository.findById(clubeId)
                .orElseThrow(() ->
                        new RuntimeException("Clube não encontrado")
                );

        Peneira peneira = new Peneira();

        peneira.setClube(clube);
        peneira.setCategory(dto.category());
        peneira.setModality(dto.modality());
        peneira.setDate(dto.date());
        peneira.setHour(dto.hour());
        peneira.setUniforms(dto.uniforms());
        peneira.setDocuments(dto.documents());
        peneira.setAbout(dto.about());

        return peneiraRepository.save(peneira);
    }
}