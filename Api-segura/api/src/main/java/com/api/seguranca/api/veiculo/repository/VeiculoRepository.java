package com.api.seguranca.api.veiculo.repository;
import com.api.seguranca.api.veiculo.entity.VeiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VeiculoRepository extends JpaRepository<VeiculoEntity, Long> {
}