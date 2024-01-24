
import './App.css';

import { Route, Routes, Navigate } from "react-router-dom"

import PATH from './paths';
import Login from './pages/Authentication/Login';
import Client from './pages/Client';
import Warehouse from './pages/Warehouse';
import Commission from './pages/Commission';
import Vehicle from './pages/Vehicle';
import Order from './pages/Order';
import ClientAdd from './pages/ClientAdd';
import Details from './pages/Details';
import Invoice from './pages/Invoice';
import CartWarehouse from './pages/CartWarehouse';

import 'bootstrap/dist/css/bootstrap.css';

function App() {
  return (
    <Routes>
      <Route index path={PATH.LOGIN} element={<Login />} />
      <Route path={PATH.CLIENT} element={<Client />} />
      <Route path={PATH.CLIENTADD} element={<ClientAdd/>}/>
      <Route path={PATH.WAREHOUSE} element={<Warehouse/>} />
      <Route path={PATH.COMMISSION} element={<Commission/>}/>
      <Route path={PATH.VEHICLE} element={<Vehicle/>}/>
      <Route path={PATH.ORDER} element={<Order/>}/>
      <Route path={PATH.CARTWAREHOUSE} element={<CartWarehouse/>}/>
      <Route path={`${PATH.DETAILS}/:id`} element={<Details/>}/>
      <Route path={`${PATH.INVOICE}/:type/:id`} element={<Invoice/>}/>

      <Route path="*" element={<Navigate to={PATH.LOGIN} replace />} />
    </Routes>
  )
}

export default App;
