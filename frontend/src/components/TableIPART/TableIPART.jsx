import React, { useState, useEffect } from "react";
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import Button from 'react-bootstrap/Button';
import Cookies from "js-cookie";

const TableIPART = () => {
  const [items, setItems] = useState([]);

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
              <td>{item.price}</td>
              <td>
                <InputGroup className="mb-3">
                  <Form.Control
                    placeholder="quantity"
                    aria-label="quantity"
                  />
                  <Button variant="primary" id="button-addon2">
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
