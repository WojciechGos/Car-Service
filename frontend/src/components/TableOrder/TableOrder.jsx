import React, { useState, useEffect } from "react";
import Cookies from "js-cookie";

const TableOrder = ({filterText} )=>{
  const [localOrders, setLocalOrders] = useState([]);
  const [selectedLocalOrderId, setSelectedLocalOrderId] = useState(null)
  const [selectedLocalOrderStatus, setSelectedLocalOrderStatus] = useState({});

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

        const initialSelectedOrderStatus = {};
        data.forEach(order => {
          initialSelectedOrderStatus[order.id] = order.orderStatus;
        });
        setSelectedLocalOrderStatus(initialSelectedOrderStatus);
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

    const handleOrderStatusChange = async (orderId, newStatus) => {
      try {
        const response = await fetch(`http://localhost:5001/api/v1/order/local/${orderId}`, {
          method: "PUT",
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${Cookies.get("jwt")}`,
          },
          body: JSON.stringify({ orderStatus: newStatus }),
        });
  
        if (response.ok) {
          setSelectedLocalOrderStatus({
            ...selectedLocalOrderStatus,
            [orderId]: newStatus,
          });
          fetchLocalOrders();
        } else {
          console.error(`Failed to update order status for order ${orderId}`);
        }
      } catch (error) {
        console.error("Error updating order status:", error);
      }
    };

    return (
      <div className="table-container">
        <div className="overflow-container5">
        <table className="custom-table">
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
              <td>
                  {/* Dropdown for Order status */}
                  <select
                    value={selectedLocalOrderStatus[localOrder.id]}
                    onChange={(e) => handleOrderStatusChange(localOrder.id, e.target.value)}
                  >
                    {Object.values(OrderStatus).map((status) => (
                      <option key={status} value={status}>
                        {status}
                      </option>
                    ))}
                  </select>
                </td>
              <td>{localOrder.receiveDate ? (new Date(localOrder.createDate).toLocaleString()) : '-'}</td>
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
        </table>
      </div>
      </div>
    )
 
};

const OrderStatus = {
  CREATING: "CREATING",
  IN_PROGRESS: "IN_PROGRESS",
  COMPLETED: "COMPLETED",
  CANCELLED: "CANCELLED",
};

export default TableOrder