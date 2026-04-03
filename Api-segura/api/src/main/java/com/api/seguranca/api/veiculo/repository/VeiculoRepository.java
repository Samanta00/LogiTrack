package com.api.seguranca.api.veiculo.repository;
import com.api.seguranca.api.veiculo.entity.VeiculoEntity;
import com.api.seguranca.api.viagem.entity.ViagemEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface VeiculoRepository extends JpaRepository<VeiculoEntity, Long> {

    @Query("SELECT v FROM VeiculoEntity v")
    List<VeiculoEntity> buscarTodosOsVeiculos();
    
    @Query("SELECT v FROM VeiculoEntity v WHERE v.id = :id")
    Optional<VeiculoEntity> encontrarUmaVeiculo(@Param("id") Long id);
}