import Table from 'react-bootstrap/Table';
import React, { useState, useEffect } from "react";
import Cookies from "js-cookie";

const TableOrder = ()=>{
  const [localOrders, setLocalOrders] = useState([]);
  const [selectedLocalOrderId, setSelectedLocalOrderId] = useState(null)

  useEffect(() => {
    fetchLocalOrders();
  }, []);

  const fetchLocalOrders = async () => {
    try {
      const response = await fetch("http://localhost:5001/api/v1/order/local", {
        headers: {
          'Authorization': `Bearer ${Cookies.get("jwt")}`,
        },
      });
      if (response.ok) {
        const data = await response.json();

        console.log(data);
  
        setLocalOrders(data);
      } else {
        console.error("Failed to fetch local orders");
      }
    } catch (error) {
      console.error("Error fetching local orders:", error);
    }
  };

    const calculateTotalCost = (localOrder) => {
      if (!localOrder.orderSparePartList || localOrder.orderSparePartList.length === 0) {
        return 0;
      }

      return localOrder.orderSparePartList.reduce((total, sparePart) => {
        return total + sparePart.price * sparePart.quantity;
      }, 0);
    };

    return (

        <Table className="grayTable" bordered hover variant="secondary" style={{ marginTop: "10px" }}>
        <thead>
          <tr>
            <th>id</th>
            <th>Create date</th>
            <th>Commission ID</th>
            <th>Contractor</th>
            <th>Order status</th>
            <th>Receive date</th>
            <th>Ordered spare parts</th>
            <th>Total cost</th>
          </tr>
        </thead>
        <tbody>
        {localOrders.map((localOrder) => (
            <tr
              key={localOrder.id}
              style={{
                cursor: "pointer",
                backgroundColor: selectedLocalOrderId === localOrder.id ? "#f2f2f2" : "transparent",
              }}
            >
              <td>{localOrder.id}</td>
              <td>{new Date(localOrder.createDate).toLocaleString()}</td>
              <td>{localOrder.commission ? (localOrder.commission.id || '-') : '-'} </td>
              <td>{localOrder.worker ? `${localOrder.worker.name} ${localOrder.worker.surname}` : '-'}</td>
              <td>{localOrder.orderStatus}</td>
              <td>{localOrder.receiveDate ? (localOrder.receiveDate) : '-'}</td>
              <td>
              {localOrder.orderSparePartList ? (
                <ul>
                  {localOrder.orderSparePartList.map((sparePart, index) => (
                    <li key={index}>
                      {`${sparePart.sparePartName} (${sparePart.quantity})`}
                    </li>
                  ))}
                </ul>
              ) : '-'}
            </td>
            <td>{calculateTotalCost(localOrder).toFixed(2)} zł</td>
            </tr>
          ))}
        </tbody>
      </Table>
           

       
    )
 
}
export default TableOrder