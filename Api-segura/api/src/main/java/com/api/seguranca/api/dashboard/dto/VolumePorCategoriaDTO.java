package com.api.seguranca.api.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VolumePorCategoriaDTO {
    private String tipo;
    private Long quantidade;
}