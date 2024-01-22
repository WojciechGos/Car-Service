import React, { useState, useEffect } from "react";
import Cookies from "js-cookie";
import { useParams } from "react-router-dom";
import  "../../App.css";
import { useNavigate } from "react-router-dom";

const CommissionDetails = () => {
  const [commissionDetails, setCommissionDetails] = useState({});
  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    fetchCommissionDetails(id);
  }, [id]);

  const fetchCommissionDetails = async (commissionId) => {
    try {
      const response = await fetch(`http://localhost:5001/api/v1/commissions/${commissionId}`, {
        headers: {
          'Authorization': `Bearer ${Cookies.get("jwt")}`,
        },
      });
      if (response.ok) {
        const data = await response.json();
        setCommissionDetails(data);
        console.log(data);
      } else {
        console.error("Failed to fetch commission details");
      }
    } catch (error) {
      console.error("Error fetching commission details:", error);
    }
  };

  const handleGenerateInvoiceSale = () => {
    navigate(`/invoice/Sales/${id}`);
  };

  const handleGenerateInvoiceVAT = () => {
    navigate(`/invoice/VAT/${id}`);
  };

  return (
      <div className="commission-details">
        <div className="IdCommission">
          <p><b>ID Commission: {commissionDetails.id}</b></p>
        </div>
        <div className="commission-details-row">
          <p><b>Commission status: {commissionDetails.commissionStatus}</b></p>
          <p><b>Total cost: {commissionDetails.totalCost ? commissionDetails.totalCost.totalCost : '-'}</b></p>
          <p><b>Payment status: Paid</b></p>
        </div>
        <div className="description">
          <p>Description: {commissionDetails.description}</p>
        </div>
        {commissionDetails.totalCost && (
          <div className="invoice-buttons">
            <button className="invoice-button" onClick={handleGenerateInvoiceSale}>Sales Invoice</button>
            <button className="invoice-button" onClick={handleGenerateInvoiceVAT}>VAT Invoice</button>
          </div>
        )}

      </div>
      
  );
};

export default CommissionDetails;
