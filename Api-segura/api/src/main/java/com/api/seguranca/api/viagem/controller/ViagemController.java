package com.api.seguranca.api.viagem.controller;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import com.api.seguranca.api.viagem.service.ViagemService;
import com.api.seguranca.api.viagem.dto.ViagemDTO;
import com.api.seguranca.api.viagem.entity.ViagemEntity;

import java.util.List;

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
}