import React, { useState, useEffect } from "react";
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import { Link } from "react-router-dom";
import PATH from "../../paths";
import Cookies from "js-cookie";

const AddCommissionForm = ({ onAddCommission, onCancel }) => {
  const getCurrentDateTime = () => {
    const currentDate = new Date();
    return currentDate.toISOString(); 
  };

  const [newCommission, setNewCommission] = useState({
    createDate: getCurrentDateTime(),
    endDate: "",
    vehicleId: "",
    clientId: "",
    costEstimate: "",
    contractor: {
      name: "",
      surname: ""
    }
  });

  useEffect(() => {
    // Extract user information from JWT token
    const token = Cookies.get("jwt");
    if (token) {
      const decodedToken = parseJwt(token);
      if (decodedToken) {
        setNewCommission((prevCommission) => ({
          ...prevCommission,
          contractor: {
            name: decodedToken.name,
            surname: decodedToken.surname,
          },
        }));
      }
    }
  }, []); // Run the effect only once when the component mounts

  const parseJwt = (token) => {
    try {
      return JSON.parse(atob(token.split(".")[1]));
    } catch (e) {
      return null;
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewCommission({
      ...newCommission,
      [name]: value
    });
  };

  const handleAddCommission = async () => {
    try {
      const response = await fetch("http://localhost:5001/api/v1/commissions", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          'Authorization': `Bearer ${Cookies.get("jwt")}`,
        },
        body: JSON.stringify({
          createDate: newCommission.createDate,
          endDate: newCommission.endDate,
          vehicleId: newCommission.vehicleId,
          clientId: newCommission.clientId,
          costEstimate: newCommission.costEstimate,
          contractor: {
            name: newCommission.contractor.name,
            surname: newCommission.contractor.surname
          }
        })
      });

      if (response.ok) {
        onAddCommission(); 
      } else {
        console.error("Failed to add commission");
      }
    } catch (error) {
      console.error("Error adding commission:", error);
    }
  };

  return (
    <div>
      <Form>
        <Form.Group controlId="formEndDate">
          <Form.Label>End Date</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter end date"
            name="endDate"
            value={newCommission.endDate}
            onChange={handleInputChange}
          />
        </Form.Group>

        <Form.Group controlId="formVehicleId">
          <Form.Label>Vehicle ID</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter vehicle ID"
            name="vehicleId"
            value={newCommission.vehicleId}
            onChange={handleInputChange}
          />
        </Form.Group>

        <Form.Group controlId="formClientId">
          <Form.Label>Client ID</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter client ID"
            name="clientId"
            value={newCommission.clientId}
            onChange={handleInputChange}
          />
        </Form.Group>

        <Form.Group controlId="formCostEstimate">
          <Form.Label>Cost Estimate</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter cost estimate"
            name="costEstimate"
            value={newCommission.costEstimate}
            onChange={handleInputChange}
          />
        </Form.Group>

        <Form.Group controlId="formContractorName">
          <Form.Label>Contractor Name</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter contractor name"
            name="contractorName"
            value={newCommission.contractor.name}
            onChange={(e) => handleInputChange({ target: { name: "name", value: e.target.value } })}
          />
        </Form.Group>

        <Form.Group controlId="formContractorSurname">
          <Form.Label>Contractor Surname</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter contractor surname"
            name="contractorSurname"
            value={newCommission.contractor.surname}
            onChange={(e) => handleInputChange({ target: { name: "surname", value: e.target.value } })}
          />
        </Form.Group>

        <Button variant="primary" onClick={handleAddCommission} style={{ marginTop: "10px", fontSize: "16px" }}>
          Add Commission
        </Button>
        <Link to={PATH.COMMISSION}>
          <Button variant="secondary" onClick={onCancel} style={{ marginTop: "10px", marginLeft: "10px", fontSize: "16px" }}>
            Cancel
          </Button>
        </Link>
      </Form>
    </div>
  );
};

export default AddCommissionForm;
