package peneiras_app.dto;

import peneiras_app.entity.enums.Category;
import peneiras_app.entity.enums.DocumentType;
import peneiras_app.entity.enums.Modality;
import peneiras_app.entity.enums.Uniform;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public record PeneiraUpdateDTO(
        Category category,
        Modality modality,
        LocalDate date,
        LocalTime hour,
        Set<Uniform> uniforms,
        DocumentType documents,
        String about
) {
}