
import './App.css';

import { Route, Routes, Navigate } from "react-router-dom"

import PATH from './paths';
import Login from './pages/Authentication/Login';
import Client from './pages/Client';
import Warehouse from './pages/Warehouse';
import Commission from './pages/Commission';
import CommissionAdd from './pages/CommissionAdd';
import Vehicle from './pages/Vehicle';
import Order from './pages/Order';
import ClientAdd from './pages/ClientAdd';
import Wholesalers from './pages/Wholesalers';
import IPART from './pages/IPART';
import StarHurt from './pages/StarHurt';
import SalersOrders from './pages/SalersOrders';
import Cart from './pages/Cart';
import Details from './pages/Details';
import Invoice from './pages/Invoice';

import 'bootstrap/dist/css/bootstrap.css';

function App() {
  return (
    <Routes>
      <Route index path={PATH.LOGIN} element={<Login />} />
      <Route path={PATH.CLIENT} element={<Client />} />
      <Route path={PATH.CLIENTADD} element={<ClientAdd/>}/>
      <Route path={PATH.WAREHOUSE} element={<Warehouse/>} />
      <Route path={PATH.COMMISSION} element={<Commission/>}/>
      <Route path={PATH.COMMISSIONADD} element={<CommissionAdd />}/>
      <Route path={PATH.VEHICLE} element={<Vehicle/>}/>
      <Route path={PATH.ORDER} element={<Order/>}/>
      <Route path={PATH.WHOLESALERS} element={<Wholesalers/>}/>
      <Route path={PATH.IPART} element={<IPART/>}/>
      <Route path={PATH.STARHURT} element={<StarHurt/>}/>
      <Route path={PATH.SALERSORDERS} element={<SalersOrders/>}/>
      <Route path={PATH.CART} element={<Cart/>}/>
      <Route path={`${PATH.DETAILS}/:id`} element={<Details/>}/>
      <Route path={`${PATH.INVOICE}/:type/:id`} element={<Invoice/>}/>

      <Route path="*" element={<Navigate to={PATH.LOGIN} replace />} />
    </Routes>
  )
}

export default App;
