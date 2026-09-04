package peneiras_app.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peneiras_app.dto.AuthDTO;
import peneiras_app.dto.AuthResponseDTO;
import peneiras_app.entity.Clube;
import peneiras_app.entity.Player;
import peneiras_app.repository.ClubeRepository;
import peneiras_app.repository.PlayerRepository;
import peneiras_app.security.JwtService;

@Service
public class AuthService {

    private final PlayerRepository playerRepository;
    private final ClubeRepository clubeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmailService emailService;
    private final ResetCodeService resetCodeService;
    private final PasswordResetService passwordResetService;

    public AuthService(
            PlayerRepository playerRepository,
            ClubeRepository clubeRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            EmailService emailService,
            ResetCodeService resetCodeService,
            PasswordResetService passwordResetService
    ) {
        this.playerRepository = playerRepository;
        this.clubeRepository = clubeRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.emailService = emailService;
        this.resetCodeService = resetCodeService;
        this.passwordResetService = passwordResetService;
    }

    public AuthResponseDTO login(AuthDTO dto) {

        Player player = playerRepository
                .findByEmail(dto.getEmail())
                .orElse(null);

        if (player != null) {

            if (!passwordEncoder.matches(
                    dto.getPassword(),
                    player.getPassword()
            )) {
                throw new RuntimeException("Email ou senha inválidos");
            }

            String token = jwtService.generateToken(
                    player.getId()
            );

            return new AuthResponseDTO(
                    "Login realizado com sucesso",
                    token
            );
        }

        Clube clube = clubeRepository
                .findByEmail(dto.getEmail())
                .orElse(null);

        if (clube != null) {

            if (!passwordEncoder.matches(
                    dto.getPassword(),
                    clube.getPassword()
            )) {
                throw new RuntimeException("Email ou senha inválidos");
            }

            String token = jwtService.generateToken(
                    clube.getId()
            );

            return new AuthResponseDTO(
                    "Login realizado com sucesso",
                    token
            );
        }

        throw new RuntimeException("Email ou senha inválidos");
    }




    public void forgotPassword(String email) {

        Player player = playerRepository
                .findByEmail(email)
                .orElse(null);

        if (player != null) {

            String code = resetCodeService.generateCode();

            passwordResetService.saveCode(email, code);

            emailService.sendResetCode(email, code);

            return;
        }

        Clube clube = clubeRepository
                .findByEmail(email)
                .orElse(null);

        if (clube != null) {

            String code = resetCodeService.generateCode();

            passwordResetService.saveCode(email, code);

            emailService.sendResetCode(email, code);

            return;
        }

        throw new RuntimeException("Email não encontrado");
    }


    public void verifyResetCode(
            String email,
            String code
    ) {

        boolean valid = passwordResetService.verifyCode(
                email,
                code
        );

        if (!valid) {
            throw new RuntimeException("Código inválido");
        }
    }


    public void resetPassword(
            String email,
            String code,
            String newPassword
    ) {

        boolean valid = passwordResetService.verifyCode(
                email,
                code
        );

        if (!valid) {
            throw new RuntimeException("Código inválido");
        }

        String encodedPassword =
                passwordEncoder.encode(newPassword);

        // PLAYER

        Player player = playerRepository
                .findByEmail(email)
                .orElse(null);

        if (player != null) {

            player.setPassword(encodedPassword);

            playerRepository.save(player);

            passwordResetService.removeCode(email);

            return;
        }

        // CLUBE

        Clube clube = clubeRepository
                .findByEmail(email)
                .orElse(null);

        if (clube != null) {

            clube.setPassword(encodedPassword);

            clubeRepository.save(clube);

            passwordResetService.removeCode(email);

            return;
        }

        throw new RuntimeException("Email não encontrado");
    }
}