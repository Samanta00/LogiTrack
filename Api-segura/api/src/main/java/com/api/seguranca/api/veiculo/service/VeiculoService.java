package com.api.seguranca.api.veiculo.service;

import org.springframework.stereotype.Service;

import com.api.seguranca.api.veiculo.entity.VeiculoEntity;
import com.api.seguranca.api.veiculo.repository.VeiculoRepository;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public VeiculoEntity buscarVeiculoPorId(Long id) {
        return veiculoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
    }
}