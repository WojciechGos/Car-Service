import Sidebar from "../components/Sidebar/SideBar";
import CostHeader from "../components/CostHeader/CostHeader";
import { useState } from "react";
import { CostEstimateContextProvider } from "../context/CostEstimateContext";
import CostEstimateCreator from "../components/CostEstimateCreator/CostEstimateCreator";
import CostEstimateTable from "../components/CostEstimateTable/CostEstimateTable";
import { useParams } from "react-router-dom";

const CostEstimate = () => {
  const [selected, setSelected] = useState("details");

  const {id} = useParams()
  console.log(id+ "commission id asdasdasdas"  )
  return (
    <div style={{ display: "flex" }}>
      <Sidebar />

      <div>
        <CostHeader selected={selected} setSelected={setSelected} id={id} />
        <CostEstimateContextProvider>
          {selected === "details" ? (
            <CostEstimateTable filterText='' commissionId={id} />
          ) : (
            <CostEstimateCreator filterText='' />
          )}
        </CostEstimateContextProvider>
      </div>
    </div>
  );
};

export default CostEstimate;
