import React, { useState, useEffect } from "react";
import Table from 'react-bootstrap/Table';

const TableClient = () => {
  const [clients, setClients] = useState([]);
  const [selectedClientId, setSelectedClientId] = useState(null);

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

  const handleDelete = async () => {
    if (!selectedClientId) {
      console.error("No client selected for deletion");
      return;
    }

    try {
      const response = await fetch(`http://localhost:5001/api/v1/clients/${selectedClientId}`, {
        method: 'DELETE',
      });

      if (response.ok) {
        fetchClients();
        setSelectedClientId(null);
      } else {
        console.error("Failed to delete client");
      }
    } catch (error) {
      console.error("Error deleting client:", error);
    }
  };

  const handleSelectClient = (clientId) => {
    
    setSelectedClientId((prevSelectedClientId) =>
      prevSelectedClientId === clientId ? null : clientId
    );
  };

  return (
    <div>
      <button onClick={handleDelete} disabled={!selectedClientId}>
        Delete Selected Client
      </button>

      <Table className="grayTable" bordered hover variant="secondary" style={{ marginTop: "10px" }}>
        <thead>
          <tr>
            <th>id</th>
            <th>Name</th>
            <th>Surname</th>
            <th>E-mail</th>
            <th>Phone</th>
          </tr>
        </thead>
        <tbody>
        {clients.map((client) => (
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
          </tr>
        ))}
        </tbody>
      </Table>
    </div>
  );
};

export default TableClient;
