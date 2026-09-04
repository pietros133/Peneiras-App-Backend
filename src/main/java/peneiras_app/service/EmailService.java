package peneiras_app.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendResetCode(String email, String code) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("contato.kairosgrafica@gmail.com");
        message.setTo(email);
        message.setSubject("Código para redefinição de senha");

        message.setText(
                "Olá!\n\n" +
                        "Seu código para redefinir a senha é:\n\n" +
                        code + "\n\n" +
                        "Esse código será utilizado para confirmar a redefinição da sua senha.\n\n" +
                        "Se você não solicitou a redefinição de senha, ignore este e-mail."
        );

        mailSender.send(message);
    }
}