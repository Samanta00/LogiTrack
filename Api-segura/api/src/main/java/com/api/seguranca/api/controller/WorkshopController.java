package com.api.seguranca.api.controller;

import com.api.seguranca.api.repository.WorkshopRepository;
import com.api.seguranca.api.model.WorkshopEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.api.seguranca.api.repository.ColaboradorRepository;
import com.api.seguranca.api.model.AtaEntity;
import com.api.seguranca.api.model.ColaboradorEntity;
import com.api.seguranca.api.repository.AtaRepository;


@RestController
@CrossOrigin(
    origins = {
        "http://localhost:4200",
        "http://172.19.0.2:4200"
    }
)
@RequestMapping("/api/workshops")
public class WorkshopController {

    @Autowired
    private WorkshopRepository workshopRepository;

    @Autowired
    private AtaRepository ataRepository;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @PostMapping
    public WorkshopEntity criarWorkshop(@RequestBody WorkshopEntity workshop) {
        return workshopRepository.save(workshop);
    }

    @PutMapping("/{workshopId}/atas/{ataId}/colaboradores/{colaboradorId}")
    public AtaEntity adicionarColaboradorNaAta(
            @PathVariable Integer workshopId,
            @PathVariable Integer ataId,
            @PathVariable Integer colaboradorId
    ) {
        AtaEntity ata = ataRepository
                .findByIdAndWorkshopId(ataId, workshopId)
                .orElseThrow(() -> new RuntimeException("Ata não encontrada para este workshop"));

        ColaboradorEntity colaborador = colaboradorRepository
                .findById(colaboradorId)
                .orElseThrow(() -> new RuntimeException("Colaborador não encontrado"));

        if (!ata.getColaboradores().contains(colaborador)) {
            ata.getColaboradores().add(colaborador);
        }

        return ataRepository.save(ata);
    }




}
