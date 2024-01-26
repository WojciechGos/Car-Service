// AddCommissionForm.js
import React, { useState } from "react";
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Cookies from "js-cookie";
import TableWorker from "../TableWorker/TableWorker";

const AddCommissionForm = () => {
  const [newCommission, setNewCommission] = useState({
    vehicleId: "",
    clientId: "",
    workerId: "",
    description: "",
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewCommission({
      ...newCommission,
      [name]: value
    });
  };

  const handleAddCommission = async () => {
    try {
      console.log("newCommission")
      console.log(newCommission)

      const response = await fetch("http://localhost:5001/api/v1/commissions", {
        method: "POST",
        headers: {
          "Content-Type": "Application/json",
          'Authorization': `Bearer ${Cookies.get("jwt")}`,
        },
        body: JSON.stringify(newCommission)
      });

      if (response.ok) {
        // Handle success (e.g., show a success message, redirect, etc.)
        console.log("Commission added successfully");
      } else {
        // Handle failure (e.g., show an error message)
        console.error("Failed to add commission");
      }
    } catch (error) {
      console.error("Error adding commission:", error);
    }
  };

  return (
    <div>
      <Form>

        <Form.Group controlId="formVehicleId">
          <Form.Label>Vehicle ID</Form.Label>
          <Form.Control
            type="text"
            name="vehicleId"
            value={newCommission.vehicleId}
            onChange={handleInputChange}
          />
        </Form.Group>

        <Form.Group controlId="formClientId">
          <Form.Label>Client ID</Form.Label>
          <Form.Control
            type="text"
            name="clientId"
            value={newCommission.clientId}
            onChange={handleInputChange}
          />
        </Form.Group>

        <Form.Group controlId="formContractor">
          <Form.Label>Worker ID</Form.Label>
          <Form.Control
            type="text"
            name="workerId"
            value={newCommission.workerId}
            onChange={handleInputChange}
          />
        </Form.Group>

        <Form.Group controlId="formCostEstimate">
          <Form.Label>Description</Form.Label>
          <Form.Control
            type="text"
            name="description"
            value={newCommission.description}
            onChange={handleInputChange}
          />
        </Form.Group>



        {/* Add more Form.Group elements for other fields as needed */}
        
        <Button variant="primary" onClick={handleAddCommission} style={{ marginTop: "10px", fontSize: "16px" }}>
          Add Commission
        </Button>
     
      </Form>
      <TableWorker/>
    </div>
  );
};

export default AddCommissionForm;
