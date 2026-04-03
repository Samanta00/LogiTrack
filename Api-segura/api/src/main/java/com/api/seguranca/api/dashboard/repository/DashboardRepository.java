package com.api.seguranca.api.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.seguranca.api.viagem.entity.ViagemEntity;

@Repository
public interface DashboardRepository extends JpaRepository<ViagemEntity, Long> {
    @Query(value = """
        SELECT SUM(km_percorrida)
        FROM viagens
    """, nativeQuery = true)
    Double totalKm();

    @Query(value = """
            SELECT SUM(vi.km_percorrida)
            FROM viagens vi
            WHERE vi.veiculo_id = :id
    """, nativeQuery = true)
    Double somarKmPorVeiculo(@Param("id") Long id);

    @Query("""
        SELECT ve.tipo, COUNT(*)
        FROM VeiculoEntity ve
        WHERE UPPER(ve.tipo) = UPPER(:tipo)
        GROUP BY ve.tipo
    """)
    List<Object[]> countByTipo(@Param("tipo") String tipo);

}

