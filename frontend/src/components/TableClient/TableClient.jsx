import Cookies from "js-cookie";
import React, { useState, useEffect } from "react";
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';

const TableClient = ({ filterText }) => {
  const [clients, setClients] = useState([]);
  const [isEditingClient, setIsEditingClient] = useState(false);
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
        const sortedClients = data.sort((a, b) => a.id - b.id);
        setClients(sortedClients);
      } else {
        console.error("Failed to fetch clients");
      }
    } catch (error) {
      console.error("Error fetching clients:", error);
    }
  };
  const handleSaveEdit = async () => {
    try {
      const response = await fetch(`http://localhost:5001/api/v1/clients/${editedClientId}`, {
        method: "PUT",
        headers: {
          'Authorization': `Bearer ${Cookies.get("jwt")}`,
          "Content-Type": "application/json"
        },
        body: JSON.stringify(editedClient),
      });

      if (response.ok) {
        const updatedClients = clients.map((client) => {
          if (client.id === editedClientId) {
            return { ...client, ...editedClient };
          }
          return client;
        });

        setClients(updatedClients);
        setEditedClientId(null);
        setIsEditingClient(false);
      } else {
        console.error("Failed to save changes");
      }
    } catch (error) {
      console.error("Error saving changes:", error);
    }
  };

  const handleEditClient = (clientId) => {
    const clientToEdit = clients.find((client) => client.id === clientId);
    setEditedClientId(clientId);
    setEditedClient(clientToEdit);
    setIsEditingClient(true);
  };

  const handleCancelEdit = () => {
    setEditedClientId(null);
    setEditedClient({
      id: null,
      name: "",
      surname: "",
      email: "",
      phoneNumber: ""
    });
    setIsEditingClient(false);
  };

  const filteredClients = clients.filter((client) =>
    client.name.toLowerCase().includes(filterText.toLowerCase()) ||
    client.surname.toLowerCase().includes(filterText.toLowerCase()) ||
    client.email.toLowerCase().includes(filterText.toLowerCase()) ||
    client.phoneNumber.includes(filterText)
  );

  return (
    <div className="table-container">
      <div className="overflow-container8">
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
              <Button type="button" variant="danger" onClick={handleSaveEdit} style={{ marginTop: "10px", fontSize: "16px" }}>
                Save Changes
              </Button>
              <Button type="button" variant="secondary" onClick={handleCancelEdit} style={{ marginTop: "10px", fontSize: "16px" ,marginLeft:"5px"}}>
                Cancel
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
                <th/>
              </tr>
            </thead>
            <tbody>
              {filteredClients.map((client) => (
                <tr
                  key={client.id}
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
