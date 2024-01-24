import Sidebar from "../components/Sidebar/SideBar"
import HeaderCart from "../components/HeaderCart/HeaderCart"
import TableCart from "../components/TableCart/TableCart"

const Cart = ()=>{
    return (
        <div style={{display: "flex"}}>
            <Sidebar />
          
            <div>
                <HeaderCart />
                <TableCart />
            </div>


        </div>

       
           
            
    )
}

export default Cart