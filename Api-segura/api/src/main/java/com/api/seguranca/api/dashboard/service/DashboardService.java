package com.api.seguranca.api.dashboard.service;

import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.api.seguranca.api.dashboard.repository.DashboardRepository;
import com.api.seguranca.api.dashboard.dto.DashboardDTO;
import com.api.seguranca.api.dashboard.dto.VolumePorCategoriaDTO;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final DashboardRepository repository;

    public DashboardDTO getDashboard() {

        DashboardDTO dto = new DashboardDTO();

        Double totalKm = repository.totalKm();

        dto.setTotalKm(totalKm != null ? totalKm : 0.0);

        dto.setProjecaoFinanceira(0.0);

        return dto;
    }

    public DashboardDTO somarKmPorVeiculo(Long id){
        DashboardDTO dto = new DashboardDTO();
    
        Double totalKm = repository.somarKmPorVeiculo(id);
    
        dto.setTotalKm(totalKm != null ? totalKm : 0.0);
        dto.setProjecaoFinanceira(0.0);
    
        return dto;
    }

    public VolumePorCategoriaDTO retornoVolumePorCategoria(String tipo) {
        List<Object[]> resultado = repository.countByTipo(tipo);
    
        if (!resultado.isEmpty()) {
            Object[] row = resultado.get(0);
    
            String categoria = (String) row[0];
            Long count = (Long) row[1];
    
            return new VolumePorCategoriaDTO(categoria, count);
        }
    
        return new VolumePorCategoriaDTO(tipo.toUpperCase(), 0L);
    }

}