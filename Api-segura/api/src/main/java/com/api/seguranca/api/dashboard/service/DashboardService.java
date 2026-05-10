package com.api.seguranca.api.dashboard.service;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.val;

import com.api.seguranca.api.dashboard.repository.DashboardRepository;
import com.api.seguranca.api.dashboard.dto.DashboardDTO;
import com.api.seguranca.api.dashboard.dto.ProjecaoFinanceiraDTO;
import com.api.seguranca.api.dashboard.dto.ProjecaoTipoDTO;
import com.api.seguranca.api.dashboard.dto.RankingVeiculoDTO;
import com.api.seguranca.api.dashboard.dto.VolumePorCategoriaDTO;
import com.api.seguranca.api.manutencao.dto.ManutencaoDTO;

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
        List<Object[]> resultado = repository.somarKmEQuantidadePorTipo(tipo);
    
        if (!resultado.isEmpty()) {
            Object[] row = resultado.get(0);
    
            String categoria = (String) row[0];
            Double totalKm = ((Number) row[1]).doubleValue();
            Long quantidade = ((Number) row[2]).longValue();
    
            return new VolumePorCategoriaDTO(categoria, totalKm, quantidade);
        }
    
        return new VolumePorCategoriaDTO(tipo.toUpperCase(), 0.0, 0L);
    }

    public RankingVeiculoDTO getVeiculoMaisUtilizado() {

        List<Object[]> results = repository.findVeiculoMaisUtilizado();
    
        if (results.isEmpty()) {
            return new RankingVeiculoDTO(0L, 0.0, null, null);
        }
    
        Object[] result = results.get(0);
    
        return new RankingVeiculoDTO(
            ((Number) result[0]).longValue(),  
            ((Number) result[1]).doubleValue(), 
            (String) result[2], 
            (String) result[3]  
        );
    }
    public ProjecaoFinanceiraDTO getProjecaoFinanceiraMes() {

        Double total = repository.getProjecaoFinanceiraMes();

        List<Object[]> resultados = repository.getProjecaoPorTipo();

        List<ProjecaoTipoDTO> listaTipos = resultados.stream()
            .map(r -> new ProjecaoTipoDTO(
                (String) r[0],
                ((Number) r[1]).doubleValue()
            ))
            .toList();

        return new ProjecaoFinanceiraDTO(
            total != null ? total : 0.0,
            listaTipos
        );
    }

}