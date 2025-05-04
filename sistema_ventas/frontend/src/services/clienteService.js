//url quemada prueba
const API_URL = 'http://localhost:8080/api/clientes';

/* export async function obtenerClientes(auth) {
    const headers = new Headers();
    headers.append('Authorization', 'Basic ' + btoa(`${auth.usuario}:${auth.clave}`));
  
    const response = await fetch(API_URL, { headers });
  
    if (!response.ok) {
      throw new Error('Error al obtener los clientes');
    }
  
    return await response.json();
  }

  export async function obtenerClientesPaginados(auth, page, size, sortField, sortOrder) {
    try {
      const url = `${API_URL}?page=${page}&size=${size}&sort=${sortField},${sortOrder}`;
      
      const response = await fetch(url, {
        method: 'GET',
        headers: {
          'Authorization': 'Basic ' + btoa(auth.usuario + ':' + auth.clave)
        }
      });
  
      if (!response.ok) {
        throw new Error('Error al obtener clientes');
      }
  
      return await response.json();
    } catch (error) {
      console.error('Error al obtener clientes paginados:', error);
      throw error;
    }
  }

  export async function buscarClientes(auth, nombre, correo, page, size, sortField, sortOrder) {
    try {
      // Preparamos la URL con los parámetros
      const url = `${API_URL}?nombre=${nombre}&correo=${correo}&page=${page}&size=${size}&sort=${sortField},${sortOrder}`;
  
      // Hacemos la solicitud GET al backend con la autorización
      const response = await fetch(url, {
        method: 'GET',
        headers: {
          'Authorization': 'Basic ' + btoa(auth.usuario + ':' + auth.clave)
        }
      });
  
      if (!response.ok) {
        throw new Error('Error al obtener clientes');
      }
  
      // Retornamos la respuesta como JSON
      return await response.json();
    } catch (error) {
      console.error('Error en la solicitud de clientes:', error);
      throw error;
    } 
  } */

    export async function obtenerClientesFiltrados(auth, page, size, sortField, sortOrder, nombre, correo) {
        const params = new URLSearchParams();
        params.append('page', page);
        params.append('size', size);
        if (sortField && sortOrder) {
          params.append('sortField', sortField);
          params.append('sortOrder', sortOrder === 1 ? 'asc' : 'desc');
        }
        if (nombre) params.append('nombre', nombre);
        if (correo) params.append('correo', correo);
      
        const response = await fetch(`${API_URL}?${params.toString()}`, {
          headers: {
            'Authorization': 'Basic ' + btoa(`${auth.usuario}:${auth.clave}`)
          }
        });
      
        if (!response.ok) {
          throw new Error('Error al obtener los clientes');
        }
      
        return await response.json();
      }
      
      export async function exportarClientesExcel(auth) {
        try {
          const response = await fetch(`${API_URL}/exportar`, {
            method: 'GET',
            headers: {
              'Authorization': 'Basic ' + btoa(`${auth.usuario}:${auth.clave}`)
            }
          });
      
          if (!response.ok) {
            throw new Error('Error al exportar clientes');
          }
      
          return await response.blob(); // devolvemos el blob para que el frontend lo maneje
        } catch (error) {
          console.error('Error en exportarClientesExcel:', error);
          throw error;
        }
      }
      
  