import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useLocation } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import { fetchAmenazas } from '../controllers/AmenazaController';
import LoadingOverlay from './LoadingOverlay';

const ListadoAmenazas = () => {
    const [error, setError] = useState(null);
    const [success, setSuccess] = useState(null);
    const [loading, setLoading] = useState(false);

    const location = useLocation();
    const navigate = useNavigate();

    const [amenazas, setAmenazas] = useState([]);

    const fetchAmenazasFromApi = async () => {
        setLoading(true);
        const data = await fetchAmenazas();
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
        fetchAmenazasFromApi();
        const successMessage = location.state && location.state.successMessage;
        if (successMessage != null && successMessage !== "") {
        setSuccess(successMessage);
        setTimeout(() => {
            setSuccess(null);
        }, 5000);

        navigate('.', { state: { ...location.state, successMessage: undefined }, replace: true });
        }
      }, [location.state, navigate, location]);

      return (
        <div className="container">
            {loading && <LoadingOverlay />}
      <div className="form-container form-list">
        <h2>Listado de Amenazas</h2>
        <table className="table">
          <thead>
            <tr>
              <th scope="col">ID</th>
              <th scope="col">Nombre</th>
              <th scope="col">Descripción</th>
              <th scope="col">Usuario</th>
            </tr>
          </thead>
          <tbody>
            {amenazas && amenazas.length > 0 ? (amenazas.map((amenaza) => (
              <tr key={amenaza.id}>
                <td>{amenaza.id}</td>
                <td>{amenaza.nombre}</td>
                <td>{amenaza.descripcion}</td>
                <td>{amenaza.usuario}</td>
              </tr>
            ))) : ("")}
          </tbody>
        </table>
        <div className='row'>
        <div className="mb-3 d-grid gap-2 d-flex justify-content-center">
            <Link to="/reportar" className="btn btn-primary">Reportar amenaza</Link>
            <Link to="/" className="btn btn-secondary">Cerrar sesión</Link>
          </div>
        </div>
      </div>
      {error && <div className="error-message">{error}</div>}
      {success && <div className="success-message">{success}</div>}
    </div>
      );
};

export default ListadoAmenazas;