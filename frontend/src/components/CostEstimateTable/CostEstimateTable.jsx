import Table from "react-bootstrap/Table";
import React, { useState, useEffect, useContext } from "react";
import Cookies from "js-cookie";
import { jwtDecode } from "jwt-decode";
import CostEstimateContext from "../../context/CostEstimateContext";
import Button from "react-bootstrap/esm/Button";
import Form from 'react-bootstrap/Form';


const CostEstimateTable = ({ commissionId, showSaveButton }) => {
  

  const { costData, sparePartList, acceptCostEstimate, getSpareParts, laborCost, onlaborCostChange } = useContext(CostEstimateContext);
  const [totalCost, setTotalCost] = useState(0)
  console.log(sparePartList);

  const buttonStyle = {
    marginTop: "10px",
    fontSize: "32px",
    fontFamily: "'Extra Bolt Italic', sans-serif",
    backgroundColor: "red",
  };

  const calculateTotal = ()=>{
    console.log("calculateTotal")
    console.log(sparePartList)
    const tmp = sparePartList.reduce((acc, sparePart) => {
      console.log("sparePart")
      console.log(sparePart)
      return acc + sparePart.quantity * sparePart.sparePart.price;
    }, 0);
    if(showSaveButton)
      setTotalCost(tmp + parseInt(laborCost))
    else
      setTotalCost(tmp + parseInt(costData.laborPrice))
  }
  useEffect(() => {
    getSpareParts(commissionId);
    calculateTotal()
    console.log("costData")
    console.log(costData)
  }, [sparePartList,]);

  console.log("sparePartList");
  console.log(sparePartList);

  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        alignItems: "flex-end",
      }}
    >
      {/* <div className="d-flex justify-content-around w-100">
        <h2>Name: {costData.name} </h2>
      </div> */}

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
              {/* {costData !== null ? (
                sparePartList.map((item, index) => (
                  <tr key={index}>
                    <td>{item.id}</td>
                    <td>{item.sparePart.name}</td>

                    <td className="parametrsView2">
                      {item.sparePart.parameters}
                    </td>

                    <td>{item.quantity}</td>
                    <td>
                      {(item.sparePart.price * item.quantity).toFixed(2)} zł
                    </td>
                  </tr>
                ))
              ) : (
                <>czemu</>
              )} */}
              {
                sparePartList.map((item, index) => (
                  <tr key={index}>
                    <td>{item.id}</td>
                    <td>{item.sparePart.name}</td>

                    <td className="parametrsView2">
                      {item.sparePart.parameters}
                    </td>

                    <td>{item.quantity}</td>
                    <td>
                      {(item.sparePart.price * item.quantity).toFixed(2)} zł
                    </td>
                  </tr>
                ))
                }
            </tbody>
          </table>
        </div>
      </div>
      <div>
        {showSaveButton === true ? (
          <div>
            <Form>
              <h4>
                Labor Cost zł
                </h4>
              <Form.Control
                type="number"
                name="laborCost"
                value={laborCost}
                onChange={onlaborCostChange}

              />
            </Form>
          </div>
        ) : (
          
          <h4>Labor cost: {costData.laborPrice} zł</h4>
        )}
        <h3>Total cost: {totalCost} zł</h3>
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
