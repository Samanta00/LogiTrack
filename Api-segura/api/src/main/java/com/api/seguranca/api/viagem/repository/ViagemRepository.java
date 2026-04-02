package com.api.seguranca.api.viagem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.api.seguranca.api.viagem.entity.ViagemEntity;
import java.util.List;

@Repository
public interface ViagemRepository extends JpaRepository<ViagemEntity, Long> {
    @Query("SELECT v FROM ViagemEntity v JOIN FETCH v.veiculo")
    List<ViagemEntity> findAllWithVeiculo();
}