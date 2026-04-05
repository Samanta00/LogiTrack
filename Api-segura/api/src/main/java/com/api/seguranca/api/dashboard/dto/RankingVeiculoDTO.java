package com.api.seguranca.api.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RankingVeiculoDTO {

    private Long veiculoId;
    private Double totalKm;
    private String modelo;
    private String placa;
}