package peneiras_app.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ResetCodeService {

    private final Random random = new Random();

    public String generateCode() {
        return String.format("%04d", random.nextInt(10000));
    }
}