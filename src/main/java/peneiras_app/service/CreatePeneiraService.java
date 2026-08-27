package peneiras_app.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import peneiras_app.dto.PeneiraCreateDTO;
import peneiras_app.entity.Clube;
import peneiras_app.entity.Peneira;
import peneiras_app.repository.ClubeRepository;
import peneiras_app.repository.PeneiraRepository;

import java.util.UUID;

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

    public Peneira create(PeneiraCreateDTO dto) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        UUID clubeId = (UUID) authentication.getPrincipal();

        Clube clube = clubeRepository.findById(clubeId)
                .orElseThrow(() ->
                        new RuntimeException("Clube não encontrado")
                );

        Peneira peneira = new Peneira();

        peneira.setClube(clube);
        peneira.setCategory(dto.getCategory());
        peneira.setModality(dto.getModality());
        peneira.setDate(dto.getDate());
        peneira.setHour(dto.getHour());
        peneira.setUniforms(dto.getUniforms());
        peneira.setDocuments(dto.getDocuments());
        peneira.setAbout(dto.getAbout());

        return peneiraRepository.save(peneira);
    }
}