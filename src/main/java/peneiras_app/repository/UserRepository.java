package peneiras_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peneiras_app.entity.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByEmail(String email);
}