import Sidebar from "../components/Sidebar/SideBar"
import HeaderWhoseler from "../components/HeaderWhoseler/HeaderWhoseler"
import TableWhoseler from "../components/TableWhoseler/TableWhoseler"

const Wholeseler = ()=>{
    return (
        <div style={{display: "flex"}}>
            <Sidebar />
            
            <div>
                <HeaderWhoseler />
                <TableWhoseler />
            </div>
        
        </div>

       
           
            
    )
}

export default Wholeseler