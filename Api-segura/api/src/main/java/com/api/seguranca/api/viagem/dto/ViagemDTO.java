package com.api.seguranca.api.viagem.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ViagemDTO {
    private Long veiculoId;
    private String origem;
    private String destino;
    private Double kmPercorrida;
    private LocalDateTime dataSaida;
    private LocalDateTime dataChegada;
}