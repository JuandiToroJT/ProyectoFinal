import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import { fetchLoginPersona } from '../controllers/UsuarioController';
import LoadingOverlay from './LoadingOverlay';

const LoginForm = () => {
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(null);
  const [loading, setLoading] = useState(false);

  const [userId, setUserId] = useState('');
  const [password, setPassword] = useState('');

  const location = useLocation();
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    setLoading(true);
    const data = await fetchLoginPersona(userId, password);
    if (data.success){
        setError(null);
        if (data.respuesta.numeroRegistro === 0){
          localStorage.setItem('userId', userId);
          navigate('/listado-amenazas');
        }
        else if (data.respuesta.numeroRegistro === 1){
          localStorage.setItem('userId', userId);
          navigate('/listado-usuarios');
        }
        else{
          setError('Rol no reconocido en la respuesta de la API');
        }
    }
    else{
        setError(data.error);
    }

    setLoading(false);
  };

  useEffect(() => {
    localStorage.removeItem('userId');
    const successMessage = location.state && location.state.successMessage;
    if (successMessage != null && successMessage !== "") {
      setSuccess(successMessage);
      setTimeout(() => {
        setSuccess(null);
      }, 5000);

      navigate('.', { state: { ...location.state, successMessage: undefined }, replace: true });
    }
  }, [location, location.state, navigate]);

  return (
    <div className="container">
      {loading && <LoadingOverlay />}
      <div className="form-container form-login">
        <h2>SOC - Centro de Operaciones de Seguridad</h2>
        <form onSubmit={handleLogin}>
          <div className="mb-3">
            <label htmlFor="userId" className="form-label">ID de Persona:</label>
            <input type="text" className="form-control" id="userId" name="userId" maxLength="10" value={userId} onChange={(e) => setUserId(e.target.value)} required />
          </div>
          <div className="mb-3">
            <label htmlFor="password" className="form-label">Contraseña:</label>
            <input type="password" className="form-control" id="password" name="password" maxLength="255" minLength="8" value={password} onChange={(e) => setPassword(e.target.value)} required />
          </div>
          <div className="mb-3 d-grid gap-2">
            <button type="submit" className="btn btn-primary">Iniciar Sesión</button>
            <Link to="/registro" className="btn btn-secondary">Registrarse</Link>
          </div>
        </form>
      </div>
      {error && <div className="error-message">{error}</div>}
      {success && <div className="success-message">{success}</div>}
    </div>
  );
};

export default LoginForm;
