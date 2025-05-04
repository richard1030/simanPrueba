
//IMPORTS DE REACT
import 'primereact/resources/themes/saga-blue/theme.css';      // Tema de PrimeReact (puedes cambiarlo)
import 'primereact/resources/primereact.min.css';              // Estilos base de PrimeReact
import 'primeicons/primeicons.css';                            // Iconos
import 'primeflex/primeflex.css';                              // Utilidades de diseño


import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App.jsx';
import reportWebVitals from './reportWebVitals';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
