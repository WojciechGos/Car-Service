import React, { useState } from "react";
import Sidebar from "../components/Sidebar/SideBar";
import HeaderVehicle from "../components/HeaderVehicle/HeaderVehicle";
import TableVehicle from "../components/TableVehicle/TableVehicle";

const Vehicle = () => {
  const [filterText, setFilterText] = useState("");
  const [isEditingVehicle, setIsEditingVehicle] = useState(false);

  const handleFilterChange = (text) => {
    setFilterText(text);
  };

  const handleEditVehicle = (editing) => {
    setIsEditingVehicle(editing);
  };

  const handleSaveEdit = () => {
    setIsEditingVehicle(false);
  };

  return (
    <div style={{ display: "flex" }}>
      <Sidebar />
      <div>
        <HeaderVehicle
          onFilterChange={handleFilterChange}
          onEditVehicle={handleEditVehicle}
          onSaveEdit={handleSaveEdit}
          isEditingVehicle={isEditingVehicle}
        />
        <TableVehicle
          onEditVehicle={handleSaveEdit}
          isEditingVehicle={isEditingVehicle}
          filterText={filterText}
        />
      </div>
    </div>
  );
};

export default Vehicle;
