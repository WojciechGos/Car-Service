import Table from 'react-bootstrap/Table';
import React, { useState, useEffect } from "react";
import Cookies from "js-cookie";
import { jwtDecode } from 'jwt-decode';
import bin from "./trash-bin.png";
import order from "./order.png";

const TableCartWarehouse = ()=>{
    const [localOrder, setLocalOrder] = useState([]);
    const [commissionId, setCommissionId] = useState('');

    const inputButtonStyle = {
        justifyContent: 'center'
      };

    useEffect(() => {
        const workerEmail = getUserInfoFromToken()?.sub;
        if (workerEmail) {
          fetchLocalOrderByWorkerEmail(workerEmail);
        }
      }, []);

      const getUserInfoFromToken = () => {
        const token = Cookies.get('jwt');
      
        if (token) {
          try {
            const decodedToken = jwtDecode(token);
            return decodedToken;
          } catch (error) {
            console.error('Błąd dekodowania tokena:', error);
            return null;
          }
        }
      
        return null;
      };

      const fetchLocalOrderByWorkerEmail = async (workerEmail) => {
        try {
          const response = await fetch(`http://localhost:5001/api/v1/order/local/${workerEmail}`, {
            method: 'GET',
            headers: {
              'Authorization': `Bearer ${Cookies.get("jwt")}`,
            },
          });
      
          if (response.ok) {
            const data = await response.json();
            
            if (data !== null) {
              console.log('Local order by worker email:', data);
              setLocalOrder(data);
            } else {
              console.warn('Received null data from the server');
            }
          } else {
            console.error('Failed to fetch local order by worker email:', response.statusText);
            setLocalOrder([]);
          }
        } catch (error) {
            setLocalOrder([]);
          console.error('Network error:', error.message);
        }
      };
      

      const deleteFromOrder = async (cartSparePartId, sparePartId, userEmail) => {
          try {
            const response = await fetch(`http://localhost:5001/api/v1/order/local/item/${sparePartId}?email=${userEmail}`, {
              method: 'DELETE',
              headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${Cookies.get("jwt")}`,
              },
            });
      
            if (response.ok) {
              console.log('Usunięto przedmiot z koszyka:');
              const workerEmail = getUserInfoFromToken()?.sub;
              if (workerEmail) {
                fetchLocalOrderByWorkerEmail(workerEmail);
              }
            } else {
              console.error('Błąd podczas usuwania przedmiotu do koszyka:', response.statusText);
            }
          } catch (error) {
            console.error('Błąd sieci:', error.message);
          }
      };

      const handleOrder = async (localOrderId, commissionId) => {
        try {
          if (!commissionId) {
            alert('Commission ID is required.');
            return;
          }
      
          if (!localOrder || !localOrder.orderSparePartList || localOrder.orderSparePartList.length === 0) {
            alert('Your cart is empty. Add items to the cart before placing an order.');
            return;
          }
      
          const response = await fetch(`http://localhost:5001/api/v1/order/local/${localOrderId}`, {
            method: 'PUT',
            headers: {
              'Content-Type': 'application/json',
              'Authorization': `Bearer ${Cookies.get("jwt")}`,
            },
            body: JSON.stringify({
              orderStatus: "IN_PROGRESS",
              commissionId: commissionId
            }),
          });
      
          if (response.ok) {
            console.log('Local order status updated to IN_PROGRESS');
            fetchLocalOrderByWorkerEmail(getUserInfoFromToken()?.sub);
            setCommissionId('');
          } else {
            console.error('Failed to update local order status:', response.statusText);
          }
        } catch (error) {
          console.error('Network error:', error.message);
        }
      };         

    return (
      <div className="table-container">
        <div className="overflow-container5" >
          <Table className="custom-table">
            <thead>
              <tr>
                <th>Spare Part ID</th>
                <th>Name</th>
                <th>Producent</th>
                <th>Quantity</th>
                <th>Total Cost</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {localOrder &&
                localOrder.orderSparePartList &&
                localOrder.orderSparePartList.map((sparePart, index) => (
                  <tr key={index}>
                    <td style={{ verticalAlign: 'middle'}}>{sparePart.sparePartId}</td>
                    <td style={{ verticalAlign: 'middle'}}>{sparePart.sparePartName}</td>
                    <td style={{ verticalAlign: 'middle'}}>{sparePart.producerName}</td>
                    <td style={{ verticalAlign: 'middle'}}>{sparePart.quantity}</td>
                    <td style={{ verticalAlign: 'middle'}}>{(sparePart.price * sparePart.quantity).toFixed(2)} zł</td>
                    <td style={inputButtonStyle}>
                      <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
                        <button style={{border:'none', backgroundColor: 'transparent'}}  onClick={() => deleteFromOrder(sparePart.id, sparePart.sparePartId, getUserInfoFromToken()?.sub)}>
                          <img src={bin} alt="bin" style={{ width: '40px', height: '40px' }} />
                        </button>
                      </div>
                    </td>
                  </tr>
                ))}
            </tbody>
            </Table>
            <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'flex-end' }}>
              <div style={{ display: 'flex', flexDirection: 'row', alignItems: 'center'}}>
              <label>Commission ID: </label>
              <input
                type="text"
                value={commissionId}
                style={{width: "56px", marginLeft: "10px",marginRight:"10px"}}
                onChange={(e) => setCommissionId(e.target.value)}
              />
            <button style={{border:'none', backgroundColor: 'transparent'}} onClick={() => handleOrder(localOrder.id, commissionId)}>
              <img src={order} alt="order" style={{ width: '40px', height: '40px' }} />
            </button>
            </div>
            </div>
          </div>
      </div>
  )
}
export default TableCartWarehouse