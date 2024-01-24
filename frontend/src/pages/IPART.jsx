import Sidebar from "../components/Sidebar/SideBar"
import HeaderWholesalers from "../components/HeaderWholesalers/HeaderWholesaler"
import TableIPART from "../components/TableIPART/TableIPART"

const IPART = ()=>{
    return (
        <div style={{display: "flex"}}>
            <Sidebar />
          
            <div>
                <HeaderWholesalers />
                <TableIPART />
            </div>


        </div>

       
           
            
    )
}

export default IPART