import Sidebar from "../components/Sidebar/SideBar"
import HeaderVehicle from "../components/HeaderVehicle/HeaderVehicle"
import TableAddVehicle from "../components/TableVehicle/TableAddVehicle"

const VehicleAdd = ()=>{
    return (
        <div style={{display: "flex"}}>
            <Sidebar />
            
            <div>
            <HeaderVehicle />
            <TableAddVehicle />
           
           </div>
        </div>
       
           
            
    )
}

export default VehicleAdd