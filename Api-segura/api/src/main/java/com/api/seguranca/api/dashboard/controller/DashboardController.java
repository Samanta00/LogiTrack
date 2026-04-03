package com.api.seguranca.api.dashboard.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.api.seguranca.api.dashboard.dto.DashboardDTO;
import com.api.seguranca.api.dashboard.dto.ManutencaoDTO;
import com.api.seguranca.api.dashboard.dto.VolumePorCategoriaDTO;
import com.api.seguranca.api.dashboard.service.DashboardService;
import com.api.seguranca.api.viagem.entity.ViagemEntity;


@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<DashboardDTO> getDashboard() {
        return ResponseEntity.ok(service.getDashboard());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DashboardDTO> getTotalPercorridoVeiculo(@PathVariable Long id){
        return ResponseEntity.ok(service.somarKmPorVeiculo(id));
    }
    @GetMapping("/categoria/{tipo}")
    public ResponseEntity<VolumePorCategoriaDTO> retornoVolumePorCategoria(@PathVariable String tipo){
        return ResponseEntity.ok(service.retornoVolumePorCategoria(tipo));
    }
    @GetMapping("/manutencao")
    public ResponseEntity<List<ManutencaoDTO>> getCronogramaManutencao(){
        return ResponseEntity.ok(service.cronogramaManutencao());
    }
        
       
    

  
}