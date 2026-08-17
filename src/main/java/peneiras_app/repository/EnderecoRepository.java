package peneiras_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peneiras_app.entity.Endereco;

import java.util.UUID;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {
}
