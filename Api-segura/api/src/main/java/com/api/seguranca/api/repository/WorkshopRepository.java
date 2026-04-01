package com.api.seguranca.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.api.seguranca.api.model.WorkshopEntity;


public interface WorkshopRepository extends JpaRepository <WorkshopEntity, Integer>{
        List<WorkshopEntity> findByNomeContainingIgnoreCase(String nome);
}

