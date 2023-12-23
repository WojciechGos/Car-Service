import Sidebar from "../components/Sidebar/SideBar"
import HeaderClient from "../components/HeaderClient/HeaderClient"
import TableClient from "../components/TableClient/TableClient"

const Client = ()=>{
    return (
        <div style={{display: "flex"}}>
            <Sidebar />
            
            <div>
            <HeaderClient />
           <TableClient />
           
           </div>
        </div>
       
           
            
    )
}

export default Client