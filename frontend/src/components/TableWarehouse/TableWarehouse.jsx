import React, { useState, useEffect } from "react";
import Cookies from "js-cookie";
import { jwtDecode } from 'jwt-decode';

const TableWarehouse = ( {filterText} )=>{
  const [spareParts, setSpareParts] = useState([]);
  const [selectedSparePartId, setSelectedSparePartId] = useState(null)
  const [quantities, setQuantities] = useState({});

  const tdShort = {
    width: "222px", 
  };

  const inputButtonStyle = {
    justifyContent: 'center'
  };

  useEffect(() => {
    fetchSpareParts();
  }, []);

  const fetchSpareParts = async () => {
    try {
      const response = await fetch("http://localhost:5001/api/v1/spareParts", {
        headers: {
          'Authorization': `Bearer ${Cookies.get("jwt")}`,
        },
      });
      if (response.ok) {
        const data = await response.json();
        const formattedSpareParts = data.map(sparePart => ({
          ...sparePart,
          price: sparePart.price.toFixed(2),
        }));

        console.log(formattedSpareParts.parameters);
  
        setSpareParts(formattedSpareParts);
      } else {
        console.error("Failed to fetch spare parts");
      }
    } catch (error) {
      console.error("Error fetching spare parts:", error);
    }
  };

  const filteredSpareParts = spareParts.filter((sparePart) =>
    sparePart.name.toLowerCase().includes(filterText.toLowerCase())
  );

  
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
        const response = await fetch(`http://localhost:5001/api/v1/order/local/item/${sparePartId}`, {
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
          fetchSpareParts();
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
        <div className="overflow-container4">
        <table className="custom-table">
          <thead>
            <tr>
              <th>id</th>
              <th>Name</th>
              <th>Parameters</th>
              <th>Quantity</th>
              <th>Price</th>
              <th>Quantity:</th>
              <th/>
            </tr>
          </thead>
          <tbody>
          {filteredSpareParts.map((sparePart) => (
              <tr
                key={sparePart.id}
                style={{
                  cursor: "pointer",
                  backgroundColor: selectedSparePartId === sparePart.id ? "#f2f2f2" : "transparent",
                }}
              >
                <td>{sparePart.id}</td>
                <td>{sparePart.name}</td>
                <td style={tdShort}>{sparePart.parameters}</td>
                <td>{sparePart.quantity}</td>
                <td>{sparePart.price} zł</td>
                <td style={inputButtonStyle}>
                  <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
                    <label>
                      <input
                        style={{ width: "85px"}}
                        type="text"
                        placeholder="Enter quantity..."
                        value={quantities[sparePart.id] || ''}
                        onChange={(e) => handleQuantityChange(e, sparePart.id)}
                      />
                    </label>
                    <button onClick={() => handleAddToCart(sparePart.id, getUserInfoFromToken()?.sub)}>add</button>
                  </div>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
    )
 
}
export default TableWarehouse