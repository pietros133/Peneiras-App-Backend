package peneiras_app.service;

import org.springframework.stereotype.Service;
import peneiras_app.dto.UserCreateDTO;
import peneiras_app.entity.User;
import peneiras_app.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(UserCreateDTO dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("E-mail ja cadastrado");
        }

        LocalDate minimumBirthDate = LocalDate.now().minusYears(10);

        if (dto.getBirthDate().isAfter(minimumBirthDate)) {
            throw new RuntimeException("O usuario deve ter no minimo 10 anos");
        }

        if (dto.getHeightCm() < 50 || dto.getHeightCm() > 250) {
            throw new RuntimeException("Altura invalida");
        }

        User user = new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setBirthDate(dto.getBirthDate());
        user.setPosition(dto.getPosition());
        user.setDominantFoot(dto.getDominantFoot());
        user.setHeightCm(dto.getHeightCm());

        return userRepository.save(user);
    }
}