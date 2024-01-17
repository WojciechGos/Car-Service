import Sidebar from "../components/Sidebar/SideBar"
import HeaderSalersOrders from "../components/HeaderSalersOrders/HeaderSalersOrders"
import TableSalersOrders from "../components/TableSalersOrders/TableSalersOrders"

const SalersOrders = ()=>{
    return (
        <div style={{display: "flex"}}>
            <Sidebar />
          
            <div>
                <HeaderSalersOrders />
               <TableSalersOrders />
            </div>


        </div>

       
           
            
    )
}

export default SalersOrders