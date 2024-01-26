import Table from 'react-bootstrap/Table';
import React, { useState, useEffect, useContext } from "react";
import Cookies from "js-cookie";
import CostEstimateContext from '../../context/CostEstimateContext';
import estimate from "./estimation.png";



const CostEstimateCreator = ( {filterText} )=>{
  const [spareParts, setSpareParts] = useState([]);
  const [selectedSparePartId, setSelectedSparePartId] = useState(null)
  const [quantities, setQuantities] = useState({});

  const {addSparePart} = useContext(CostEstimateContext)


  const tdShort = {
    width: "700px", 
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

    return (
      <div className="table-container">
      <div className="overflow-container6">
        <table className="custom-table">
        <thead>
          <tr>
          <th>id</th>
          <th>Name</th>
          <th>Parameters</th>
          <th>Quantity</th>
          <th>Price</th>
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
                <div style={{ display: 'flex', flexDirection: 'row', alignItems: 'center' }}>
                  <label>
                    <input
                      style={{ width: "120px",marginRight:"10px"}}
                      type="text"
                      placeholder="Enter quantity..."
                      value={quantities[sparePart.id] || ''}
                      onChange={(e) => handleQuantityChange(e, sparePart.id)}
                    />
                  </label>
                  {/* <button onClick={() =>{addSparePart(sparePart, quantities)}}>add</button> */}
                  <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'flex-end' }}>
                    <button style={{border:'none', backgroundColor: 'transparent'}} onClick={() =>{addSparePart(sparePart, quantities)}}>
                      <img src={estimate} alt="estimate" style={{ width: '40px', height: '40px' }} />
                    </button>
                  </div>
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
export default CostEstimateCreator