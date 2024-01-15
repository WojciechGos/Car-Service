import React, { useState, useEffect } from "react";
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import { Link } from "react-router-dom";
import PATH from "../../paths";

const TableClient = () => {
  const [clients, setClients] = useState([]);
  const [newClient, setNewClient] = useState({
    name: "",
    surname: "",
    email: "",
    phoneNumber: ""
  });

  useEffect(() => {
    fetchClients();
  }, []);

  const fetchClients = async () => {
    try {
      const response = await fetch("http://localhost:5001/api/v1/clients");
      if (response.ok) {
        const data = await response.json();
        setClients(data);
      } else {
        console.error("Failed to fetch clients");
      }
    } catch (error) {
      console.error("Error fetching clients:", error);
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewClient({
      ...newClient,
      [name]: value
    });
  };

  const handleAddClient = async () => {
    try {
      const response = await fetch("http://localhost:5001/api/v1/clients", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          name: newClient.name,
          surname: newClient.surname,
          email: newClient.email,
          phoneNumber: newClient.phoneNumber
        })
      });
  
      if (response.ok) {
        fetchClients();
  
        setNewClient({
          name: "",
          surname: "",
          email: "",
          phoneNumber: ""
        });
      } else {
        console.error("Failed to add client");
      }
    } catch (error) {
      console.error("Error adding client:", error);
    }
  };

  return (
    <div>
      <Form>
        <Form.Group controlId="formName">
          <Form.Label>Name</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter name"
            name="name"
            value={newClient.name}
            onChange={handleInputChange}
          />
        </Form.Group>

        <Form.Group controlId="formSurname">
            <Form.Label>Surname</Form.Label>
            <Form.Control
                type="text"
                placeholder="Enter surname"
                name="surname"
                value={newClient.surname}
                onChange={handleInputChange}
            />
            </Form.Group>

            <Form.Group controlId="formEmail">
            <Form.Label>Email</Form.Label>
            <Form.Control
                type="email"
                placeholder="Enter email"
                name="email"
                value={newClient.email}
                onChange={handleInputChange}
            />
            </Form.Group>

            <Form.Group controlId="formPhoneNumber">
            <Form.Label>Phone Number</Form.Label>
            <Form.Control
                type="text"
                placeholder="Enter phone number"
                name="phoneNumber"
                value={newClient.phoneNumber}
                onChange={handleInputChange}
            />
            </Form.Group>

        <Link to={PATH.CLIENT}>
        <Button variant="primary" onClick={handleAddClient} style={{ marginTop: "10px", fontSize: "16px" }}>
          Add Client
        </Button>

        </Link>
      </Form>
    </div>
  );
};

export default TableClient;
