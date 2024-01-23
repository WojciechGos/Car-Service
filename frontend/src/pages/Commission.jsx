
import Sidebar from "../components/Sidebar/SideBar"
import React, { useState } from "react";
import TableCommission from "../components/TableCommission/TableCommission"
import HeaderCommission from "../components/HeaderCommission/HeaderCommission"

const Commission = () => {
  const [selectedCommissionId, setSelectedCommissionId] = useState(null);
  const [rerender, setRerender] = useState(false);
  const [filterText, setFilterText] = useState('');

  return (
    <div style={{ display: "flex" }}>
      <Sidebar />
      <div>
        <HeaderCommission
          selectedCommissionId={selectedCommissionId}
          rerender={rerender}
          setrerender={setRerender}
          onFilterChange={setFilterText}
        />
        <TableCommission
          selectedCommissionId={selectedCommissionId}
          setSelectedCommissionId={setSelectedCommissionId}
          rerender={rerender}
          setrerender={setRerender}
          filterText={filterText}
        />
      </div>
    </div>
  );
};

export default Commission;
