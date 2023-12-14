import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import { fetchUsuarios } from '../controllers/UsuarioController';
import LoadingOverlay from './LoadingOverlay';

const ListadoUsuarios = () => {
    const [error, setError] = useState(null);
    const [success, setSuccess] = useState(null);
    const [loading, setLoading] = useState(false);

    const [usuarios, setUsuarios] = useState([]);

    const fetchUsuariosFromApi = async () => {
        setLoading(true);
        const userId = localStorage.getItem('userId');
        const data = await fetchUsuarios(userId);
        if (data.success){
            setError(null);
            setUsuarios(data.respuesta);
        }
        else{
            setError(data.error);
        }

        setLoading(false);
    };

    useEffect(() => {
        fetchUsuariosFromApi();
      }, []);

      return (
        <div className="container">
            {loading && <LoadingOverlay />}
      <div className="form-container form-list">
        <h2>Listado de Usuarios</h2>
        <table className="table">
          <thead>
            <tr>
              <th scope="col">ID</th>
              <th scope="col">Identificación</th>
              <th scope="col">Nombre</th>
              <th scope="col">Correo</th>
              <th scope="col">Área</th>
            </tr>
          </thead>
          <tbody>
            {usuarios && usuarios.length > 0 ? (usuarios.map((usuario) => (
              <tr key={usuario.id}>
                <td>{usuario.id}</td>
                <td>{usuario.identificacion}</td>
                <td>{usuario.nombre}</td>
                <td>{usuario.correo}</td>
                <td>{usuario.nombreArea}</td>
              </tr>
            ))) : ("")}
          </tbody>
        </table>
        <div className='row'>
        <div className="mb-3 d-grid gap-2 d-flex justify-content-center">
            <Link to="/analizar" className="btn btn-primary">Analizar log</Link>
            <Link to="/" className="btn btn-secondary">Cerrar sesión</Link>
          </div>
        </div>
      </div>
      {error && <div className="error-message">{error}</div>}
      {success && <div className="success-message">{success}</div>}
    </div>
      );
};

export default ListadoUsuarios;