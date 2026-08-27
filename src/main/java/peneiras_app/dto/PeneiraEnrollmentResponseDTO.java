package peneiras_app.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class PeneiraEnrollmentResponseDTO {

    private UUID id;
    private PeneiraResponseDTO peneira;
    private LocalDateTime enrolledAt;

    public PeneiraEnrollmentResponseDTO(
            UUID id,
            PeneiraResponseDTO peneira,
            LocalDateTime enrolledAt
    ) {
        this.id = id;
        this.peneira = peneira;
        this.enrolledAt = enrolledAt;
    }

    public UUID getId() {
        return id;
    }

    public PeneiraResponseDTO getPeneira() {
        return peneira;
    }

    public LocalDateTime getEnrolledAt() {
        return enrolledAt;
    }
}