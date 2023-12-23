import Sidebar from "../components/Sidebar/SideBar"
import HeaderOrder from "../components/HeaderOrder/HeaderOrder"
import TableOrder from "../components/TableOrder/TableOrder"

const Order = ()=>{
    return (
        <div style={{display: "flex"}}>
            <Sidebar />
            
            <div>
                <HeaderOrder />
                <TableOrder/>   
            </div>
        
        </div>

       
           
            
    )
}

export default Order