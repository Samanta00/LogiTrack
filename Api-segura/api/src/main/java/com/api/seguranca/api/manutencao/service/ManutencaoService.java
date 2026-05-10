package com.api.seguranca.api.manutencao.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.seguranca.api.manutencao.dto.ManutencaoDTO;
import com.api.seguranca.api.manutencao.entity.ManutencaoEntity;
import com.api.seguranca.api.manutencao.repository.ManutencaoRepository;
import com.api.seguranca.api.veiculo.entity.VeiculoEntity;
import com.api.seguranca.api.veiculo.repository.VeiculoRepository;
import com.api.seguranca.api.viagem.entity.ViagemEntity;
import com.api.seguranca.api.viagem.repository.ViagemRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;


@Service
public class ManutencaoService {

    //ManutencaoRepository é o tipo e repository é o valo da variável
    @Autowired 
    private ManutencaoRepository manutencaoRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;


    public ManutencaoEntity salvarManutencao(ManutencaoDTO dto) {

        if (manutencaoRepository.existsConflitoManutencao(
            dto.getVeiculoId(),
            dto.getDataInicio(),
            dto.getDataFinalizacao()
        )) {
            throw new RuntimeException(
                "Veículo já possui manutenção nesse período."
            );
        }

        VeiculoEntity veiculo = veiculoRepository.findById(dto.getVeiculoId()).orElseThrow(()-> new RuntimeException("Veiculo não encontrado"));

        ManutencaoEntity manutencao = new ManutencaoEntity();
        manutencao.setCustoEstimado(dto.getCustoEstimado());
        manutencao.setDataFinalizacao(dto.getDataFinalizacao());
        manutencao.setDataInicio(dto.getDataInicio());
    
        veiculo.setId(dto.getVeiculoId());
        manutencao.setVeiculo(veiculo);

        manutencao.setStatus(dto.getStatus());
        manutencao.setTipoServico(dto.getTipoServico());     
        return manutencaoRepository.save(manutencao);
        
    }


    public List<ManutencaoDTO> cronogramaManutencao(){

        return manutencaoRepository.cronogramaManutencao(
            PageRequest.of(0, 5)
        );

       
    }
     
}
