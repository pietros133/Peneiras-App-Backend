package peneiras_app.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peneiras_app.dto.ClubeCreateDTO;
import peneiras_app.entity.Clube;
import peneiras_app.repository.ClubeRepository;

@Service
public class CreateClubeService {

    private final ClubeRepository clubeRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateClubeService(
            ClubeRepository clubeRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.clubeRepository = clubeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Clube create(ClubeCreateDTO dto) {

        if (clubeRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        Clube clube = new Clube();

        clube.setName(dto.getName());
        clube.setEmail(dto.getEmail());
        clube.setPassword(passwordEncoder.encode(dto.getPassword()));
        clube.setCategory(dto.getCategory());
        clube.setPhone(dto.getPhone());
        clube.setWhatsapp(dto.getWhatsapp());
        clube.setInstagramAccount(dto.getInstagramAccount());

        return clubeRepository.save(clube);
    }
}