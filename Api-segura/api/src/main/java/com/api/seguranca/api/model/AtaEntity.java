package com.api.seguranca.api.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ata")
public class AtaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "workshop_id")
    private WorkshopEntity workshop;

    @ManyToMany
    @JoinTable(
        name = "ata_colaborador",
        joinColumns = @JoinColumn(name = "ata_id"),
        inverseJoinColumns = @JoinColumn(name = "colaborador_id")
    )
    private List<ColaboradorEntity> colaboradores = new ArrayList<>();

    public AtaEntity() {
    }

    public AtaEntity(WorkshopEntity workshop) {
        this.workshop = workshop;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public WorkshopEntity getWorkshop() {
        return workshop;
    }

    public void setWorkshop(WorkshopEntity workshop) {
        this.workshop = workshop;
    }

    public List<ColaboradorEntity> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(List<ColaboradorEntity> colaboradores) {
        this.colaboradores = colaboradores;
    }
}
