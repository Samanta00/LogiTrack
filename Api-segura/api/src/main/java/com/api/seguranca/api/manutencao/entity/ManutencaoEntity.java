package com.api.seguranca.api.manutencao.entity;
import com.api.seguranca.api.veiculo.entity.VeiculoEntity;


import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import jakarta.persistence.*;


@Entity
@Table(name = "manutencoes")
public class ManutencaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private VeiculoEntity veiculo;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_finalizacao")
    private LocalDate dataFinalizacao;

    @Column(name = "tipo_servico")
    private String tipoServico;

    @Column(name = "custo_estimado")
    private BigDecimal custoEstimado;

    private String status;
}