// url quedama para pruebas dev
const API_URL = 'http://localhost:8080/api/productos';

export async function obtenerProductos(auth) {
  const res = await fetch(API_URL, {
    headers: {
      'Authorization': 'Basic ' + btoa(auth.usuario + ':' + auth.clave),
    },
  });
  return res.json();
}

export async function crearProducto(producto, auth) {
  return await fetch(API_URL, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': 'Basic ' + btoa(auth.usuario + ':' + auth.clave),
    },
    body: JSON.stringify(producto),
  });
}

export async function actualizarProducto(id, producto, auth) {
    return await fetch(`${API_URL}/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + btoa(auth.usuario + ':' + auth.clave),
      },
      body: JSON.stringify(producto),
    });
  }
  
  export async function eliminarProducto(id, auth) {
    return await fetch(`${API_URL}/${id}`, {
      method: 'DELETE',
      headers: {
        'Authorization': 'Basic ' + btoa(auth.usuario + ':' + auth.clave),
      },
    });
  }