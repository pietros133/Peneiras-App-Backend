package peneiras_app.dto;

import peneiras_app.entity.enums.Category;
import peneiras_app.entity.enums.DocumentType;
import peneiras_app.entity.enums.Modality;
import peneiras_app.entity.enums.Uniform;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.UUID;

public class PeneiraResponseDTO {

    private UUID id;
    private Category category;
    private Modality modality;
    private LocalDate date;
    private LocalTime hour;
    private Set<Uniform> uniforms;
    private DocumentType documents;
    private String about;

    public PeneiraResponseDTO(
            UUID id,
            Category category,
            Modality modality,
            LocalDate date,
            LocalTime hour,
            Set<Uniform> uniforms,
            DocumentType documents,
            String about
    ) {
        this.id = id;
        this.category = category;
        this.modality = modality;
        this.date = date;
        this.hour = hour;
        this.uniforms = uniforms;
        this.documents = documents;
        this.about = about;
    }

    public UUID getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public Modality getModality() {
        return modality;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getHour() {
        return hour;
    }

    public Set<Uniform> getUniforms() {
        return uniforms;
    }

    public DocumentType getDocuments() {
        return documents;
    }

    public String getAbout() {
        return about;
    }
}