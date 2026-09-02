package peneiras_app.dto;

import peneiras_app.entity.enums.DominantFoot;
import peneiras_app.entity.enums.Position;

import java.time.LocalDate;

public record PlayerDataDTO(
        String name,
        String email,
        LocalDate birthDate,
        Position position,
        DominantFoot dominantFoot,
        Integer heightCm,
        String userImg
) {
}