package com.api.seguranca.api.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.api.seguranca.api.veiculo.entity.VeiculoEntity;
import com.api.seguranca.api.veiculo.repository.VeiculoRepository;
import com.api.seguranca.api.viagem.entity.ViagemEntity;
import com.api.seguranca.api.viagem.repository.ViagemRepository;
import com.api.seguranca.api.manutencao.entity.ManutencaoEntity;
import com.api.seguranca.api.manutencao.repository.ManutencaoRepository;


@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(
            VeiculoRepository veiculoRepository,
            ViagemRepository viagemRepository,
            ManutencaoRepository manutencaoRepository
    ) {

        return args -> {

            if (veiculoRepository.count() == 0) {

                VeiculoEntity v1 = new VeiculoEntity();
                v1.setPlaca("ABC-1234");
                v1.setModelo("Fiorino");
                v1.setTipo("LEVE");
                v1.setAno(2022);

                v1 = veiculoRepository.save(v1);

                VeiculoEntity v2 = new VeiculoEntity();
                v2.setPlaca("XYZ-9876");
                v2.setModelo("Volvo FH");
                v2.setTipo("PESADO");
                v2.setAno(2021);

                v2 = veiculoRepository.save(v2);

                ViagemEntity viagem = new ViagemEntity();
                viagem.setVeiculo(v1);
                viagem.setDataSaida(LocalDateTime.parse("2024-05-01T08:00:00"));
                viagem.setDataChegada(LocalDateTime.parse("2024-05-01T18:00:00"));
                viagem.setOrigem("São Paulo");
                viagem.setDestino("Rio de Janeiro");
                viagem.setKmPercorrida(435.0);

                viagemRepository.save(viagem);

                ManutencaoEntity manutencao = new ManutencaoEntity();
                manutencao.setVeiculo(v1);
                manutencao.setDataInicio(LocalDate.parse("2024-06-10"));
                manutencao.setDataFinalizacao(LocalDate.parse("2024-06-11"));
                manutencao.setTipoServico("Troca de Óleo");
                manutencao.setCustoEstimado(BigDecimal.valueOf(350));
                manutencao.setStatus("PENDENTE");

                manutencaoRepository.save(manutencao);
            }
        };
    }
}