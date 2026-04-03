package com.api.seguranca.api.dashboard.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.api.seguranca.api.dashboard.repository.DashboardRepository;
import com.api.seguranca.api.dashboard.dto.DashboardDTO;

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

    public DashboardDTO retornoVolumePorCategoria(String tipo){
        DashboardDTO dto = new DashboardDTO();

        int totalVolume = repository.


    }

}