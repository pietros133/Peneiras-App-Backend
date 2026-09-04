package peneiras_app.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PasswordResetService {

    private final Map<String, String> resetCodes = new ConcurrentHashMap<>();

    public void saveCode(String email, String code) {
        resetCodes.put(email, code);
    }

    public boolean verifyCode(String email, String code) {

        String savedCode = resetCodes.get(email);

        if (savedCode == null) {
            return false;
        }

        return savedCode.equals(code);
    }

    public void removeCode(String email) {
        resetCodes.remove(email);
    }
}