import React, { useState } from "react";
import Sidebar from "../components/Sidebar/SideBar";
import HeaderClient from "../components/HeaderClient/HeaderClient";
import TableClient from "../components/TableClient/TableClient";

const Client = () => {
  const [filterText, setFilterText] = useState("");
  const [isEditingClient, setIsEditingClient] = useState(false);

  const handleFilterChange = (text) => {
    setFilterText(text);
  };

  const handleEditClient = (editing) => {
    setIsEditingClient(editing);
  };

  const handleSaveEdit = () => {
    setIsEditingClient(false);
  };

  return (
    <div style={{ display: "flex" }}>
      <Sidebar />
      <div>
        <HeaderClient
          onFilterChange={handleFilterChange}
          onEditClient={handleEditClient}
          onSaveEdit={handleSaveEdit}
        />
        <TableClient filterText={filterText} isEditingClient={isEditingClient} onEditClient={handleSaveEdit} />
      </div>
    </div>
  );
};

export default Client;
