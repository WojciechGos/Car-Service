import Sidebar from "../components/Sidebar/SideBar"
import HeaderOrder from "../components/HeaderLocalOrder/HeaderLocalOrder"
import TableLocalOrder from "../components/TableLocalOrder/TableLocalOrder"

const LocalOrder = ()=>{
    return (
        <div style={{display: "flex"}}>
            <Sidebar />
            
            <div>
                <HeaderOrder />
                <TableLocalOrder/>   
            </div>
        
        </div>

       
           
            
    )
}

export default LocalOrder