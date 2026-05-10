package com.api.seguranca.api.manutencao.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;

import com.api.seguranca.api.manutencao.dto.ManutencaoDTO;
import com.api.seguranca.api.manutencao.entity.ManutencaoEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ManutencaoRepository extends JpaRepository<ManutencaoEntity, Long> {

    @Query("""
        SELECT COUNT(m) > 0
        FROM ManutencaoEntity m
        WHERE m.veiculo.id = :veiculoId
        AND (
            :dataInicio BETWEEN m.dataInicio AND m.dataFinalizacao
            OR
            :dataFinalizacao BETWEEN m.dataInicio AND m.dataFinalizacao
            OR
            m.dataInicio BETWEEN :dataInicio AND :dataFinalizacao
        )
    """)
    boolean existsConflitoManutencao(
        Long veiculoId,
        LocalDate dataInicio,
        LocalDate dataFinalizacao
    );

    @Query("""
        SELECT new com.api.seguranca.api.manutencao.dto.ManutencaoDTO(
            m.id,
            m.veiculo.id,
            m.dataInicio,
            m.dataFinalizacao,
            m.tipoServico,
            m.custoEstimado,
            m.status
        )
        FROM ManutencaoEntity m
        WHERE m.status = 'PENDENTE'
        ORDER BY m.dataInicio ASC
    """)
    List<ManutencaoDTO> cronogramaManutencao(Pageable pageable);

    @Query("""
        SELECT new com.api.seguranca.api.manutencao.dto.ManutencaoDTO(
            m.id,
            m.veiculo.id,
            m.dataInicio,
            m.dataFinalizacao,
            m.tipoServico,
            m.custoEstimado,
            m.status
        )
        FROM ManutencaoEntity m
        ORDER BY m.dataInicio ASC
    """)
    List<ManutencaoDTO> findProximasManutencoes();

}