const API_URL = 'http://localhost:8080/amenaza';

export const fetchAmenazas = async () => {
    try {
        const response = await fetch(`${API_URL}/listado`);
    
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
        console.error('Error al obtener las amenazas:', error);
        return { error: 'Error al obtener las amenazas', success: false };
      }
};

export const fetchPostAmenaza = async (idUsuario, body) => {
  try {
      const response = await fetch(`${API_URL}/${idUsuario}/reportar`, {
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
      console.error('Error al registrar la amenaza:', error);
      return { error: 'Error al registrar la amenaza', success: false };
    }
};

export const fetchAmenazasLog = async () => {
  try {
      const response = await fetch(`${API_URL}/analizarLog`);
  
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
      console.error('Error al obtener las amenazas:', error);
      return { error: 'Error al obtener las amenazas', success: false };
    }
};