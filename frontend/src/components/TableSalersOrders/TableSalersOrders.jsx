import React, { useState, useEffect } from "react";
import Cookies from "js-cookie";

const TableSalersOrders = ()=>{
  const [externalOrders, setExternalOrders] = useState([]);

  useEffect(() => {
    fetchExternalOrders();
  }, []);

  const fetchExternalOrders = async () => {
    try {
      const response = await fetch("http://localhost:5001/api/v1/order/external", {
        headers: {
          'Authorization': `Bearer ${Cookies.get("jwt")}`,
        },
      });
      if (response.ok) {
        const data = await response.json();
        setExternalOrders(data);
        console.log(data)
      } else {
        console.error("Failed to fetch external orders");
      }
    } catch (error) {
      console.error("Error fetching external orders:", error);
    }
  };

  const calculateTotalCost = (externalOrder) => {
    if (!externalOrder.items || externalOrder.items.length === 0) {
      return 0;
    }

    return externalOrder.items.reduce((total, item) => {
      return total + item.price * item.quantity;
    }, 0);
  };

  return (
    <div className="table-container">
      <div className="overflow-container5">
      <table className="custom-table">
        <thead>
          <tr>
            <th>Id</th>
            <th>Create date</th>
            <th>Contractor</th>
            <th>Order status</th>
            <th>Receive date</th>
            <th>Ordered items</th>
            <th>Total cost</th>
          </tr>
        </thead>
        <tbody>
          {externalOrders.map(externalOrder => (
            <tr
              key={externalOrder.id}
            >
              <td>{externalOrder.id}</td>
              <td>{new Date(externalOrder.createDate).toLocaleString()}</td>
              <td>{externalOrder.worker ? `${externalOrder.worker.name} ${externalOrder.worker.surname}` : '-'}</td>
              <td>{externalOrder.orderStatus}</td>
              <td>{externalOrder.receiveDate ? (externalOrder.receiveDate) : '-'}</td>
              <td>
              {externalOrder.items ? (
                <ul>
                  {externalOrder.items.map((item, index) => (
                    <li key={index}>
                      {`${item.sparePartName} (${item.quantity})`}
                    </li>
                  ))}
                </ul>
              ) : '-'}
            </td>
            <td>{calculateTotalCost(externalOrder).toFixed(2)} zł</td>
            </tr>
          ))}
          </tbody>
          </table>
      </div>
    </div>
  );
}
export default TableSalersOrders
