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

public class PeneiraCreateDTO {

    @NotNull
    private Category category;

    @NotNull
    private Modality modality;

    @NotNull
    private LocalDate date;

    @NotNull
    private LocalTime hour;

    @NotEmpty
    private Set<Uniform> uniforms;

    @NotNull
    private DocumentType documents;

    @NotBlank
    private String about;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Modality getModality() {
        return modality;
    }

    public void setModality(Modality modality) {
        this.modality = modality;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public Set<Uniform> getUniforms() {
        return uniforms;
    }

    public void setUniforms(Set<Uniform> uniforms) {
        this.uniforms = uniforms;
    }

    public DocumentType getDocuments() {
        return documents;
    }

    public void setDocuments(DocumentType documents) {
        this.documents = documents;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}