import React, { useState, useEffect } from 'react';
import { Link, useNavigate   } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import { fetchAreas, fetchPostPersona } from '../controllers/UsuarioController';
import LoadingOverlay from './LoadingOverlay';

const RegistrationForm = () => {
    const [areas, setAreas] = useState([]);
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);
    const navigate  = useNavigate();

    const [formData, setFormData] = useState({
        id: '',
        identificacion: '',
        nombre: '',
        correo: '',
        area: '',
        clave: '',
        administrador: false,
      });

      const handleChange = (e) => {
        const { name, value, type, checked } = e.target;
      
        if (type === 'checkbox') {
          setFormData((prevData) => ({
            ...prevData,
            [name]: checked,
          }));
        } else {
          setFormData((prevData) => ({
            ...prevData,
            [name]: value,
          }));
        }
      };
      
      const handleSelectChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({
          ...prevData,
          [name]: value,
        }));
      };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        const data = await fetchPostPersona(formData);
        if (data.success){
            setError(null);
            navigate('/', { state: { successMessage: 'Registro exitoso' } });
        }
        else{
            setError(data.error);
        }

        setLoading(false);
      };
  
    const fetchAreasFromApi = async () => {
        setLoading(true);
        const data = await fetchAreas();
        if (data.success){
            setError(null);
            setAreas(data.respuesta);
        }
        else{
            setError(data.error);
        }

        setLoading(false);
    };
  
    useEffect(() => {
      fetchAreasFromApi();
    }, []);

  return (
    <div className="container">
        {loading && <LoadingOverlay />}
      <div className="form-container form-register">
        <h2>Registro / Edición</h2>
        <form onSubmit={handleSubmit}>
            <div className="row">
            <div className="mb-3 col-12 col-md-6">
            <label htmlFor="id" className="form-label">ID de Persona:</label>
            <input type="text" className="form-control" id="id" name="id" maxLength="10" onChange={handleChange} value={formData.id} required />
          </div>
          <div className="mb-3 col-12 col-md-6">
            <label htmlFor="identificacion" className="form-label">Identificación:</label>
            <input type="number" className="form-control" id="identificacion" name="identificacion" onChange={handleChange} value={formData.identificacion} required />
          </div>
          <div className="mb-3 col-12 col-md-6">
            <label htmlFor="nombre" className="form-label">Nombre:</label>
            <input type="text" className="form-control" id="nombre" name="nombre" maxLength="50" onChange={handleChange} value={formData.nombre} required />
          </div>
          <div className="mb-3 col-12 col-md-6">
            <label htmlFor="correo" className="form-label">Correo:</label>
            <input type="email" className="form-control" id="correo" name="correo" maxLength="255" onChange={handleChange} value={formData.correo} />
          </div>
          <div className="mb-3 col-12 col-md-6">
            <label htmlFor="area" className="form-label">Área:</label>
            <select className="form-select" id="area" name="area" required onChange={handleSelectChange} value={formData.area}>
                <option value="" disabled>Seleccione un área</option>
                {areas && areas.length > 0 ? (areas.map(area => (
                    <option key={area.codigo} value={area.codigo}>{area.descripcion}</option>
                ))) : ("")}
            </select>
          </div>
          <div className="mb-3 col-12 col-md-6">
            <label htmlFor="clave" className="form-label">Contraseña:</label>
            <input type="password" className="form-control" id="clave" name="clave" maxLength="255" minLength="8" onChange={handleChange} value={formData.clave} required />
          </div>
          <div className="mb-2 mt-2 col-12 d-flex justify-content-center">
            <input type="checkbox" className="form-check-input" id="administrador" name="administrador" onChange={handleChange} value={formData.administrador} />
            <label className="form-check-label" htmlFor="administrador">¿Administrador?</label>
          </div>
          <div className="mb-3 d-grid gap-2 d-flex justify-content-center">
            <button type="submit" className="btn btn-primary">Registrarse</button>
            <Link to="/" className="btn btn-secondary">Volver</Link>
          </div>
            </div>
        </form>
      </div>
      {error && <div className="error-message">{error}</div>}
    </div>
  );
};

export default RegistrationForm;
