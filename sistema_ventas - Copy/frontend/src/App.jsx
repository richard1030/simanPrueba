import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import ProductoCrud from './components/ProductoCrud';
import ClienteLista from './components/ClienteLista';
import EstadisticasVentas from './pages/ventas/EstadisticasVentas';

function App() {
  return (
    <Router>
      <div className="p-4">
        {/* Menú de navegación */}
        <div className="mb-6">
          <Link to="/" className="p-button p-button-text mr-4">Productos</Link>
          <Link to="/clientes" className="p-button p-button-text mr-4">Clientes</Link>
          <Link to="/ventas/estadisticas" className="p-button p-button-text">Estadísticas de Ventas</Link>
        </div>

        {/* Rutas de la aplicación */}
        <Routes>
          <Route path="/" element={<div><h2 className="text-xl mb-3">Gestión de Productos</h2><ProductoCrud /></div>} />
          <Route path="/clientes" element={<div><h2 className="text-xl mt-6 mb-3">Listado de Clientes</h2><ClienteLista /></div>} />
          <Route path="/ventas/estadisticas" element={<EstadisticasVentas />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
