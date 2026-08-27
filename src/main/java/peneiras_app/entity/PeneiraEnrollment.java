package peneiras_app.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "player_peneira",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_player_peneira",
                        columnNames = {"player_id", "peneira_id"}
                )
        }
)
public class PeneiraEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "peneira_id", nullable = false)
    private Peneira peneira;

    @Column(name = "inscrito_em", nullable = false)
    private LocalDateTime enrolledAt;

    public PeneiraEnrollment() {
    }

    public PeneiraEnrollment(Player player, Peneira peneira) {
        this.player = player;
        this.peneira = peneira;
        this.enrolledAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Peneira getPeneira() {
        return peneira;
    }

    public void setPeneira(Peneira peneira) {
        this.peneira = peneira;
    }

    public LocalDateTime getEnrolledAt() {
        return enrolledAt;
    }

    public void setEnrolledAt(LocalDateTime enrolledAt) {
        this.enrolledAt = enrolledAt;
    }
}