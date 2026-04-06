export type Ranking = {
    veiculoId: number;
    totalKm: number;
    modelo: string;
    placa: string;
};
  
export interface DadosVeiculoUnico {
    totalKm: number;
    projecaoFinanceira: number;
}

  export type DetalheProjecao = {
    tipo: string;
    total: number;
};

export type Projecao = {
    totalMesAtual: number;
    porTipo: DetalheProjecao[];
};

export type Manutencao = {
    id: number;
    veiculoId: number;
    dataInicio: string;
    tipoServico: string;
    custoEstimado: number;
    status: string;
};

export interface VolumePorCategoria {
    tipo: string;
    totalKm: number; 
    quantidadeViagens: number;
}