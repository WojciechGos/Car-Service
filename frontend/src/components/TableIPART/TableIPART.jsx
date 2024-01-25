import React, { useState, useEffect } from "react";
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import Button from 'react-bootstrap/Button';
import Cookies from "js-cookie";
import { jwtDecode } from 'jwt-decode';

const TableIPART = () => {
  const [items, setItems] = useState([]);
  const [quantities, setQuantities] = useState({});

  useEffect(() => {
    fetchItems();
  }, []);

  const fetchItems = async () => {
    try {
      const response = await fetch("http://localhost:5001/api/v1/wholesalers/ipart", {
        headers: {
          'Authorization': `Bearer ${Cookies.get("jwt")}`,
        },
      });
      if (response.ok) {
        const data = await response.json();
        setItems(data);
        console.log(data)
      } else {
        console.error("Failed to fetch items");
      }
    } catch (error) {
      console.error("Error fetching items:", error);
    }
  };

  const handleQuantityChange = (event, sparePartId) => {
    setQuantities((prevQuantities) => ({
      ...prevQuantities,
      [sparePartId]: event.target.value,
    }));
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

  const handleAddToCart = async (sparePartId, userEmail) => {
    const quantity = quantities[sparePartId] || '';
    console.log(sparePartId);

    if (quantity.trim() === '') {
      alert('Please enter a quantity before placing the order.');
    } else {
      try {
        const response = await fetch(`http://localhost:5001/api/v1/wholesalers/ipart/${sparePartId}`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${Cookies.get("jwt")}`,
          },
          body: JSON.stringify({
            email: userEmail,
            quantity: parseInt(quantity, 10), 
          }),
        });

        if (response.ok) {
          const data = await response.json();
          console.log('Dodano przedmiot do koszyka:', data);
          fetchItems();
          setQuantities({});
        } else {
          console.error('Błąd podczas dodawania przedmiotu do koszyka:', response.statusText);
        }
      } catch (error) {
        console.error('Błąd sieci:', error.message);
      }
    }
  };

  return (
    <div className="table-container">
      <div className="overflow-container3">
      <table className="custom-table">
        <thead>
          <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Quantity</th>
            <th>Parameters</th>
            <th>Price</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {items.map(item => (
            <tr
              key={item.id}
            >
              <td>{item.id}</td>
              <td>{item.sparePartName}</td>
              <td>{item.quantity}</td>
              <td className="parametrsView">{item.parameters}</td>
              <td>{item.price.toLocaleString('pl-PL', { style: 'currency', currency: 'PLN' })}</td>
              <td>
                <InputGroup className="mb-3">
                  <Form.Control
                    placeholder="quantity"
                    aria-label="quantity"
                    value={quantities[item.id] || ''}
                    onChange={(e) => handleQuantityChange(e, item.id)}
                  />
                  <Button variant="primary" id="button-addon2" onClick={() => handleAddToCart(item.id, getUserInfoFromToken()?.sub)}>
                    add
                  </Button>
                </InputGroup>
              </td>
            </tr>
          ))}
          </tbody>
          </table>
      </div>
    </div>
  );
}

export default TableIPART;
