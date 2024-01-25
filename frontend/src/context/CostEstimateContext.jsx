import { createContext, useState } from "react";
import Cookies from "js-cookie";
const CostEstimateContext = createContext();

export function CostEstimateContextProvider({ children }) {
  const [costData, setCostData] = useState({ name: "cost data wojtek" });
  const [sparePartList, setSparePartList] = useState([]);

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
        name: item.name,
        parameters: item.parameters,
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

  const acceptCostEstimate = async () => {

    const bodyObject = {
        name: costData.name,

    }

    try {
      const response = await fetch(
        `http://localhost:5001/api/v1/cost`,
        {
          method: "POST",
          headers: {
            Authorization: `Bearer ${Cookies.get("jwt")}`,
          },
        }
      );

      if (response.ok) {
        const data = await response.json();

        if (data !== null) {
         
        } else {
          console.warn("Received null data from the server");
        }
      } else {
        console.error(
          "Failed to fetch local order by worker email:",
          response.statusText
        );

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
      }}
    >
      {children}
    </CostEstimateContext.Provider>
  );
}
export default CostEstimateContext;
