import { useParams } from "react-router-dom"
import CostTableContainer from "../components/CostTable/CostTableContainer"
import Sidebar from "../components/Sidebar/SideBar"


const CostTotal = ()=>{

    const {id} = useParams();

    console.log("commission " +id)

    return (
        <div style={{display: "flex"}}>
            <Sidebar />
            
            <div>
                <CostTableContainer costType='total' commissionId={id}/>
           </div>

        </div> 
    )
}

export default CostTotal