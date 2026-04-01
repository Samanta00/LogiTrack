package com.api.seguranca.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.seguranca.api.model.ColaboradorEntity;

public interface ColaboradorRepository extends JpaRepository<ColaboradorEntity, Integer> {
}
