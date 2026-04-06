import { useEffect, useState } from "react";
import { api } from "../api/api";
import { motion, AnimatePresence } from "framer-motion";
import { Trash2, Edit3, Plus, X } from "lucide-react"; // npm install lucide-react
import "../personalizacao/dashboard/index.css";

// Interface baseada no seu JSON de retorno
interface Viagem {
  id: number;
  veiculo: {
    id: number;
    modelo: string;
    placa: string;
  };
  origem: string;
  destino: string;
  kmPercorrida: number;
  dataSaida: string;
  dataChegada: string;
}

const VEICULOS_DISPONIVEIS = [
  { id: 1, nome: "Fiorino (ABC-1234)" },
  { id: 2, nome: "Volvo FH (XYZ-9876)" },
  { id: 3, nome: "Mercedes Sprinter (KJG-1122)" },
  { id: 4, nome: "Scania R500 (LMN-4455)" },
];

export default function Viagem() {
  const [viagens, setViagens] = useState<Viagem[]>([]);
  const [loading, setLoading] = useState(false);
  const [editMode, setEditMode] = useState(false);
  const [selectedId, setSelectedId] = useState<number | null>(null);

  const [formData, setFormData] = useState({
    veiculoId: 1,
    origem: "",
    destino: "",
    kmPercorrida: 0,
    dataSaida: "",
    dataChegada: ""
  });

  // 1. LISTAR VIAGENS
  const carregarViagens = async () => {
    try {
      const res = await api.get("/viagens");
      setViagens(res.data);
    } catch (e) {
      console.error("Erro ao listar", e);
    }
  };

  useEffect(() => { carregarViagens(); }, []);

  // 2. BUSCAR VIAGEM PARA EDITAR (Pelo ID na rota)
  const selecionarParaEdicao = async (id: number) => {
    try {
      const res = await api.get(`/viagens/${id}`);
      const v = res.data;
      setFormData({
        veiculoId: v.veiculo.id,
        origem: v.origem,
        destino: v.destino,
        kmPercorrida: v.kmPercorrida,
        dataSaida: v.dataSaida.split('.')[0], // Limpa milissegundos para o input
        dataChegada: v.dataChegada.split('.')[0]
      });
      setSelectedId(id);
      setEditMode(true);
      window.scrollTo({ top: 0, behavior: 'smooth' });
    } catch (e) {
      alert("Erro ao buscar detalhes da viagem.");
    }
  };

  // 3. CRIAR OU ATUALIZAR
  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    try {
      if (editMode && selectedId) {
        await api.put(`/viagens/${selectedId}`, formData);
      } else {
        await api.post("/viagens", formData);
      }
      resetForm();
      carregarViagens();
    } catch (e) {
      alert("Erro na operação.");
    } finally {
      setLoading(false);
    }
  };

  // 4. EXCLUIR VIAGEM
  const excluirViagem = async (id: number) => {
    if (!confirm("Deseja realmente excluir esta viagem?")) return;
    try {
      await api.delete(`/viagens/${id}`);
      carregarViagens();
    } catch (e) {
      alert("Erro ao excluir.");
    }
  };

  const resetForm = () => {
    setFormData({ veiculoId: 1, origem: "", destino: "", kmPercorrida: 0, dataSaida: "", dataChegada: "" });
    setEditMode(false);
    setSelectedId(null);
  };

  return (
    <div className="p-8 min-h-screen bg-[#f4f7f6]">
      <div className="max-w-5xl mx-auto space-y-8">
        
        {/* FORMULÁRIO (CRIAR/EDITAR) */}
        <motion.div layout className="bg-white p-6 rounded-2xl shadow-sm border border-gray-200">
          <div className="flex justify-between items-center mb-6">
            <h2 className="text-lg font-bold text-gray-800">
              {editMode ? `Editando Viagem #${selectedId}` : "Nova Viagem"}
            </h2>
            {editMode && (
              <button onClick={resetForm} className="text-gray-400 hover:text-red-500 transition-colors">
                <X size={20} />
              </button>
            )}
          </div>

          <form onSubmit={handleSubmit} className="grid grid-cols-1 md:grid-cols-3 gap-4">
            <div className="md:col-span-1">
              <label className="label-style">Veículo</label>
              <select 
                className="input-style"
                value={formData.veiculoId}
                onChange={(e) => setFormData({...formData, veiculoId: Number(e.target.value)})}
              >
                {VEICULOS_DISPONIVEIS.map(v => <option key={v.id} value={v.id}>{v.nome}</option>)}
              </select>
            </div>
            <div>
              <label className="label-style">Origem</label>
              <input type="text" className="input-style" value={formData.origem} onChange={e => setFormData({...formData, origem: e.target.value})} required />
            </div>
            <div>
              <label className="label-style">Destino</label>
              <input type="text" className="input-style" value={formData.destino} onChange={e => setFormData({...formData, destino: e.target.value})} required />
            </div>
            <div>
              <label className="label-style">KM</label>
              <input type="number" className="input-style" value={formData.kmPercorrida} onChange={e => setFormData({...formData, kmPercorrida: Number(e.target.value)})} required />
            </div>
            <div>
              <label className="label-style">Saída</label>
              <input type="datetime-local" className="input-style" value={formData.dataSaida} onChange={e => setFormData({...formData, dataSaida: e.target.value})} required />
            </div>
            <div>
              <label className="label-style">Chegada</label>
              <input type="datetime-local" className="input-style" value={formData.dataChegada} onChange={e => setFormData({...formData, dataChegada: e.target.value})} required />
            </div>

            <button type="submit" disabled={loading} className={`md:col-span-3 py-3 rounded-xl font-bold text-white transition-all ${editMode ? 'bg-amber-500' : 'bg-indigo-600'}`}>
              {loading ? "Salvando..." : editMode ? "Atualizar Viagem" : "Cadastrar Viagem"}
            </button>
          </form>
        </motion.div>

        {/* LISTAGEM DE VIAGENS */}
        <div className="bg-white rounded-2xl shadow-sm border border-gray-200 overflow-hidden">
          <div className="p-5 bg-gray-50/50 border-b border-gray-100 flex justify-between items-center">
            <h2 className="text-[10px] uppercase font-bold text-gray-400">Histórico de Viagens</h2>
            <span className="text-xs text-gray-400">{viagens.length} viagens encontradas</span>
          </div>
          
          <table className="w-full text-left">
            <thead className="text-[10px] uppercase text-gray-400 border-b border-gray-100">
              <tr>
                <th className="px-6 py-3">Viagem</th>
                <th className="px-6 py-3">Veículo</th>
                <th className="px-6 py-3">Rota</th>
                <th className="px-6 py-3">Distância</th>
                <th className="px-6 py-3 text-right">Ações</th>
              </tr>
            </thead>
            <tbody className="divide-y divide-gray-50">
              <AnimatePresence>
                {viagens.map((v) => (
                  <motion.tr 
                    key={v.id} 
                    initial={{ opacity: 0 }} 
                    animate={{ opacity: 1 }} 
                    exit={{ opacity: 0, x: -20 }}
                    className="hover:bg-gray-50/50 transition-colors text-sm"
                  >
                    <td className="px-6 py-4 font-mono text-xs text-indigo-600 font-bold">#{v.id}</td>
                    <td className="px-6 py-4">
                      <div className="font-medium text-gray-700">{v.veiculo.modelo}</div>
                      <div className="text-[10px] text-gray-400">{v.veiculo.placa}</div>
                    </td>
                    <td className="px-6 py-4 text-gray-600">
                      {v.origem} → {v.destino}
                    </td>
                    <td className="px-6 py-4 font-semibold text-gray-800">{v.kmPercorrida} km</td>
                    <td className="px-6 py-4 text-right space-x-2">
                      <button onClick={() => selecionarParaEdicao(v.id)} className="p-2 text-amber-500 hover:bg-amber-50 rounded-lg transition-colors">
                        <Edit3 size={16} />
                      </button>
                      <button onClick={() => excluirViagem(v.id)} className="p-2 text-red-500 hover:bg-red-50 rounded-lg transition-colors">
                        <Trash2 size={16} />
                      </button>
                    </td>
                  </motion.tr>
                ))}
              </AnimatePresence>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}