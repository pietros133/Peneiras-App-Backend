package peneiras_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peneiras_app.entity.PeneiraEnrollment;
import peneiras_app.entity.Player;

import java.util.List;
import java.util.UUID;

public interface PeneiraEnrollmentRepository extends JpaRepository<PeneiraEnrollment, UUID> {

    boolean existsByPlayerIdAndPeneiraId(UUID playerId, UUID peneiraId);

    List<PeneiraEnrollment> findByPlayerId(UUID playerId);

    List<PeneiraEnrollment> findByPeneiraId(UUID peneiraId);

    List<PeneiraEnrollment> findByPlayer(Player player);
}