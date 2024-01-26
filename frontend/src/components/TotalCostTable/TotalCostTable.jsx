import Table from "react-bootstrap/Table";
import React, { useState, useEffect } from "react";
import Cookies from "js-cookie";
import { jwtDecode } from "jwt-decode";

const TotalCostTable = ({ costType, commissionId }) => {
  const [costData, setCostData] = useState({ spareParts: [], name: "" });

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

      if (costType === "total") {
        if (data.totalCost === null) return;
        setCostData(data.totalCost);
      } else if (costType === "estimate") {
        if (data.costEstimate === null) return;
        setCostData(data.costEstimate);
      }
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    getCostEstimate();
  }, [costType]);

  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        alignItems: "flex-end",
      }}
    >
      <div className="d-flex justify-content-around w-100">
        <h2>Name: {costData.name} </h2>
      </div>

      <div className="table-container">
      <div className="overflow-container5">
        <table className="custom-table">
        <thead>
          <tr>
            <th>id</th>
            <th>Name</th>
            <th>Parameters</th>
            <th>Quantity</th>
            <th>Total cost</th>
          </tr>
        </thead>
        <tbody>
          {costData !== null ? (
            costData.spareParts.map((item, index) => (
              <tr key={index}>
                <td>{item.id}</td>
                <td>{item.sparePart.name}</td>
                <td>{item.sparePart.parameters}</td>
                <td>{item.quantity}</td>
                <td>{(item.sparePart.price * item.quantity).toFixed(2)} zł</td>
              </tr>
            ))
          ) : (
            <></>
          )}
        </tbody>
          </table>
      </div>
    </div>
      <div>
        <h2>Labor cost: {costData.laborPrice} zł</h2>
        <h2>Total cost: {costData.totalCost} zł</h2>
      </div>
    </div>
  );
};
export default TotalCostTable;
