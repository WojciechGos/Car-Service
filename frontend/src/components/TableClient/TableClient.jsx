// TableClient.js
import React, { useState, useEffect } from "react";

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

  const handleSelectClient = (clientId) => {
    setSelectedClientId((prevSelectedClientId) =>
      prevSelectedClientId === clientId ? null : clientId
    );
  };

  return (
    <div className="table-container">
      <div className="overflow-container">
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
        </table>
      </div>
    </div>
  );
};

export default TableClient;
