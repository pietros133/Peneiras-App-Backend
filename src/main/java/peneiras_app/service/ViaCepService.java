package peneiras_app.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import peneiras_app.dto.ViaCepResponseDTO;

@Service
public class ViaCepService {
    private final RestTemplate restTemplate;

    public ViaCepService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ViaCepResponseDTO buscarCep(String cep){

        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        return restTemplate.getForObject(
                url,
                ViaCepResponseDTO.class
        );
    }
}
