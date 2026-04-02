package com.api.seguranca.api.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import com.api.seguranca.api.viagem.entity.ViagemEntity;

@Repository
public interface DashboardRepository extends JpaRepository<ViagemEntity, Long> {

    @Query(value = """
        SELECT SUM(km_percorrida)
        FROM viagens
    """, nativeQuery = true)
    Double totalKm();
}