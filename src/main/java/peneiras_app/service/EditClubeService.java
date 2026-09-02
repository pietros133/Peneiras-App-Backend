package peneiras_app.service;

import org.springframework.stereotype.Service;
import peneiras_app.dto.ClubeDataDTO;
import peneiras_app.dto.ClubeResponseDTO;
import peneiras_app.dto.ClubeUpdateDTO;
import peneiras_app.entity.Clube;
import peneiras_app.repository.ClubeRepository;

import java.util.UUID;

@Service
public class EditClubeService {

    private final ClubeRepository clubeRepository;

    public EditClubeService(ClubeRepository clubeRepository) {
        this.clubeRepository = clubeRepository;
    }

    public ClubeResponseDTO execute(UUID userId, ClubeUpdateDTO dto) {

        Clube clube = clubeRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Clube não encontrado"));

        if (!clube.getEmail().equals(dto.email())
                && clubeRepository.existsByEmail(dto.email())) {

            throw new RuntimeException("E-mail já cadastrado");
        }

        clube.setName(dto.name());
        clube.setEmail(dto.email());
        clube.setCategory(dto.category());
        clube.setPhone(dto.phone());
        clube.setWhatsapp(dto.whatsapp());
        clube.setInstagramAccount(dto.instagram());

        clubeRepository.save(clube);

        ClubeDataDTO data = new ClubeDataDTO(
                clube.getName(),
                clube.getEmail(),
                clube.getCategory(),
                clube.getPhone(),
                clube.getWhatsapp(),
                clube.getInstagramAccount()
        );

        return new ClubeResponseDTO(
                "Clube atualizado com sucesso",
                data
        );
    }
}