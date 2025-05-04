import React, { useEffect, useState } from 'react';
import { obtenerProductos, crearProducto, actualizarProducto, eliminarProducto } from '../services/productoService';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Button } from 'primereact/button';
import { InputText } from 'primereact/inputtext';

export default function ProductoCrud() {
  const [productos, setProductos] = useState([]);
  const [producto, setProducto] = useState({ nombre: '', precio: '', cantidadDisponible: '' });
  const [editando, setEditando] = useState(false);
  const [idEditando, setIdEditando] = useState(null);

  //solo para pruebas dev
  const auth = { usuario: 'admin', clave: 'admin123' };

  useEffect(() => {
    cargarProductos();
  }, []);

  const cargarProductos = async () => {
    const data = await obtenerProductos(auth);
    setProductos(data);
  };

  const guardarProducto = async () => {
    if (editando) {
      await actualizarProducto(idEditando, producto, auth);
    } else {
      await crearProducto(producto, auth);
    }
    setProducto({ nombre: '', precio: '', cantidadDisponible: '' });
    setEditando(false);
    cargarProductos();
  };

  const seleccionarProducto = (producto) => {
    setProducto(producto);
    setEditando(true);
    setIdEditando(producto.id);
  };

  const eliminar = async (id) => {
    await eliminarProducto(id, auth);
    cargarProductos();
  };

  const accionesTemplate = (rowData) => (
    <div className="flex gap-2">
      <Button icon="pi pi-pencil" rounded severity="warning" onClick={() => seleccionarProducto(rowData)} />
      <Button icon="pi pi-trash" rounded severity="danger" onClick={() => eliminar(rowData.id)} />
    </div>
  );

  return (
    <div className="p-4">
      <h2 className="text-xl mb-3">{editando ? 'Editar Producto' : 'Nuevo Producto'}</h2>
      <div className="flex gap-2 mb-3">
        <InputText placeholder="Nombre" value={producto.nombre}
          onChange={e => setProducto({ ...producto, nombre: e.target.value })} />
        <InputText placeholder="Precio" value={producto.precio}
          onChange={e => setProducto({ ...producto, precio: e.target.value })} />
        <InputText placeholder="CantidadDisponible" value={producto.cantidadDisponible}
          onChange={e => setProducto({ ...producto, cantidadDisponible: e.target.value })} />
        <Button label={editando ? 'Actualizar' : 'Guardar'} onClick={guardarProducto} />
      </div>

      <DataTable value={productos} tableStyle={{ minWidth: '50rem' }}>
        <Column field="id" header="ID"></Column>
        <Column field="nombre" header="Nombre"></Column>
        <Column field="precio" header="Precio"></Column>
        <Column field="cantidadDisponible" header="CantidadDisponible"></Column>
        <Column header="Acciones" body={accionesTemplate}></Column>
      </DataTable>
    </div>
  );
}
