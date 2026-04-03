package com.api.seguranca.api.veiculo.dto;

import lombok.Data;

@Data
public class VeiculoDTO {
    private Long id;

    private String placa;
    private String modelo;
    private String tipo;
    private Integer ano;
}
