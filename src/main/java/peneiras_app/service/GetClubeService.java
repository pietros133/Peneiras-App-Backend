package peneiras_app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peneiras_app.dto.AddressDTO;
import peneiras_app.dto.GetClubeResponseDTO;
import peneiras_app.entity.Clube;
import peneiras_app.entity.Endereco;
import peneiras_app.repository.ClubeRepository;

import java.util.UUID;

@Service
public class GetClubeService {

    private final ClubeRepository clubeRepository;

    public GetClubeService(ClubeRepository clubeRepository) {
        this.clubeRepository = clubeRepository;
    }

    @Transactional(readOnly = true)
    public GetClubeResponseDTO execute(UUID clubeId) {
        Clube clube = clubeRepository.findById(clubeId)
                .orElseThrow(() -> new RuntimeException("Clube não encontrado"));

        Endereco endereco = clube.getAddress();
        AddressDTO addressDTO = null;

        if (endereco != null) {
            addressDTO = new AddressDTO(
                    endereco.getCep(),
                    endereco.getNumero(),
                    endereco.getComplemento()
            );
        }

        return new GetClubeResponseDTO(
                clube.getName(),
                clube.getEmail(),
                clube.getCategory(),
                clube.getPhone(),
                clube.getWhatsapp(),
                clube.getInstagramAccount(),
                clube.getClubeImg(),
                addressDTO
        );
    }
}
