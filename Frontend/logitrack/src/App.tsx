import { BrowserRouter, Routes, Route } from "react-router-dom";
import Dashboard from "./pages/Dashboard";
import Viagem from "./pages/Viagem";

import Navbar from "./components/Navbar";



export default function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={<Dashboard />} />
        <Route path="/viagens" element={<Viagem/>} />
      </Routes>
    </BrowserRouter>
  );
}