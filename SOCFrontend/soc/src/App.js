import React, { useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LoginForm from './components/LoginForm';
import RegistrationForm from './components/RegistrationForm';
import ListadoAmenazas from './components/listado-amenazas';
import ListadoUsuarios from './components/listado-usuarios';
import Reportar from './components/reportar';
import Analizar from './components/analizar';
import './App.css';

const App = () => {
  useEffect(() => {
    document.title = 'SOC';
  }, []);

  return (
    <Router>
      <Routes>
        <Route path="/analizar" element={<Analizar />} />
        <Route path="/reportar" element={<Reportar />} />
        <Route path="/listado-amenazas" element={<ListadoAmenazas />} />
        <Route path="/listado-usuarios" element={<ListadoUsuarios />} />
        <Route path="/registro" element={<RegistrationForm />} />
        <Route path="/" element={<LoginForm />} />
      </Routes>
    </Router>
  );
}

export default App;
