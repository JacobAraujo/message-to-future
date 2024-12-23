import React from 'react';
import { BrowserRouter } from 'react-router-dom';
import AppRoutes from './routes/AppRoutes';
import { AuthProvider } from './context/AuthContext';
import './assets/styles/index.css';

function App() {
  return (
    <AuthProvider>
        <BrowserRouter>
          <AppRoutes />
        </BrowserRouter>
    </AuthProvider>
  );
}

export default App;
