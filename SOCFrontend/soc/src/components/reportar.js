import React, { useState } from 'react';
import { Link, useNavigate   } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import { fetchPostAmenaza } from '../controllers/AmenazaController';
import LoadingOverlay from './LoadingOverlay';

const Reportar = () => {
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);
    const navigate  = useNavigate();

    const [formData, setFormData] = useState({
        nombre: '',
        descripcion: ''
      });

      const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: value,
          }));
      };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        const userId = localStorage.getItem('userId');
        const data = await fetchPostAmenaza(userId, formData);
        if (data.success){
            setError(null);
            navigate('/listado-amenazas', { state: { successMessage: 'Amenaza reportada' } });
        }
        else{
            setError(data.error);
        }

        setLoading(false);
      };

  return (
    <div className="container">
        {loading && <LoadingOverlay />}
      <div className="form-container form-register">
        <h2>Reportar amenaza</h2>
        <form onSubmit={handleSubmit}>
            <div className="row">
            <div className="mb-3 col-12">
            <label htmlFor="nombre" className="form-label">Nombre de la amenaza:</label>
            <input type="text" className="form-control" id="nombre" name="nombre" maxLength="100" onChange={handleChange} value={formData.nombre} required />
          </div>
          <div className="mb-3 col-12">
            <label htmlFor="descripcion" className="form-label">Descripción de la amenaza:</label>
            <textarea
                className="form-control"
                id="descripcion"
                name="descripcion"
                onChange={handleChange}
                value={formData.descripcion}
                required
            ></textarea>
            </div>
          <div className="mb-3 d-grid gap-2 d-flex justify-content-center">
            <button type="submit" className="btn btn-primary">Reportar</button>
            <Link to="/listado-amenazas" className="btn btn-secondary">Volver</Link>
          </div>
            </div>
        </form>
      </div>
      {error && <div className="error-message">{error}</div>}
    </div>
  );
};

export default Reportar;