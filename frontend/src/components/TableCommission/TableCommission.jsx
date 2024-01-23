import React, { useState, useEffect } from "react";
import Cookies from "js-cookie";
import { useNavigate } from "react-router-dom";

const TableCommission = ({ selectedCommissionId, setSelectedCommissionId, filterText, activeFilter }) => {
  const [commissions, setCommissions] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    addTotalCost();
    fetchCommissions();
  }, []);

  const addTotalCost = async () => {
    try {
      const requestData = {
        commissionId: 1,
        costType: "total",
        name: "Service123",
        createDate: "2023-01-01",
        sparePartQuantities: {
          "1": 10,
          "2": 5,
          "3": 8
        },
        laborPrice: 100.00
      };
  
      const response = await fetch("http://localhost:5001/api/v1/costs", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${Cookies.get("jwt")}`
        },
        body: JSON.stringify(requestData),
      });
  
      if (response.ok) {
        const data = await response.json();
        // Możesz tutaj coś zrobić z odpowiedzi, jeśli to konieczne
      } else {
        console.error("Server error:", response.status);
      }
    } catch (error) {
      console.error("Error during adding cost", error);
    }
  };

  const fetchCommissions = async () => {
    try {
      const response = await fetch("http://localhost:5001/api/v1/commissions", {
        headers: {
          'Authorization': `Bearer ${Cookies.get("jwt")}`,
        },
      });
      if (response.ok) {
        const data = await response.json();
        setCommissions(data);
      } else {
        console.error("Failed to fetch commissions");
      }
    } catch (error) {
      console.error("Error fetching commissions:", error);
    }
  };

  const handleCommissionClick = (commissionId) => { 
    if (selectedCommissionId === commissionId) {
      navigate(`/CommissionDetails/${commissionId}`);
    } else {
      setSelectedCommissionId(commissionId);
    }
  };

  const filteredCommissions = commissions.filter((commission) => {

    console.log(activeFilter)
    const vehicleId = commission.vehicle?.id?.toString() || '';
    const clientId = commission.client?.id?.toString() || '';

    let filterCondition = true;

    switch (activeFilter) {
      case 'id':
        filterCondition = commission.id?.toString().includes(filterText);
        break;
      case 'idVehicle':
        filterCondition = vehicleId.includes(filterText);
        break;
      case 'idClient':
        filterCondition = clientId.includes(filterText);
        break;
      default:
        filterCondition =
          vehicleId.includes(filterText) ||
          clientId.includes(filterText) ||
          commission.costEstimate?.toString().includes(filterText) ||
          (commission.contractor.name + " " + commission.contractor.surname).toLowerCase().includes(filterText.toLowerCase());
        break;
    }

    return filterCondition;
  });

  return (
    <div className="table-container">
      <table className="custom-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Create date</th>
            <th>End date</th>
            <th>ID vehicle</th>
            <th>ID client</th>
            <th>Cost estimate</th>
            <th>Contractor</th>
          </tr>
        </thead>
        <tbody>
          {filteredCommissions.map((commission) => (
            <tr
              key={commission.id}
              onClick={() => handleCommissionClick(commission.id)}
              style={{
                cursor: "pointer",
                backgroundColor: selectedCommissionId === commission.id ? "#f2f2f2" : "transparent",
              }}
            >
              <td>{commission.id}</td>
              <td>{commission.createDate instanceof Date ? commission.createDate.toLocaleString() : commission.createDate}</td>
              <td>{commission.endDate instanceof Date ? commission.endDate.toLocaleString() : commission.endDate}</td>
              <td>{commission.vehicle.id}</td>
              <td>{commission.client.id}</td>
              <td>{commission.costEstimate}</td>
              <td>{commission.contractor.name} {commission.contractor.surname}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default TableCommission;
