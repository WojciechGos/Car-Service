import Sidebar from "../components/Sidebar/SideBar"
import HeaderClient from "../components/HeaderClient/HeaderClient"
import TableAddClient from "../components/TableClient/TableAddClient"

const ClientAdd = ()=>{
    return (
        <div style={{display: "flex"}}>
            <Sidebar />
            
            <div>
            <HeaderClient />
            <TableAddClient />
           
           </div>
        </div>
       
           
            
    )
}

export default ClientAdd