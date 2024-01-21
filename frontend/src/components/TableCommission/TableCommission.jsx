import React, { useState, useEffect } from "react";
import Cookies from "js-cookie";
import { useNavigate } from "react-router-dom";

const TableCommission = () => {
  const [commissions, setCommissions] = useState([]);
  const [selectedCommissionId, setSelectedCommissionId] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    fetchCommissions();
  }, []);

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
        console.log(data);
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
          {commissions.map((commission) => (
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