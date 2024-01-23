import Sidebar from "../components/Sidebar/SideBar"
import React, { useState } from "react";
import TableCommission from "../components/TableCommission/TableCommission"
import HeaderCommission from "../components/HeaderCommission/HeaderCommission"

const Commission = () => {
  const [selectedCommissionId, setSelectedCommissionId] = useState(null);
  const [filterText, setFilterText] = useState("");
  const [activeFilter, setActiveFilter] = useState(null);

  const handleFilterChange = (text) => {
    setFilterText(text);
  };

  const handleFilterButtonClick = (filterType) => {
    setActiveFilter(filterType);
    setFilterText("");
  };

  return (
    <div style={{ display: "flex" }}>
      <Sidebar />
      <div>
        <HeaderCommission
          selectedCommissionId={selectedCommissionId}
          onFilterChange={handleFilterChange}
          onFilterButtonClick={handleFilterButtonClick}
        />
        <TableCommission
          selectedCommissionId={selectedCommissionId}
          setSelectedCommissionId={setSelectedCommissionId}
          filterText={filterText}
          activeFilter={activeFilter}
        />
      </div>
    </div>
  );
};

export default Commission;
