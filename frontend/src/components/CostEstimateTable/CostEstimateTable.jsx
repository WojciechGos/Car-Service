import Table from "react-bootstrap/Table";
import React, { useState, useEffect, useContext } from "react";
import Cookies from "js-cookie";
import { jwtDecode } from "jwt-decode";
import CostEstimateContext from "../../context/CostEstimateContext";
import Button from "react-bootstrap/esm/Button";

const CostEstimateTable = ({ commissionId, showSaveButton }) => {
  const { costData, sparePartList, acceptCostEstimate } =
    useContext(CostEstimateContext);
  console.log(sparePartList);

  const buttonStyle = {
    marginTop: "10px",
    fontSize: "32px",
    fontFamily: "'Extra Bolt Italic', sans-serif",
    backgroundColor: "red",
  };

  useEffect(() => {}, []);

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

      <Table
        className="grayTable"
        bordered
        hover
        variant="secondary"
        style={{ marginTop: "10px" }}
      >
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
            sparePartList.map((item, index) => (
              <tr key={index}>
                <td>{item.id}</td>
                <td>{item.name}</td>
                <td>{item.parameters}</td>
                <td>{item.quantity}</td>
                <td>{(item.price * item.quantity).toFixed(2)} zł</td>
              </tr>
            ))
          ) : (
            <></>
          )}
        </tbody>
      </Table>
      <div>
        <h4>Labor cost: {costData.laborPrice} zł</h4>
        <h3>Total cost: {costData.totalCost} zł</h3>
      </div>

      {showSaveButton === true ? (
        <Button
          variant="light"
          style={buttonStyle}
          onClick={() => {
            acceptCostEstimate(commissionId);
          }}
        >
          Save
        </Button>
      ) : (
        <></>
      )}
    </div>
  );
};
export default CostEstimateTable;
