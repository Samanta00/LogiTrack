package com.api.seguranca.api.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjecaoTipoDTO {
    private String tipo;
    private Double total;
}