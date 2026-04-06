export interface Manutencao {
    id?: number;
    veiculoId: number;
    dataInicio: string;
    dataFimPrevista: string;
    tipoServico: string;
    custoEstimado: number;
    status: string;
  }
  