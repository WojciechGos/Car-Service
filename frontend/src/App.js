
import './App.css';

import { Route, Routes, Navigate } from "react-router-dom"

import PATH from './paths';
import Login from './pages/Authentication/Login';
import Panel from './pages/Panel';

function App() {
  return (
    <Routes>
      <Route index path={PATH.LOGIN} element={<Login />} />
      <Route path={PATH.PANEL} element={<Panel />} />
      <Route path="*" element={<Navigate to={PATH.LOGIN} replace />} />
    </Routes>
  )
}

export default App;
