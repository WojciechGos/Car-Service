
import './App.css';

import { Route, Routes, Navigate } from "react-router-dom"

import PATH from './paths';
import Login from './pages/Authentication/Login';
import Client from './pages/Client';
import Warehouse from './pages/Warehouse';
import Commission from './pages/Commission';

import 'bootstrap/dist/css/bootstrap.css';
function App() {
  return (
    <Routes>
      <Route index path={PATH.LOGIN} element={<Login />} />
      <Route path={PATH.CLIENT} element={<Client />} />
      <Route path={PATH.WAREHOUSE} element={<Warehouse/>} />
      <Route path={PATH.COMMISSION} element={<Commission/>}/>


      <Route path="*" element={<Navigate to={PATH.LOGIN} replace />} />
    </Routes>
  )
}

export default App;
