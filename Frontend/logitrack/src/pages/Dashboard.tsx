import { api } from "../api/api";
import { useEffect, useState } from "react";
import { motion } from "framer-motion";

import {
  AreaChart,
  Area,
  XAxis,
  YAxis,
  Tooltip,
  ResponsiveContainer,
  CartesianGrid,
  Legend
} from "recharts";

import "../personalizacao/dashboard/index.css";

import type {
  Ranking,
  Projecao,
  Manutencao,
  VolumePorCategoria,
} from "../types/Dashboard";

// Interface para a nova rota de veículo individual
interface DadosVeiculoUnico {
  totalKm: number;
  projecaoFinanceira: number;
}

export default function Dashboard() {
  const [ranking, setRanking] = useState<Ranking | null>(null);
  const [projecao, setProjecao] = useState<Projecao | null>(null);
  const [manutencoes, setManutencoes] = useState<Manutencao[]>([]);

  // Estados dos Filtros
  const [tipoVeiculo, setTipoVeiculo] = useState<string>("PESADO");
  const [volumeData, setVolumeData] = useState<VolumePorCategoria | null>(null);

  // Estados do Veículo Específico
  const [veiculoSelecionadoId, setVeiculoSelecionadoId] = useState<number>(1);
  const [dadosVeiculo, setDadosVeiculo] = useState<DadosVeiculoUnico | null>(null);

  const listaVeiculos = [
    { id: 1, nome: "Fiorino (ABC-1234)" },
    { id: 2, nome: "Volvo FH (XYZ-9876)" },
    { id: 3, nome: "Mercedes Sprinter (KJG-1122)" },
    { id: 4, nome: "Scania R500 (LMN-4455)" },
  ];

  // 1. Carregamento Inicial
  useEffect(() => {
    api.get("/dashboard/ranking").then((res) => setRanking(res.data)).catch(console.error);
    api.get("/dashboard/projecao").then((res) => setProjecao(res.data)).catch(console.error);
    api.get("/dashboard/manutencao").then((res) => setManutencoes(res.data)).catch(console.error);
  }, []);

  // 2. Carregamento por Categoria (Leve/Pesado)
  useEffect(() => {
    api.get(`/dashboard/categoria/${tipoVeiculo}`)
      .then((res) => setVolumeData(res.data))
      .catch(console.error);
  }, [tipoVeiculo]);

  // 3. Carregamento por Veículo Específico (ID)
  useEffect(() => {
    api.get(`/dashboard/${veiculoSelecionadoId}`)
      .then((res) => setDadosVeiculo(res.data))
      .catch(console.error);
  }, [veiculoSelecionadoId]);

  // Formatação dos dados do gráfico
  const dataGrafico = projecao?.porTipo?.map((item) => ({
    name: item.tipo,
    valorPrincipal: item.total,
    valorSombra: item.total * 0.2,
  })) || [];

  return (
    <div className="p-8 min-h-screen font-sans antialiased" style={{ backgroundColor: "#f4f7f6" }}>
      <div className="max-w-7xl mx-auto">
        <header className="mb-8">
          <h1 className="text-xl font-semibold text-gray-800 mb-6">LogiTrack Dashboard</h1>

          <div className="grid grid-cols-1 md:grid-cols-2 gap-4 p-4 bg-white rounded-lg shadow-sm border border-gray-200">
            <div>
              <label className="block text-[10px] uppercase font-bold text-gray-400 mb-1">Volume por Categoria</label>
              <select
                value={tipoVeiculo}
                onChange={(e) => setTipoVeiculo(e.target.value)}
                className="w-full bg-gray-50 border-none rounded-md py-1.5 px-3 text-sm font-semibold text-indigo-600 outline-none cursor-pointer"
              >
                <option value="PESADO">🚛 Pesado</option>
                <option value="LEVE">🚗 Leve</option>
              </select>
            </div>

            <div>
              <label className="block text-[10px] uppercase font-bold text-gray-400 mb-1">Análise Individual de Veículo</label>
              <select
                value={veiculoSelecionadoId}
                onChange={(e) => setVeiculoSelecionadoId(Number(e.target.value))}
                className="w-full bg-gray-50 border-none rounded-md py-1.5 px-3 text-sm font-semibold text-emerald-600 outline-none cursor-pointer"
              >
                {listaVeiculos.map(v => (
                  <option key={v.id} value={v.id}>{v.nome}</option>
                ))}
              </select>
            </div>
          </div>
        </header>

        {/* --- SEÇÃO DO GRÁFICO --- */}
        <div className="bg-white p-6 rounded-xl shadow-sm border border-gray-100 mb-8">
          <div className="flex justify-between items-center mb-6">
            <h2 className="text-[10px] uppercase font-bold text-gray-400 tracking-widest">Finance Forecast</h2>
            <span className="text-sm font-bold text-indigo-600">
              Total: R$ {projecao?.totalMesAtual?.toLocaleString("pt-BR") || "0,00"}
            </span>
          </div>
          <div className="h-[350px] w-full">
            {dataGrafico.length > 0 ? (
              <ResponsiveContainer width="100%" height="100%">
                <AreaChart data={dataGrafico} margin={{ bottom: 30, left: -20 }}>
                  <CartesianGrid strokeDasharray="0" vertical={false} stroke="#f0f0f0" />
                  <XAxis dataKey="name" axisLine={false} tickLine={false} tick={{ fill: "#999", fontSize: 10 }} interval={0} angle={-15} textAnchor="end" />
                  <YAxis axisLine={false} tickLine={false} tick={{ fill: "#999", fontSize: 10 }} />
                  <Tooltip formatter={(value: any) => [`R$ ${Number(value).toFixed(2)}`, "Custo"]} />
                  <Legend verticalAlign="top" align="right" iconType="circle" wrapperStyle={{ paddingBottom: "20px", fontSize: "12px" }} />
                  <Area name="Custo Base" type="monotone" dataKey="valorSombra" stackId="1" stroke="#b09c7a" fill="#c9bfa9" fillOpacity={0.8} strokeWidth={2} />
                  <Area name="Valor Real" type="monotone" dataKey="valorPrincipal" stackId="1" stroke="#6ba26b" fill="#a8d1a8" fillOpacity={0.8} strokeWidth={2} />
                </AreaChart>
              </ResponsiveContainer>
            ) : (
              <div className="flex items-center justify-center h-full text-gray-400 italic">Carregando projeções...</div>
            )}
          </div>
        </div>

        <div className="grid lg:grid-cols-4 gap-8">
          {/* TABELA */}
          <div className="lg:col-span-3 bg-white rounded-xl shadow-sm border border-gray-100 overflow-hidden">
            <table className="w-full text-left">
              <thead className="text-[10px] uppercase text-gray-400 bg-gray-50/50">
                <tr>
                  <th className="px-6 py-3">ID</th>
                  <th className="px-6 py-3">Veículo</th>
                  <th className="px-6 py-3">Serviço</th>
                  <th className="px-6 py-3">Custo</th>
                  <th className="px-6 py-3">Status</th>
                </tr>
              </thead>
              <tbody className="divide-y divide-gray-50 text-sm">
                {manutencoes.map((m) => (
                  <tr key={m.id} className="hover:bg-gray-50/50 transition-colors">
                    <td className="px-6 py-4 text-gray-400">#{m.id}</td>
                    <td className="px-6 py-4 font-medium text-gray-700">ID: {m.veiculoId}</td>
                    <td className="px-6 py-4 text-gray-500">{m.tipoServico}</td>
                    <td className="px-6 py-4 font-bold">R$ {m.custoEstimado.toFixed(2)}</td>
                    <td className="px-6 py-4">
                      <span className={`px-2 py-1 rounded-full text-[10px] font-bold ${m.status === "PENDENTE" ? "bg-amber-100 text-amber-600" : "bg-emerald-100 text-emerald-600"}`}>
                        {m.status}
                      </span>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>

          {/* CARDS LATERAIS */}
          <div className="lg:col-span-1 space-y-6">
            {/* NOVO CARD: VEÍCULO SELECIONADO */}
            <motion.div key={veiculoSelecionadoId} initial={{ opacity: 0, y: 10 }} animate={{ opacity: 1, y: 0 }} className="bg-white p-6 rounded-xl shadow-sm border-t-4 border-emerald-500">
              <p className="text-[10px] uppercase font-bold text-gray-400 mb-2">Análise Individual</p>
              <h4 className="text-xs font-bold text-gray-700 mb-4">{listaVeiculos.find(v => v.id === veiculoSelecionadoId)?.nome}</h4>
              <div className="space-y-3">
                <div className="flex justify-between border-b pb-1">
                  <span className="text-[10px] text-gray-400 uppercase">Km Total</span>
                  <span className="font-bold text-gray-800">{dadosVeiculo?.totalKm?.toFixed(1) || "0.0"}</span>
                </div>
              </div>
            </motion.div>

            {/* CARD CATEGORIA */}
            <motion.div key={tipoVeiculo} initial={{ opacity: 0 }} animate={{ opacity: 1 }} className="bg-white p-6 rounded-xl shadow-sm border-l-4 border-indigo-500">
              <p className="text-[10px] uppercase font-bold text-gray-400 mb-2">Categoria: {volumeData?.tipo || tipoVeiculo}</p>
              <p className="text-2xl font-bold text-gray-800">{volumeData?.quantidadeViagens || 0} <span className="text-xs font-normal text-gray-400">Viagens</span></p>
           
              <div className="space-y-4">
                <div>
                  <p className="text-xl font-bold text-indigo-600">
                    {Number(volumeData?.totalKm || 0).toFixed(1)} km
                  </p>
                  <p className="text-[10px] text-gray-400 uppercase font-bold">
                    Total Percorrido
                  </p>
                </div>
              </div>
           
            </motion.div>

            {/* CARD MAIOR KM GERAL */}
            <div className="bg-white p-6 rounded-xl shadow-sm border border-gray-100">
              <p className="text-[10px] uppercase font-bold text-gray-400 mb-2">
                Veiculo com maior Quilometragem
              </p>
              <p className="text-xl font-bold text-gray-800">
                {ranking?.modelo || "---"}
              </p>
              <p className="text-xs text-gray-400 mt-1">
                Total: {ranking?.totalKm || 0} km
              </p>
            </div>
            
          </div>
        </div>
      </div>
    </div>
  );
}