import React, { useState, useEffect } from "react";
import Table from 'react-bootstrap/Table';
import InputGroup from 'react-bootstrap/InputGroup';
import Button from 'react-bootstrap/Button';
import Cookies from "js-cookie";

const TableCart = () => {
  const [items, setItems] = useState([]);

  useEffect(() => {
    fetchItems();
  }, []);

  const fetchItems = async () => {
    try {
      const response = await fetch("http://localhost:5001/api/v1/order/external", {
        headers: {
          'Authorization': `Bearer ${Cookies.get("jwt")}`,
        },
      });
      if (response.ok) {
        const data = await response.json();
        setItems(data);
        console.log(data);
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
        <Table className="custom-table">
          <thead>
            <tr>
              <th>Id</th>
              <th>Name</th>
              <th>Quantity</th>
              <th>Producer</th>
              <th>Parameters</th>
              <th>Total cost</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {items.map(item => (
              <tr key={item.id}>
                <td>{item.id}</td>
                <td>{item.sparePartName}</td>
                <td>{item.quantity}</td>
                <td>{item.producer}</td>
                <td>{item.parameters}</td>
                <td>{item.totalCost}</td>
                <td>
                  <InputGroup className="mb-3">
                    <Button variant="danger" id="button-addon2">
                      delete
                    </Button>
                  </InputGroup>
                </td>
              </tr>
            ))}
          </tbody>
        </Table>
      </div>
    </div>
  );
};

export default TableCart;
