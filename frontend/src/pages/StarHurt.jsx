import Sidebar from "../components/Sidebar/SideBar"
import HeaderWholesalers from "../components/HeaderWholesalers/HeaderWholesaler"
import TableStarHurt from "../components/TableStarHurt/TableStarHurt"

const StarHurt = ()=>{
    return (
        <div style={{display: "flex"}}>
            <Sidebar />
          
            <div>
                <HeaderWholesalers />
                <TableStarHurt/>
            </div>


        </div>

       
           
            
    )
}

export default StarHurt