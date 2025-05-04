import React, { useEffect, useState } from 'react';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { InputText } from 'primereact/inputtext';
import { Button } from 'primereact/button';
import { obtenerClientesFiltrados,exportarClientesExcel  } from '../services/clienteService';

function ClienteLista() {
    const [clientes, setClientes] = useState([]);
    const [filtroGlobal, setFiltroGlobal] = useState('');
    const [totalRegistros, setTotalRegistros] = useState(0);
    const [paginacion, setPaginacion] = useState({ page: 0, rows: 10 });
    const [orden, setOrden] = useState({ field: null, order: null });
    const [filtros, setFiltros] = useState({
        global: { value: null, matchMode: 'contains' },
        nombre: { value: null, matchMode: 'contains' },
        correo: { value: null, matchMode: 'contains' },
      });
  

    /* useEffect(() => {
        if (filtroGlobal && filtroGlobal.trim() !== '') {
          buscar();
        } else {
          cargarClientes(paginacion.page, paginacion.rows);
        }
      }, [paginacion, filtroGlobal, orden, filtros]); */
           
      useEffect(() => {
        const cargarClientes = async () => {
          try {
            // solo para desarrollo
            const auth = { usuario: 'admin', clave: 'admin123' };
            // Usar filtro global para los filtros de nombre y correo
            const nombre = filtroGlobal ? filtroGlobal : filtros.nombre?.value || '';
            const correo = filtroGlobal ? filtroGlobal : filtros.correo?.value || '';
      
            const data = await obtenerClientesFiltrados(
              auth,
              paginacion.page,
              paginacion.rows,
              orden.field,
              orden.order,
              nombre,
              correo
            );
            setClientes(data.content);
            setTotalRegistros(data.totalElements);
          } catch (error) {
            console.error("Error al obtener clientes", error);
          }
        };
      
        cargarClientes();
      }, [filtroGlobal, paginacion, orden, filtros]);

    /*  const buscar = async () => {
        try {
          const auth = { usuario: 'admin', clave: 'admin123' };
          const nombre = filtros.nombre?.value || '';
          const correo = filtros.correo?.value || '';
          const data = await buscarClientes(
            auth,
            nombre,
            correo,
            paginacion.page,
            paginacion.rows,
            orden.field,
            orden.order
          );
          setClientes(data.content);
          setTotalRegistros(data.totalElements);
        } catch (error) {
          console.error("Error al buscar clientes", error);
        }
      }; */

      const onSort = (e) => {
        setOrden({ field: e.sortField, order: e.sortOrder });
      };
    
      const onFilter = (e) => {
        
        // Actualizar el filtro global con los valores de filtros específicos si es necesario
        if (e.filters.nombre?.value || e.filters.correo?.value) {
          setFiltroGlobal(''); // Limpiar filtro global si se usa filtro específico
        }

        setFiltros(e.filters);
      };

      const exportarExcel = async () => {
        try {
          const auth = { usuario: 'admin', clave: 'admin123' };
          const blob = await exportarClientesExcel(auth);
      
          const url = window.URL.createObjectURL(blob);
          const link = document.createElement('a');
          link.href = url;
          link.download = 'clientes.xlsx';
          document.body.appendChild(link);
          link.click();
          link.remove();
          window.URL.revokeObjectURL(url);
        } catch (error) {
          console.error('Error al exportar clientes:', error);
        }
      };
      
      
      return (
        <div className="p-4">
          <h2 className="text-xl mb-3">Listado de Clientes</h2>
    
          <span className="p-input-icon-left mb-3">
            <i className="pi pi-search" />
            <InputText
              value={filtroGlobal}
              onChange={(e) => setFiltroGlobal(e.target.value)}
              placeholder="Buscar por nombre o correo"
            />
            <Button label="Buscar" onClick={() => setFiltroGlobal(filtroGlobal)} className="ml-2" />

          </span>
          <div className="mb-3">
          <Button
            label="Exportar a Excel"
            icon="pi pi-file-excel"
            className="p-button-success"
            onClick={exportarExcel}
          />
        </div>
          <DataTable
            value={clientes}
            paginator
            rows={paginacion.rows}
            totalRecords={totalRegistros}
            lazy
            first={paginacion.page * paginacion.rows}
            onPage={(e) => setPaginacion({ page: e.page, rows: e.rows })}
            filters={filtros}
            onFilter={onFilter}
            onSort={onSort}
            sortField={orden.field}
            sortOrder={orden.order}
            globalFilterFields={['nombre', 'correo']}
          >
            <Column field="id" header="ID" sortable />
            <Column field="nombre" header="Nombre" sortable filter />
            <Column field="correo" header="Correo" sortable filter />
          </DataTable>
        </div>
      );
    }

export default ClienteLista;