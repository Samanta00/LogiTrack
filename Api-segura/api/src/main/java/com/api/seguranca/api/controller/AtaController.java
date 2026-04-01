package com.api.seguranca.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.api.seguranca.api.repository.AtaRepository;
import com.api.seguranca.api.repository.WorkshopRepository;
import com.api.seguranca.api.repository.ColaboradorRepository;
import com.api.seguranca.api.model.AtaEntity;
import com.api.seguranca.api.model.ColaboradorEntity;
import com.api.seguranca.api.model.WorkshopEntity;
import java.util.List;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import com.api.seguranca.api.dto.ColaboradorResponse;





@RestController
@RequestMapping("/api/atas")
@CrossOrigin(
    origins = {
        "http://localhost:4200",
        "http://172.19.0.2:4200"
    }
)
public class AtaController {

    @Autowired
    private AtaRepository ataRepository;

    @Autowired
    private WorkshopRepository workshopRepository;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @PostMapping
    public AtaEntity criarAta(@RequestParam Integer workshopId) {
        WorkshopEntity workshop = workshopRepository.findById(workshopId)
                .orElseThrow(() -> new RuntimeException("Workshop não encontrado"));

        AtaEntity ata = new AtaEntity();
        ata.setWorkshop(workshop);

        return ataRepository.save(ata);
    }

        private List<ColaboradorResponse> mapearAtas(List<AtaEntity> atas) {
        return atas.stream()
            .flatMap(ata ->
                ata.getColaboradores().stream()
                    .map(colaborador ->
                        new ColaboradorResponse(
                            colaborador.getId(),
                            colaborador.getNome(),
                            ata.getWorkshop().getNome(),
                            ata.getWorkshop().getDataRealizacao()
                        )
                    )
            )
            .sorted((a, b) -> a.getNomeColaborador()
                .compareToIgnoreCase(b.getNomeColaborador()))
            .toList();
    }

    private List<ColaboradorResponse> mapearColaboradoresOrdenados() {
        List<AtaEntity> atas = ataRepository.findAll();
        return mapearAtas(atas);
    }


    @GetMapping
    public List<ColaboradorResponse> listarAtas(
            @RequestParam(required = false) String workshopNome,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data
    ) {

        if (workshopNome != null) {
            return mapearAtas(ataRepository.findByWorkshopNome(workshopNome));
        }

        if (data != null) {
            return mapearAtas(ataRepository.findByWorkshopData(data));
        }

        return mapearColaboradoresOrdenados();
    }


    @PutMapping("/{ataId}/colaboradores/{colaboradorId}")
    public AtaEntity adicionarColaborador(
            @PathVariable Integer ataId,
            @PathVariable Integer colaboradorId) {

        AtaEntity ata = ataRepository.findById(ataId)
                .orElseThrow(() -> new RuntimeException("Ata não encontrada"));

        ColaboradorEntity colaborador = colaboradorRepository.findById(colaboradorId)
                .orElseThrow(() -> new RuntimeException("Colaborador não encontrado"));

        ata.getColaboradores().add(colaborador);

        return ataRepository.save(ata);
    }

    @DeleteMapping("/{ataId}/colaboradores/{colaboradorId}")
    public AtaEntity removerColaborador(
            @PathVariable Integer ataId,
            @PathVariable Integer colaboradorId) {

        AtaEntity ata = ataRepository.findById(ataId)
                .orElseThrow(() -> new RuntimeException("Ata não encontrada"));

        ata.getColaboradores()
           .removeIf(c -> c.getId() == colaboradorId);

        return ataRepository.save(ata);
    }
}
