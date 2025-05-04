const API_URL = 'http://localhost:8080/api/ventas'; // ajusta si es otro puerto

export async function obtenerTopProductos(auth) {
  const res = await fetch(`${API_URL}/top-productos`, {
    headers: {
      'Authorization': 'Basic ' + btoa(auth.usuario + ':' + auth.clave),
    },
  });
  if (!res.ok) throw new Error('Error al obtener productos');
  return res.json();
}

export async function obtenerClienteMayorIngreso(auth) {
  const res = await fetch(`${API_URL}/mayor-cliente`, {
    headers: {
      'Authorization': 'Basic ' + btoa(auth.usuario + ':' + auth.clave),
    },
  });
  if (!res.ok) throw new Error('Error al obtener cliente');
  return res.json();
}

export async function obtenerIngresoUltimoMes(auth) {
  const res = await fetch(`${API_URL}/ingreso-ultimo-mes`, {
    headers: {
      'Authorization': 'Basic ' + btoa(auth.usuario + ':' + auth.clave),
    },
  });
  if (!res.ok) throw new Error('Error al obtener ingreso');
  return res.json();
}
