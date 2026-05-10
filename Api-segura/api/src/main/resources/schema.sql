CREATE TABLE IF NOT EXISTS veiculos (
    id SERIAL PRIMARY KEY,
    placa VARCHAR(10) UNIQUE NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    tipo VARCHAR(20) CHECK (tipo IN ('LEVE', 'PESADO')), -- Exemplo de constraint
    ano INTEGER
);

CREATE TABLE IF NOT EXISTS viagens (
    id SERIAL PRIMARY KEY,
    veiculo_id INTEGER REFERENCES veiculos(id) ON DELETE CASCADE,
    data_saida TIMESTAMP NOT NULL,
    data_chegada TIMESTAMP,
    origem VARCHAR(100),
    destino VARCHAR(100),
    km_percorrida DECIMAL(10,2),

    CONSTRAINT regra_de_negocio_viagem UNIQUE (
        FOREIGN KEY (veiculo_id)
        REFERENCES veiculos(id)
        ON DELETE CASCADE -- se o veiculo for deletado todas as viagens referentes a ele serão deletadas juntas
    ),

    
);


CREATE TABLE IF NOT EXISTS manutencoes (
    id SERIAL PRIMARY KEY,
    veiculo_id INTEGER REFERENCES veiculos(id) ON DELETE CASCADE,
    data_inicio DATE NOT NULL,
    data_finalizacao DATE,
    tipo_servico VARCHAR(100),
    custo_estimado DECIMAL(10,2),
    status VARCHAR(20) DEFAULT 'PENDENTE', -- PENDENTE, EM_REALIZACAO, CONCLUIDA

    CONSTRAINT regra_de_negocio_manutencao(
        FOREIGN KEY (veiculo_id) 
        REFERENCES veiculos(id)
        ON DELETE CASCADE
    ),

    
);

