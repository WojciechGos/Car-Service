import React, { useState, useEffect } from "react";
import Table from 'react-bootstrap/Table';
import Cookies from "js-cookie";
import { jwtDecode } from 'jwt-decode';
import bin from "./trash-bin.png";
import order from "./order.png";

const TableCart = () => {
  const [externalOrder, setExternalOrder] = useState([]);

  const inputButtonStyle = {
    justifyContent: 'center'
  };

  useEffect(() => {
    const workerEmail = getUserInfoFromToken()?.sub;
    if (workerEmail) {
      fetchExternalOrderByWorkerEmail(workerEmail);
    }
  }, []);

  const fetchExternalOrderByWorkerEmail = async (workerEmail) => {
    try {
      const response = await fetch(`http://localhost:5001/api/v1/order/external/${workerEmail}`, {
        method: 'GET',  
        headers: {
          'Authorization': `Bearer ${Cookies.get("jwt")}`,
        },
      });
      if (response.ok) {
        const data = await response.json();
        setExternalOrder(data);
        console.log(data);
      } else {
        console.error("Failed to fetch external order by worker email");
      }
    } catch (error) {
      console.error("Error fetching external order by worker email:", error);
    }
  };

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

  const deleteFromOrder = async (itemId, userEmail, wholesaler, quantity) => {
    try {
      let apiUrl;
      if (wholesaler === 'IPART') {
        apiUrl = `http://localhost:5001/api/v1/wholesalers/ipart/${itemId}?email=${userEmail}&quantity=${quantity}`;
      } else if (wholesaler === 'STARTHURT') {
        apiUrl = `http://localhost:5001/api/v1/wholesalers/starthurt/${itemId}?email=${userEmail}&quantity=${quantity}`;
      } else {
        console.error('Invalid wholesaler:', wholesaler);
        return;
      }
  
      const response = await fetch(apiUrl, {
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
          fetchExternalOrderByWorkerEmail(workerEmail);
        }
      } else {
        console.error('Błąd podczas usuwania przedmiotu do koszyka:', response.statusText);
      }
    } catch (error) {
      console.error('Błąd sieci:', error.message);
    }
  };  

  const handleOrder = async (externalOrderId) => {
    try {
      if (!externalOrder || !externalOrder.items || externalOrder.items.length === 0) {
        alert('Your cart is empty. Add items to the cart before placing an order.');
        return;
      }
  
      const response = await fetch(`http://localhost:5001/api/v1/order/external/${externalOrderId}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${Cookies.get("jwt")}`,
        },
        body: JSON.stringify({
          orderStatus: "IN_PROGRESS"
        }),
      });
  
      if (response.ok) {
        console.log('External order status updated to IN_PROGRESS');
        fetchExternalOrderByWorkerEmail(getUserInfoFromToken()?.sub);
        setExternalOrder([]);
      } else {
        console.error('Failed to update external order status:', response.statusText);
      }
    } catch (error) {
      console.error('Network error:', error.message);
    }
  };      

  return (
    <div className="table-container">
      <div className="overflow-container5">
        <Table className="custom-table">
          <thead>
            <tr>
              <th>Item ID</th>
              <th>Name</th>
              <th>Wholesaler</th>
              <th>Quantity</th>
              <th>Total cost</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {externalOrder &&
          externalOrder.items &&
          externalOrder.items.map((item, index) => (
              <tr key={item.id}>
                <td style={{ verticalAlign: 'middle'}}>{item.externalOrderItemId}</td>
                <td style={{ verticalAlign: 'middle'}}>{item.sparePartName}</td>
                <td style={{ verticalAlign: 'middle'}}>{item.wholesaler}</td>
                <td style={{ verticalAlign: 'middle'}}>{item.quantity}</td>
                <td style={{ verticalAlign: 'middle'}}>{(item.price * item.quantity).toFixed(2)} zł</td>
                <td style={inputButtonStyle}>
                  <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
                    <button style={{border:'none', backgroundColor: 'transparent'}}  onClick={() => deleteFromOrder(item.externalOrderItemId, getUserInfoFromToken()?.sub, item.wholesaler, item.quantity)}>
                      <img src={bin} alt="bin" style={{ width: '40px', height: '40px' }} />
                    </button>
                  </div>
                </td>

              </tr>
            ))}
          </tbody>
        </Table>
        <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'flex-end' }}>
          <button style={{border:'none', backgroundColor: 'transparent'}} onClick={() => handleOrder(externalOrder.id)}>
            <img src={order} alt="order" style={{ width: '40px', height: '40px' }} />
          </button>
        </div>
      </div>
    </div>
  );
};

export default TableCart;
