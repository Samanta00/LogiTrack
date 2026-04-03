package com.api.seguranca.api.viagem.controller;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import com.api.seguranca.api.viagem.service.ViagemService;
import com.api.seguranca.api.viagem.dto.ViagemDTO;
import com.api.seguranca.api.viagem.entity.ViagemEntity;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/viagens")
@RequiredArgsConstructor
public class ViagemController {

    private final ViagemService service;

    @PostMapping
    public ViagemEntity criar(@RequestBody ViagemDTO dto) {
        return service.salvar(dto);
    }

    @GetMapping
    public List<ViagemEntity> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ViagemEntity listarViagemPorId(@PathVariable Long id){
        return service.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Não encontrado nenhum registro dessa viagem"));
           
    }      
    
    @PutMapping("/{id}")
    public ViagemEntity atualizacaoViagemPorId(@PathVariable Long id, @RequestBody ViagemDTO dto){
        return service.atualizacaoViagem(id, dto);    
    
    }

    @DeleteMapping("/{id}")
    public void deletarViagemPorId(@PathVariable Long id){
        service.deletarViagem(id);
    }

}


        