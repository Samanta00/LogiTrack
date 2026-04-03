package com.api.seguranca.api.viagem.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.api.seguranca.api.viagem.entity.ViagemEntity;
import com.api.seguranca.api.viagem.dto.ViagemDTO;
import com.api.seguranca.api.viagem.repository.ViagemRepository;

import jakarta.transaction.Transactional;

import com.api.seguranca.api.veiculo.entity.VeiculoEntity;
import com.api.seguranca.api.veiculo.repository.VeiculoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ViagemService {

    private final ViagemRepository viagemRepository;
    private final VeiculoRepository veiculoRepository;

    public ViagemEntity salvar(ViagemDTO dto) {

        if (dto.getVeiculoId() == null) {
            throw new RuntimeException("veiculoId não pode ser null");
        };        

        VeiculoEntity veiculo = veiculoRepository.findById(dto.getVeiculoId())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));


        ViagemEntity viagem = new ViagemEntity();
        viagem.setVeiculo(veiculo);
        viagem.setDataSaida(dto.getDataSaida());
        viagem.setDataChegada(dto.getDataChegada());
        viagem.setOrigem(dto.getOrigem());
        viagem.setDestino(dto.getDestino());
        viagem.setKmPercorrida(dto.getKmPercorrida());

        return viagemRepository.save(viagem);
    }

    public List<ViagemEntity> listar() {
        return viagemRepository.findAllWithVeiculo();
    }
    
    public Optional <ViagemEntity> buscarPorId(Long id){
        return viagemRepository.encontrarUmaViagem(id);
    }

    @Transactional
    public ViagemEntity atualizacaoViagem(Long id, ViagemDTO dto){
        ViagemEntity viagem = viagemRepository.encontrarUmaViagem(id)
            .orElseThrow(() -> new RuntimeException("Viagem não encontrada"));
    
        // BUSCAR veículo real no banco
        VeiculoEntity veiculo = veiculoRepository.findById(dto.getVeiculoId())
        .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
    
        viagem.setVeiculo(veiculo);
        viagem.setDestino(dto.getDestino());
        viagem.setOrigem(dto.getOrigem());
        viagem.setKmPercorrida(dto.getKmPercorrida());
        viagem.setDataSaida(dto.getDataSaida());
        viagem.setDataChegada(dto.getDataChegada());
    
        return viagemRepository.save(viagem);
    }

    @Transactional
    public void deletarViagem(Long id){
        if (!viagemRepository.existsById(id)) {
            throw new RuntimeException("Viagem não encontrada");
        }
    
        viagemRepository.deleteById(id);
    }

}