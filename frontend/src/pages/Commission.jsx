import Sidebar from "../components/Sidebar/SideBar"
import TableCommission from "../components/TableCommission/TableCommission"
import HeaderCommission from "../components/HeaderCommission/HeaderCommission"

const Commission = ()=>{
    return (
        <div style={{display: "flex"}}>
            <Sidebar />
            
            <div>
           <HeaderCommission />
           <TableCommission />
           </div>

        </div> 
    )
}

export default Commission