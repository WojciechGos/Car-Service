import Sidebar from "../components/Sidebar/SideBar"
import HeaderWholesalers from "../components/HeaderWholesalers/HeaderWholesaler"
import SquareWholesalers from "../components/SquareWholesalers/SquareWholesalers"

const Wholesalers = ()=>{
    return (
        <div style={{display: "flex"}}>
            <Sidebar />
          
        <div>
             <HeaderWholesalers />
             <SquareWholesalers />
        </div>


        </div>

       
           
            
    )
}

export default Wholesalers