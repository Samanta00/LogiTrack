import { api } from "./api";

export const listarManutencoes = () => api.get("/dashboard/manutencao");
