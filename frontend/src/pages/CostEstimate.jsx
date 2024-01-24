import Sidebar from "../components/Sidebar/SideBar"
import CostHeader from "../components/CostHeader/CostHeader"
import { useState } from "react"
import CostTableContainer from "../components/CostTable/CostTableContainer"
import CostEstimateCreator from "../components/CostEstimateCreator/CostEstimateCreator"

const CostEstimate = ()=>{

    const [selected, setSelected] = useState('details')


    return (
        <div style={{display: "flex"}}>
            <Sidebar />
            
            <div>
                <CostHeader selected={selected} setSelected={setSelected}/>

                {
                    selected === 'details' ?
                    (
                        <CostTableContainer/>
                    )
                    :
                    (
                        <CostEstimateCreator/>
                    )
                }
           </div>

        </div> 
    )
}

export default CostEstimate