
// TableClient.js
import Cookies from "js-cookie";
import React, { useState, useEffect } from "react";
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';

const TableClient = ({ filterText, isEditingClient, onEditClient }) => {
  const [clients, setClients] = useState([]);
  const [selectedClientId, setSelectedClientId] = useState(null);
  const [editedClientId, setEditedClientId] = useState(null);
  const [editedClient, setEditedClient] = useState({
    id: null,
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
      const response = await fetch("http://localhost:5001/api/v1/clients", {
        headers: {
          'Authorization': `Bearer ${Cookies.get("jwt")}`,
        },
      });
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

  const handleSelectClient = (clientId) => {
    if (!isEditingClient) {
      setSelectedClientId((prevSelectedClientId) =>
        prevSelectedClientId === clientId ? null : clientId
      );
    }
  };

  const handleSaveEdit = () => {
    const updatedClients = clients.map((client) => {
      if (client.id === editedClientId) {
        return { ...client, ...editedClient };
      }
      return client;
    });

    setClients(updatedClients);
    setEditedClientId(null);
    onEditClient(false); 
  };

  const handleEditClient = (clientId) => {
    const clientToEdit = clients.find((client) => client.id === clientId);
    setEditedClientId(clientId);
    setEditedClient(clientToEdit);
    onEditClient(true); 
  };

  const filteredClients = clients.filter((client) =>
    client.name.toLowerCase().includes(filterText.toLowerCase()) ||
    client.surname.toLowerCase().includes(filterText.toLowerCase()) ||
    client.email.toLowerCase().includes(filterText.toLowerCase()) ||
    client.phoneNumber.includes(filterText)
  );

  return (
    <div className="table-container">
      <div className="overflow-container">
        {isEditingClient ? (
          <div>
            <p>Editing Client: {editedClientId}</p>
            <Form>
              <Form.Group controlId="formName">
                <Form.Label>New Name:</Form.Label>
                <Form.Control
                  type="text"
                  value={editedClient.name}
                  onChange={(e) => setEditedClient({ ...editedClient, name: e.target.value })}
                />
              </Form.Group>

              <Form.Group controlId="formSurname">
                <Form.Label>New Surname:</Form.Label>
                <Form.Control
                  type="text"
                  value={editedClient.surname}
                  onChange={(e) => setEditedClient({ ...editedClient, surname: e.target.value })}
                />
              </Form.Group>

              <Form.Group controlId="formEmail">
                <Form.Label>New Email:</Form.Label>
                <Form.Control
                  type="text"
                  value={editedClient.email}
                  onChange={(e) => setEditedClient({ ...editedClient, email: e.target.value })}
                />
              </Form.Group>

              <Form.Group controlId="formPhoneNumber">
                <Form.Label>New Phone Number:</Form.Label>
                <Form.Control
                  type="text"
                  value={editedClient.phoneNumber}
                  onChange={(e) => setEditedClient({ ...editedClient, phoneNumber: e.target.value })}
                />
              </Form.Group>

              <Button type="button" onClick={handleSaveEdit}>
                Save Changes
              </Button>
            </Form>
          </div>
        ) : (
          <table className="custom-table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Surname</th>
                <th>E-mail</th>
                <th>Phone</th>
              </tr>
            </thead>
            <tbody>
              {filteredClients.map((client) => (
                <tr
                  key={client.id}
                  onClick={() => handleSelectClient(client.id)}
                  className={selectedClientId === client.id ? "selected-row" : ""}
                >
                  <td>{client.id}</td>
                  <td>{client.name}</td>
                  <td>{client.surname}</td>
                  <td>{client.email}</td>
                  <td>{client.phoneNumber}</td>
                  <td>
                    <button type="button" onClick={() => handleEditClient(client.id)}>
                      Edit
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </div>
  );
};

export default TableClient;
