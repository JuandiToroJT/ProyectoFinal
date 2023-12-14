const API_URL = 'http://localhost:8080/usuario';

export const fetchAreas = async () => {
    try {
        const response = await fetch(`${API_URL}/areas`);
    
        if (!response.ok && response.status !== 400) {
          const errorMessage = `Error en la solicitud: ${response.statusText}`;
          return { error: errorMessage, success: false };
        }
    
        const data = await response.json();
        if (response.status === 400){
            return { error: data.mensajeError, success: false };
        }

        return { respuesta: data, success: true };
      } catch (error) {
        console.error('Error al obtener áreas:', error);
        return { error: 'Error al obtener áreas', success: false };
      }
};

export const fetchPostPersona = async (body) => {
    try {
        const response = await fetch(`${API_URL}/persona/registrar`, {
            method: 'POST',
            headers: {
            'Content-Type': 'application/json',
            },
            body: JSON.stringify(body),
        });
    
        if (!response.ok && response.status !== 400) {
          const errorMessage = `Error en la solicitud: ${response.statusText}`;
          return { error: errorMessage, success: false };
        }
    
        const data = await response.json();
        if (response.status === 400){
            return { error: data.mensajeError, success: false };
        }

        return { respuesta: data, success: true };
      } catch (error) {
        console.error('Error al registrar la persona:', error);
        return { error: 'Error al registrar la persona', success: false };
      }
};

export const fetchLoginPersona = async (userId, password) => {
    try {
        const response = await fetch(`${API_URL}/persona/${userId}/${password}/login`, {
            method: 'POST'
        });
    
        if (!response.ok && response.status !== 400) {
          const errorMessage = `Error en la solicitud: ${response.statusText}`;
          return { error: errorMessage, success: false };
        }
    
        const data = await response.json();
        if (response.status === 400){
            return { error: data.mensajeError, success: false };
        }

        return { respuesta: data, success: true };
      } catch (error) {
        console.error('Error al ingresar:', error);
        return { error: 'Error al ingresar', success: false };
      }
};

export const fetchUsuarios = async (idAdmin) => {
    try {
        const response = await fetch(`${API_URL}/${idAdmin}/listado`);
    
        if (!response.ok && response.status !== 400) {
          const errorMessage = `Error en la solicitud: ${response.statusText}`;
          return { error: errorMessage, success: false };
        }
    
        const data = await response.json();
        if (response.status === 400){
            return { error: data.mensajeError, success: false };
        }

        return { respuesta: data, success: true };
      } catch (error) {
        console.error('Error al obtener usuarios:', error);
        return { error: 'Error al obtener usuarios', success: false };
      }
};