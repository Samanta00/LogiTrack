package com.api.seguranca.api.manutencao.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.api.seguranca.api.manutencao.entity.ManutencaoEntity;
import java.util.List;

@Repository
public interface ManutencaoRepository extends JpaRepository<ManutencaoEntity, Integer> {

    // Dashboard - próximas 5 manutenções
    @Query(value = """
        SELECT * FROM manutencoes
        ORDER BY data_inicio
        LIMIT 5
    """, nativeQuery = true)
    List<ManutencaoEntity> findProximasManutencoes();

}