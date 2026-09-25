package peneiras_app.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peneiras_app.dto.AuthDTO;
import peneiras_app.dto.AuthResponseDTO;
import peneiras_app.entity.Clube;
import peneiras_app.entity.Player;
import peneiras_app.entity.RefreshToken;
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
    private final RefreshTokenService refreshTokenService;

    public AuthService(
            PlayerRepository playerRepository,
            ClubeRepository clubeRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            EmailService emailService,
            ResetCodeService resetCodeService,
            PasswordResetService passwordResetService,
            RefreshTokenService refreshTokenService
    ) {
        this.playerRepository = playerRepository;
        this.clubeRepository = clubeRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.emailService = emailService;
        this.resetCodeService = resetCodeService;
        this.passwordResetService = passwordResetService;
        this.refreshTokenService = refreshTokenService;
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

            String accessToken = jwtService.generateToken(
                    player.getId(),
                    false
            );

            RefreshToken refreshToken
                    = refreshTokenService.createRefreshToken(player);

            return new AuthResponseDTO(
                    "Login realizado com sucesso",
                    accessToken,
                    refreshToken.getToken()
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

            String accessToken = jwtService.generateToken(
                    clube.getId(),
                    true
            );

            RefreshToken refreshToken
                    = refreshTokenService.createRefreshToken(clube);

            return new AuthResponseDTO(
                    "Login realizado com sucesso",
                    accessToken,
                    refreshToken.getToken()
            );
        }

        throw new RuntimeException("Email ou senha inválidos");
    }

    public AuthResponseDTO refreshToken(String token) {

        RefreshToken refreshToken
                = refreshTokenService.findByToken(token);

        refreshTokenService.verifyExpiration(refreshToken);

        String accessToken;

        if (refreshToken.getPlayer() != null) {

            accessToken = jwtService.generateToken(
                    refreshToken.getPlayer().getId(),
                    false
            );

        } else if (refreshToken.getClube() != null) {

            accessToken = jwtService.generateToken(
                    refreshToken.getClube().getId(),
                    true
            );

        } else {

            throw new RuntimeException(
                    "Refresh token sem usuário associado"
            );
        }

        return new AuthResponseDTO(
                "Token renovado com sucesso",
                accessToken,
                refreshToken.getToken()
        );
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

        String encodedPassword
                = passwordEncoder.encode(newPassword);

        Player player = playerRepository
                .findByEmail(email)
                .orElse(null);

        if (player != null) {

            player.setPassword(encodedPassword);
            playerRepository.save(player);

            passwordResetService.removeCode(email);

            return;
        }

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
