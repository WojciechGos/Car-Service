import Sidebar from "../components/Sidebar/SideBar"
import HeaderExternalOrder from "../components/HeaderExternalOrder/HeaderExternalOrder"
import TableExternalOrder from "../components/TableExternalOrder/TableExternalOrder"

const ExternalOrder = ()=>{
    return (
        <div style={{display: "flex"}}>
            <Sidebar />
            
            <div>
                <HeaderExternalOrder />
                <TableExternalOrder/>   
            </div>
        
        </div>

       
           
            
    )
}

export default ExternalOrder