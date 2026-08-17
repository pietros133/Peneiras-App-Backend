package peneiras_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peneiras_app.entity.Peneira;

import java.util.UUID;

public interface PeneiraRepository extends JpaRepository<Peneira, UUID> {
}
