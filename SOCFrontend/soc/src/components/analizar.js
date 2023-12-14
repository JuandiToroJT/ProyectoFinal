import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import { fetchAmenazasLog } from '../controllers/AmenazaController';
import LoadingOverlay from './LoadingOverlay';

const Analizar = () => {
    const [error, setError] = useState(null);
    const [success, setSuccess] = useState(null);
    const [loading, setLoading] = useState(false);

    const [amenazas, setAmenazas] = useState([]);

    const fetchAmenazasLogFromApi = async () => {
        setLoading(true);
        const data = await fetchAmenazasLog();
        if (data.success){
            setError(null);
            setAmenazas(data.respuesta);
        }
        else{
            setError(data.error);
        }

        setLoading(false);
    };

    useEffect(() => {
        fetchAmenazasLogFromApi();
      }, []);

      return (
        <div className="container">
            {loading && <LoadingOverlay />}
      <div className="form-container form-list">
        <h2>Listado de mitigaciones generadas por IA segun los ataques identificados en el Log </h2>
        <table className="table">
          <thead>
            <tr>
              <th scope="col">Nombre del Ataque</th>
              <th scope="col">Mitigación</th>
            </tr>
          </thead>
          <tbody>
            {amenazas && amenazas.length > 0 ? (amenazas.map((amenaza) => (
              <tr key={amenaza.nombre}>
                <td>{amenaza.nombre}</td>
                <td>{amenaza.descripcion}</td>
              </tr>
            ))) : ("")}
          </tbody>
        </table>
        <div className='row'>
        <div className="mb-3 d-grid gap-2 d-flex justify-content-center">
            <Link to="/listado-usuarios" className="btn btn-secondary">Volver</Link>
          </div>
        </div>
      </div>
      {error && <div className="error-message">{error}</div>}
      {success && <div className="success-message">{success}</div>}
    </div>
      );
};

export default Analizar;