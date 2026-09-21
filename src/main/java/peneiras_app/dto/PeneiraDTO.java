package peneiras_app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import peneiras_app.entity.enums.Category;
import peneiras_app.entity.enums.DocumentType;
import peneiras_app.entity.enums.Modality;
import peneiras_app.entity.enums.Uniform;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public record PeneiraDTO(
        @NotNull(message = "Categoria é obrigatória")
        Category category,
        @NotNull(message = "Modalidade é obrigatória")
        Modality modality,
        @NotNull(message = "Data é obrigatória")
        LocalDate date,
        @NotNull(message = "Horário é obrigatório")
        LocalTime hour,
        @NotEmpty(message = "Uniforme é obrigatório")
        Set<Uniform> uniforms,
        @NotNull(message = "Documentos são obrigatórios")
        DocumentType documents,
        @NotBlank(message = "Sobre é obrigatório")
        String about
        ) {

}
