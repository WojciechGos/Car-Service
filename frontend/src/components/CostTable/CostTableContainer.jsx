import { useEffect } from "react";
import CostTable from "./CostTable";
import { useParams } from "react-router-dom";
import { useState } from "react";
import Cookies from "js-cookie";

const CostTableContainer = ({ costType, commissionId }) => {
  const [costData, setCostData] = useState({});

  const getCostEstimate = async () => {
    try {
      const response = await fetch(
        `http://localhost:5001/api/v1/commissions/${commissionId}`,
        {
          method: "GET",
          headers: {
            Authorization: `Bearer ${Cookies.get("jwt")}`,
          },
        }
      );
      const data = await response.json();
      console.log(data);
      if (costType === "total") setCostData(data.totalCost);
      else if(costType === 'estimate') setCostData(data.costEstimate);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    getCostEstimate();
  }, [costType]);

  const props = {
    costData: costData,
  };

  return <CostTable {...props} />;
};

export default CostTableContainer;
