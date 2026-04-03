package com.api.seguranca.api.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ManutencaoDTO {

    private Long id;
    private Long veiculoId;
    private LocalDate dataInicio;
    private LocalDate dataFinalizacao;
    private String tipoServico;
    private Double custoEstimado;
    private String status;
}