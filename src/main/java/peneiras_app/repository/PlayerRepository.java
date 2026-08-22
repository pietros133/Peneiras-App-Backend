package peneiras_app.repository;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import peneiras_app.entity.Player;

import java.util.Optional;
import java.util.UUID;

public interface PlayerRepository extends JpaRepository<Player, UUID> {
    boolean existsByEmail(String email);
    Optional<Player> findByEmail(String email);
}