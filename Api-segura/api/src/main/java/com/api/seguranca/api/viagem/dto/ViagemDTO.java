package com.api.seguranca.api.viagem.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ViagemDTO {

    private Long veiculoId;
    private LocalDateTime dataSaida;
    private LocalDateTime dataChegada;
    private String origem;
    private String destino;
    private Double kmPercorrida;
}