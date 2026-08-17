package peneiras_app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import peneiras_app.entity.enums.Categoria;
import peneiras_app.entity.enums.DocumentType;
import peneiras_app.entity.enums.Modalidade;
import peneiras_app.entity.enums.Uniform;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "peneira")
public class Peneira {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria category;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Modalidade modality;

    @NotNull
    @Column(nullable = false)
    private LocalDate date;

    @NotNull
    @Column(nullable = false)
    private LocalTime hour;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Uniform uniform;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DocumentType documents;

    @NotBlank
    @Column(nullable = false)
    private String about;

    @ManyToOne
    @JoinColumn(name = "clube_id", nullable = false)
    private Clube clube;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Categoria getCategory() {
        return category;
    }

    public void setCategory(Categoria category) {
        this.category = category;
    }

    public Modalidade getModality() {
        return modality;
    }

    public void setModality(Modalidade modality) {
        this.modality = modality;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public Uniform getUniform() {
        return uniform;
    }

    public void setUniform(Uniform uniform) {
        this.uniform = uniform;
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

    public Clube getClube() {
        return clube;
    }
}