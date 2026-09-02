package peneiras_app.dto;

import peneiras_app.entity.enums.Category;

public record ClubeDataDTO(
        String name,
        String email,
        Category category,
        String phone,
        String whatsapp,
        String instagram
) {
}