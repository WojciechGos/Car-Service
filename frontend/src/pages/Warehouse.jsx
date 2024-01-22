import Sidebar from "../components/Sidebar/SideBar"
import HeaderWarehouse from "../components/HeaderWarehouse/HeaderWarehouse"
import TableWarehouse from "../components/TableWarehouse/TableWarehouse"

const Warehouse = ()=>{
    return (
        <div style={{display: "flex"}}>
            <Sidebar />
            
            <div>
            <HeaderWarehouse />
           <TableWarehouse />
           
           </div>
        </div>
       
           
            
    )
}

export default Warehouse