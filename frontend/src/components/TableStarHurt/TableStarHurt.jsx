import React, { useState, useEffect } from "react";
import Cookies from "js-cookie";
import { jwtDecode } from 'jwt-decode';
import add from "./add-to-cart.png";

const TableStarHurt = ()=>{
  const [items, setItems] = useState([]);
  const [quantities, setQuantities] = useState({});

  const inputButtonStyle = {
    justifyContent: 'center'
  };

  useEffect(() => {
    fetchItems();
  }, []);

  const fetchItems = async () => {
    try {
      const response = await fetch("http://localhost:5001/api/v1/wholesalers/starthurt", {
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
        const response = await fetch(`http://localhost:5001/api/v1/wholesalers/starthurt/${sparePartId}`, {
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
      <div className="overflow-container6">
      <table className="custom-table">
        <thead>
          <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Quantity</th>
            <th>Parameters</th>
            <th>Price</th>
            <th/>
          </tr>
        </thead>
        <tbody>
          {items.map(item => (
            <tr
              key={item.id}
            >
              <td>{item.id}</td>
              <td style={{maxWidth:'130px'}}>{item.sparePartName}</td>
              <td>{item.quantity}</td>
              <td className="parametrsView">{item.parameters}</td>
              <td>{item.price.toLocaleString('pl-PL', { style: 'currency', currency: 'PLN' })}</td>
              <td style={inputButtonStyle}>
                <div style={{ display: 'flex', flexDirection: 'row', alignItems: 'center',width:'100px'}}>
                  <label>
                    <input
                      style={{ width: "120px",marginRight:"20px"}}
                      type="text"
                      placeholder="Enter quantity..."
                      value={quantities[item.id] || ''}
                      onChange={(e) => handleQuantityChange(e, item.id)}
                    />
                  </label>
                  <button style={{border:'none', backgroundColor: 'transparent'}} onClick={() => handleAddToCart(item.id, getUserInfoFromToken()?.sub)}>
                    <img src={add} alt="Add to Cart" style={{ width: '40px', height: '40px',marginRight:'50px' }} />
                  </button>
                </div>
              </td>
            </tr>
          ))}
          </tbody>
          </table>
      </div>
    </div>
  );
}
export default TableStarHurt