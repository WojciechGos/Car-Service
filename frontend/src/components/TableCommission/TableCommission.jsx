
import React, { useState, useEffect } from "react";
import Cookies from "js-cookie";
import { useNavigate } from "react-router-dom";

const TableCommission = ({ selectedCommissionId, setSelectedCommissionId, filterText,rerender,setrerender }) => {
  const [commissions, setCommissions] = useState([]);
  const navigate = useNavigate();

  const [sortColumn, setSortColumn] = useState(null);
  const [sortOrder, setSortOrder] = useState("asc");

  useEffect(() => {
    addTotalCost();
    fetchCommissions();
  }, [rerender,sortColumn, sortOrder, filterText]);

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

      if (!response.ok) {
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
        
        const filteredData = data.filter((commission) => {
          const commissionFields = Object.keys(commission);
          return commissionFields.some((field) =>
            (field !== 'id' && field !== 'createDate' && field !== 'endDate' &&
            field !== 'vehicle' && field !== 'client' &&
            commission[field] && commission[field].toString().toLowerCase().includes(filterText.toLowerCase())) ||
            (field === 'contractor' && commission[field] && commission[field].name.toLowerCase().includes(filterText.toLowerCase()))
          );
        });
  

        const sortedData = filteredData.sort((a, b) => {
          const valueA = getNestedPropertyValue(a, sortColumn);
          const valueB = getNestedPropertyValue(b, sortColumn);
        
          if (valueA === null || valueB === null) {
            return 0; 
          }
        
          if (typeof valueA === 'string') {
            const lowerA = valueA.toLowerCase();
            const lowerB = valueB.toLowerCase();
            return sortOrder === 'asc' ? lowerA.localeCompare(lowerB) : lowerB.localeCompare(lowerA);
          } else if (typeof valueA === 'number') {
            return sortOrder === 'asc' ? valueA - valueB : valueB - valueA;
          } else {
            return sortOrder === 'asc' ? valueA.toString().localeCompare(valueB.toString()) : valueB.toString().localeCompare(valueA.toString());
          }
        });
        

        setCommissions(sortedData);
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

  const handleSort = (column) => {
    if (sortColumn === column) {
      setSortOrder((prevOrder) => (prevOrder === "asc" ? "desc" : "asc"));
    } else {
      setSortColumn(column);
      setSortOrder("asc");
    }
  };

  const renderHeaderCell = (label, column) => (
    <th
      key={column}
      onClick={() => handleSort(column)}
      style={{
        cursor: "pointer",
        textDecoration: sortColumn === column ? "underline" : "none",
      }}
    >
      {label}
      {sortColumn === column && (
        <span style={{ marginLeft: "5px" }}>
          {sortOrder === "asc" ? "▲" : "▼"}
        </span>
      )}
    </th>
  );

  const getNestedPropertyValue = (object, propertyPath) => {
    if (!object || !propertyPath) {
      return null;
    }
  
    const properties = propertyPath.split('.');
  
    return properties.reduce((obj, prop) => (obj && obj[prop] !== undefined ? obj[prop] : null), object);
  };
  

  return (
    <div className="table-container">
      <table className="custom-table">
        <thead>
          <tr>
            {renderHeaderCell("ID", "id")}
            {renderHeaderCell("Create date", "createDate")}
            {renderHeaderCell("End date", "endDate")}
            {renderHeaderCell("ID vehicle", "vehicle.id")}
            {renderHeaderCell("ID client", "client.id")}
            {renderHeaderCell("Cost estimate", "costEstimate")}
            {renderHeaderCell("Contractor", "contractor.name")}
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
              <td>{new Date(commission.createDate).toLocaleString()}</td>
              {commission.endDate ? (
                <td>{new Date(commission.endDate).toLocaleString()}</td>
              ) : (
                <td></td>
              )}
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
