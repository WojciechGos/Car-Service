import React, { useState } from "react";
import Sidebar from "../components/Sidebar/SideBar"
import HeaderWarehouse from "../components/HeaderWarehouse/HeaderWarehouse"
import TableWarehouse from "../components/TableWarehouse/TableWarehouse"

const Warehouse = ()=>{
    const [filterText, setFilterText] = useState("");

    const handleFilterChange = (text) => {
        setFilterText(text);
      };

    return (
        <div style={{display: "flex"}}>
            <Sidebar />
            
            <div>
            <HeaderWarehouse onFilterChange={handleFilterChange}/>
            <TableWarehouse filterText={filterText} />
           
           </div>
        </div>
       
           
            
    )
}

export default Warehouse