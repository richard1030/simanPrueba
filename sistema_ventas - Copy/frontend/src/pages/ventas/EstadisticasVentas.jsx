import React, { useEffect, useState } from 'react';
import {
  obtenerTopProductos,
  obtenerClienteMayorIngreso,
  obtenerIngresoUltimoMes,
} from '../../services/ventaService';

function EstadisticasVentas() {
  const [topProductos, setTopProductos] = useState([]);
  const [clienteMayorIngreso, setClienteMayorIngreso] = useState(null);
  const [ingresoUltimoMes, setIngresoUltimoMes] = useState(null);

  // Solo para pruebas (deberías usar AuthContext en producción)
  const auth = { usuario: 'admin', clave: 'admin123' };

  useEffect(() => {
    async function fetchData() {
      try {
        const productos = await obtenerTopProductos(auth);
        setTopProductos(productos);
      } catch (error) {
        console.error('Error al obtener productos más vendidos:', error);
      }

      try {
        const cliente = await obtenerClienteMayorIngreso(auth);
        setClienteMayorIngreso(cliente);
      } catch (error) {
        console.error('Error al obtener cliente con mayor ingreso:', error);
      }

      try {
        const ingreso = await obtenerIngresoUltimoMes(auth);
        setIngresoUltimoMes(ingreso);
      } catch (error) {
        console.error('Error al obtener ingreso del último mes:', error);
      }
    }

    fetchData();
  }, []);

  const formatMonto = (valor) => {
    return typeof valor === 'number' ? valor.toFixed(2) : '0.00';
  };

  return (
    <div className="p-4">
      <h2 className="text-2xl font-bold mb-4">Estadísticas de Ventas</h2>

      <div className="mb-6">
        <h3 className="text-xl font-semibold">Top 3 Productos Más Vendidos</h3>
        {topProductos.length > 0 ? (
          <ul className="list-disc ml-5">
            {topProductos.map((producto, index) => (
              <li key={index}>
                {producto.nombre} - {producto.totalCantidad} unidades
              </li>
            ))}
          </ul>
        ) : (
          <p>No se pudo obtener la información.</p>
        )}
      </div>

      <div className="mb-6">
        <h3 className="text-xl font-semibold">Cliente con Mayor Ingreso Generado</h3>
        {clienteMayorIngreso ? (
          <p>
            {clienteMayorIngreso.nombre} {clienteMayorIngreso.apellido} - ${formatMonto(clienteMayorIngreso.totalComprado)}
          </p>
        ) : (
          <p>No se pudo obtener la información.</p>
        )}
      </div>

      <div className="mb-6">
        <h3 className="text-xl font-semibold">Ingreso Total del Último Mes</h3>
        <p>${formatMonto(ingresoUltimoMes)}</p>
      </div>
    </div>
  );
}

export default EstadisticasVentas;
