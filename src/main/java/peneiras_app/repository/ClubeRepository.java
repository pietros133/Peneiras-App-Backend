package peneiras_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peneiras_app.entity.Clube;

import java.util.UUID;

@Repository
public interface ClubeRepository extends JpaRepository <Clube, UUID> {
}
