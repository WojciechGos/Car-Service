import React, { useState } from "react";
import Sidebar from "../components/Sidebar/SideBar";
import HeaderCommission from "../components/HeaderCommission/HeaderCommission";
import AddCommissionForm from "../components/TableCommission/AddCommissionForm";

const CommissionAdd = () => {
  return (
    <div style={{ display: "flex" }}>
      <Sidebar />

      <div>
        <HeaderCommission />
       
        <AddCommissionForm/>
      </div>
    </div>
  );
};

export default CommissionAdd;
