package com.api.seguranca.api.dashboard.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjecaoFinanceiraDTO {

    private Double totalMesAtual;
    private List<ProjecaoTipoDTO> porTipo;

}
