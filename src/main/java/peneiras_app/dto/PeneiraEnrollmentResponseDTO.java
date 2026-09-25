package peneiras_app.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class PeneiraEnrollmentResponseDTO {

    private final UUID id;
    private final GetPeneirasDTO peneira;
    private final LocalDateTime enrolledAt;

    public PeneiraEnrollmentResponseDTO(
            UUID id,
            GetPeneirasDTO peneira,
            LocalDateTime enrolledAt
    ) {
        this.id = id;
        this.peneira = peneira;
        this.enrolledAt = enrolledAt;
    }

    public UUID getId() {
        return id;
    }

    public GetPeneirasDTO getPeneira() {
        return peneira;
    }

    public LocalDateTime getEnrolledAt() {
        return enrolledAt;
    }
}