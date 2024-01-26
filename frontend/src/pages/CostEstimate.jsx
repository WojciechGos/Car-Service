import Sidebar from "../components/Sidebar/SideBar";
import CostHeader from "../components/CostHeader/CostHeader";
import { useEffect, useState } from "react";
import { CostEstimateContextProvider } from "../context/CostEstimateContext";
import CostEstimateCreator from "../components/CostEstimateCreator/CostEstimateCreator";
import CostEstimateTable from "../components/CostEstimateTable/CostEstimateTable";
import { useParams } from "react-router-dom";
import Cookies from "js-cookie";

const CostEstimate = () => {
  const [selected, setSelected] = useState("details");
  const [cost, setCost] = useState(null)

  const { id } = useParams();
  
  useEffect(() => {
    const getCost = async () => {
      try {
        const response = await fetch(
          `http://localhost:5001/api/v1/commissions/${id}`,
          {
            method: "GET",
            headers: {
              "Content-Type": "Application/json",
              Authorization: `Bearer ${Cookies.get("jwt")}`,
            },
          }
        );

        if (response.ok) {
          const data = await response.json();
          console.log("GET COMMISSION: CostEstimate.jsx ")
          console.log(data)
          setCost(data.costEstimate)

          if (data !== null) {
          } else {
            console.warn("Received null data from the server");
          }
        } else {
          console.error("Failed to create cost", response.statusText);
        }
      } catch (error) {
        console.error("Network error:", error.message);
      }
    };
    getCost()
  }, [])


  return (
    <div style={{ display: "flex" }}>
      <Sidebar />

      <div>
        <CostHeader selected={selected} setSelected={setSelected} id={id} showChoice={cost===null?true:false} />

        <CostEstimateContextProvider>
        {
          cost === null ? 
          (

          selected === "details" ? (
            <CostEstimateTable filterText="" commissionId={id} showSaveButton={true} />
            ) : (
              <CostEstimateCreator filterText="" />
              )
       
          )
          :
          (
            <CostEstimateTable filterText="" commissionId={id} showSaveButton={false} />
          )
          }
             </CostEstimateContextProvider>
      </div>
    </div>
  );
};

export default CostEstimate;
