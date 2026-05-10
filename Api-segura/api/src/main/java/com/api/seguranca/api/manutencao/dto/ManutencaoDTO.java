package com.api.seguranca.api.manutencao.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ManutencaoDTO {

    private Long id;
    private Long veiculoId;
    private LocalDate dataInicio;
    private LocalDate dataFinalizacao;
    private String tipoServico;
    private BigDecimal custoEstimado;
    private String status;

    public ManutencaoDTO(
            Long id,
            Long veiculoId,
            LocalDate dataInicio,
            LocalDate dataFinalizacao,
            String tipoServico,
            BigDecimal custoEstimado,
            String status
    ) {
        this.id = id;
        this.veiculoId = veiculoId;
        this.dataInicio = dataInicio;
        this.dataFinalizacao = dataFinalizacao;
        this.tipoServico = tipoServico;
        this.custoEstimado = custoEstimado;
        this.status = status;
    }
}