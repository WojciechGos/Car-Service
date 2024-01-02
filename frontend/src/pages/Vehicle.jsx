import Sidebar from "../components/Sidebar/SideBar"
import HeaderVehicle from "../components/HeaderVehicle/HeaderVehicle"
import TableVehicle from "../components/TableVehicle/TableVehicle"

const Vehicle = ()=>{
    return (
        <div style={{display: "flex"}}>
            <Sidebar />
            
            <div>
                <HeaderVehicle />
                <TableVehicle/>   
            </div>
        
        </div>

       
           
            
    )
}

export default Vehicle