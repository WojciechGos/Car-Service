import React, { useState, useEffect } from "react";
import Table from 'react-bootstrap/Table';
import InputGroup from 'react-bootstrap/InputGroup';
import Button from 'react-bootstrap/Button';
import Cookies from "js-cookie";
import { jwtDecode } from 'jwt-decode';

const TableCart = () => {
  const [externalOrder, setExternalOrder] = useState([]);

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
      <div className="overflow-container3">
        <Table className="custom-table">
          <thead>
            <tr>
              <th>Item ID</th>
              <th>Name</th>
              <th>Wholesaler</th>
              {/* <th>Parameters</th> */}
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
                <td>{item.externalOrderItemId}</td>
                <td>{item.sparePartName}</td>
                <td>{item.wholesaler}</td>
                {/* <td>{item.parameters}</td> */}
                <td>{item.quantity}</td>
                <td>{(item.price * item.quantity).toFixed(2)} zł</td>
                <td>
                  <InputGroup className="mb-3">
                    <Button variant="danger" id="button-addon2" onClick={() => deleteFromOrder(item.externalOrderItemId, getUserInfoFromToken()?.sub, item.wholesaler, item.quantity)}>
                      delete
                    </Button>
                  </InputGroup>
                </td>
              </tr>
            ))}
          </tbody>
        </Table>
      </div>
        <div className="order-button-container">
        <button onClick={() => handleOrder(externalOrder.id)}>
          Order
        </button>
      </div>
    </div>
  );
};

export default TableCart;
