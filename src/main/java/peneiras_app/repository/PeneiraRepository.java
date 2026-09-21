package peneiras_app.repository;

import peneiras_app.entity.Peneira;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PeneiraRepository extends JpaRepository<Peneira, UUID> {

    @Query(value = """
        SELECT 
            p.id, p.category, p.modality, p.date, p.hour, p.documents, p.about,
            c.name AS clube_nome,
            c.clube_img AS clube_imagem,
            e.cep AS endereco,
            json_agg(pu.uniform) FILTER (WHERE pu.uniform IS NOT NULL) AS uniforms
        FROM peneira AS p
        INNER JOIN clube AS c ON p.clube_id = c.id
        LEFT JOIN endereco AS e ON e.id = c.endereco_id
        LEFT JOIN peneira_uniform AS pu ON pu.peneira_id = p.id
        GROUP BY p.id, c.id, c.name, c.clube_img, e.cep
        """, nativeQuery = true)
    List<GetPeneiraProjection> findAllComClubeEUniformes();

    @Query(value = """
        SELECT 
            p.id, p.category, p.modality, p.date, p.hour, p.documents, p.about,
            json_agg(pu.uniform) FILTER (WHERE pu.uniform IS NOT NULL) AS uniforms
        FROM peneira AS p
        LEFT JOIN peneira_uniform AS pu ON pu.peneira_id = p.id
        WHERE p.id = :id
        GROUP BY p.id
        """, nativeQuery = true)
    Optional<GetPeneiraProjection> findByIdComUniformes(@Param("id") UUID id);
}
