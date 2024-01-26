import { createContext, useState } from "react";
import Cookies from "js-cookie";
const CostEstimateContext = createContext();

export function CostEstimateContextProvider({ children }) {
  const [costData, setCostData] = useState({ name: "" });
  const [sparePartList, setSparePartList] = useState([]);
  const [laborCost, setLaborCost] = useState(0);


  const onlaborCostChange = ({ target: { value } }) => setLaborCost(value)


  const addSparePart = (item, quantities) => {
    const keys = Object.keys(quantities);

    // Access the last key
    const lastKey = keys[keys.length - 1];

    // Access the corresponding value
    const quantity = quantities[lastKey];
    console.log(quantity);

    // Check if the spare part already exists in the list
    const existingSparePartIndex = sparePartList.findIndex(
      (sparePart) => sparePart.id === item.id
    );

    // If the spare part already exists, update its quantity
    if (existingSparePartIndex !== -1) {
      const updatedSparePartList = [...sparePartList];
      updatedSparePartList[existingSparePartIndex].quantity += quantity;

      setSparePartList(updatedSparePartList);
    } else {
      // If the spare part doesn't exist, add it to the list
      const newSparePart = {
        id: item.id, // Assuming each spare part has a unique identifier
        sparePart: item,
        quantity: quantity,
      };

      setSparePartList((prevSparePartList) => [
        ...prevSparePartList,
        newSparePart,
      ]);
    }
  };
  const deleteSparePart = (id) => {
    console.log("delete");
  };

  const updateCommissionStatus = async (commissionId) => {
    const bodyObject = {
      commissionStatus:'IN_PROGRESS'
    }
    try {
      const response = await fetch(`http://localhost:5001/api/v1/commissions/${commissionId}`, {
        method: "PUT",
        headers: {
          "Content-Type": "Application/json",
          Authorization: `Bearer ${Cookies.get("jwt")}`,
        },
        body: JSON.stringify(bodyObject),
      });

      if (response.ok) {
        const data = await response.json();

        if (data !== null) {
        } else {
          console.warn("Received null data from the server");
        }
      } else {
        console.error("Failed to update commission", response.statusText);
      }
    } catch (error) {
      console.error("Network error:", error.message);
    }
  };

  const acceptCostEstimate = async (commissionId) => {
    const sparePartIdQuantity = sparePartList.reduce((map, obj) => {
      map[obj.id] = obj.quantity;
      return map;
    }, {});

    const bodyObject = {
      costType: "estimate",
      name: "Cost Estimate",
      sparePartQuantities: sparePartIdQuantity,
      commissionId: commissionId,
      laborPrice: laborCost,
    };
    console.log("bodyObject");
    console.log(bodyObject);

    try {
      const response = await fetch(`http://localhost:5001/api/v1/costs`, {
        method: "POST",
        headers: {
          "Content-Type": "Application/json",
          Authorization: `Bearer ${Cookies.get("jwt")}`,
        },
        body: JSON.stringify(bodyObject),
      });

      if (response.ok) {
        const data = await response.json();
        await updateCommissionStatus(commissionId)
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

  const getSpareParts = async (id) => {
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

        if (data !== null) {
          console.log("getSpareParts");
          console.log(data);
          setCostData(data.costEstimate)
          if (data.costEstimate) setSparePartList(data.costEstimate.spareParts);
          // setSparePartList(data.costEstimate.spareParts)
        } else {
          console.warn("Received null data from the server");
        }
      } else {
        console.error("Failed to get cost", response.statusText);
      }
    } catch (error) {
      console.error("Network error:", error.message);
    }
  };

  return (
    <CostEstimateContext.Provider
      value={{
        costData,
        setCostData,
        sparePartList,
        setSparePartList,
        addSparePart,
        deleteSparePart,
        acceptCostEstimate,
        getSpareParts,
        laborCost,
        onlaborCostChange,
      }}
    >
      {children}
    </CostEstimateContext.Provider>
  );
}
export default CostEstimateContext;
