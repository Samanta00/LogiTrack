package com.api.seguranca.api.dto;

import java.time.LocalDateTime;

public class ColaboradorResponse {

    private int idColaborador;
    private String nomeColaborador;
    private String nomeWorkshop;
    private LocalDateTime dataWorkshop;

    public ColaboradorResponse(int idColaborador, String nomeColaborador,
                               String nomeWorkshop, LocalDateTime dataWorkshop) {
        this.idColaborador = idColaborador;
        this.nomeColaborador = nomeColaborador;
        this.nomeWorkshop = nomeWorkshop;
        this.dataWorkshop = dataWorkshop;
    }

    public int getIdColaborador() {
        return idColaborador;
    }

    public String getNomeColaborador() {
        return nomeColaborador;
    }

    public String getNomeWorkshop() {
        return nomeWorkshop;
    }

    public LocalDateTime getDataWorkshop() {
        return dataWorkshop;
    }
}
