import Sidebar from "../components/Sidebar/SideBar"
import HeaderCartWarehouse from "../components/HeaderCartWarehouse/HeaderCartWarehouse"
import TableCartWarehouse from "../components/TableCartWarehouse/TableCartWarehouse"

const CartWarehouse = ()=>{
    return (
        <div style={{display: "flex"}}>
            <Sidebar />
            <div>
                <HeaderCartWarehouse />
                <TableCartWarehouse/>   
            </div>
        </div>
    )
}

export default CartWarehouse