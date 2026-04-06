import { Link } from "react-router-dom";

export default function Navbar() {
  return (
    <nav style={{ padding: 10, background: '#eee' }}>
      <Link to="/">Dashboard</Link> | 
      <Link to="/viagens">Viagens</Link>
    </nav>
  );
}