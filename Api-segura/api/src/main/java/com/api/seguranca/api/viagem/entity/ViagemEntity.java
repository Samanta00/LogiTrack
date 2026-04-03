package com.api.seguranca.api.viagem.entity;

import com.api.seguranca.api.veiculo.entity.VeiculoEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "viagens")
public class ViagemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private VeiculoEntity veiculo;

    @Column(name = "data_saida")
    private LocalDateTime dataSaida;

    @Column(name = "data_chegada")
    private LocalDateTime dataChegada;

    private String origem;
    private String destino;
    private Double kmPercorrida;

    // getters e setters
    public Long getId() { return id; }

    public VeiculoEntity getVeiculo() { return veiculo; }
    public void setVeiculo(VeiculoEntity veiculo) { this.veiculo = veiculo; }
    
    public LocalDateTime getDataSaida() { return dataSaida; }
    public void setDataSaida(LocalDateTime dataSaida) { this.dataSaida = dataSaida; }

    public LocalDateTime getDataChegada() { return dataChegada; }
    public void setDataChegada(LocalDateTime dataChegada) { this.dataChegada = dataChegada; }

    public String getOrigem() { return origem; }
    public void setOrigem(String origem) { this.origem = origem; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }

    public Double getKmPercorrida() { return kmPercorrida; }
    public void setKmPercorrida(Double kmPercorrida) { this.kmPercorrida = kmPercorrida; }
}