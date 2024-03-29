import React, { useState, useEffect } from "react";
import Cookies from "js-cookie";
import { useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import car from "./car.jpg";


const CommissionDetails = () => {
  const [commissionDetails, setCommissionDetails] = useState({client:{}, contractor:{}, vehicle:{}});
  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    fetchCommissionDetails(id);
  }, [id]);

  const fetchCommissionDetails = async (commissionId) => {
    try {
      const response = await fetch(
        `${process.env.REACT_APP_URL}/api/v1/commissions/${commissionId}`,
        {
          headers: {
            Authorization: `Bearer ${Cookies.get("jwt")}`,
          },
        }
      );
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

  const handleShowCostEstimate = () => {
    navigate(`/cost-estimate/${id}`);
  };
  const handleShowCostTotal = () => {
    navigate(`/cost-total/${id}`);
  };

  const finishCommission = async ()=>{
    const bodyObject = {
      commissionStatus:'COMPLETED'
    }
    try {
      const response = await fetch(`${process.env.REACT_APP_URL}/api/v1/commissions/${id}`, {
        method: "PUT",
        headers: {
          "Content-Type": "Application/json",
          Authorization: `Bearer ${Cookies.get("jwt")}`,
        },
        body: JSON.stringify(bodyObject),
      });

      if (response.ok) {
        const data = await response.json();

        if (data !== null) {
        } else {
          console.warn("Received null data from the server");
        }
      } else {
        console.error("Failed to update commission", response.statusText);
      }
    } catch (error) {
      console.error("Network error:", error.message);
    }
  }

  return (
    <div className="commission-details">
      <div className="commission-details-row">
        <div>
          <button className="commission-status mb-4" onClick={()=> finishCommission()}>Finish Commission</button>
        </div>
          <button
            className="commission-status mb-4"
            onClick={() => handleShowCostTotal()}
          >
            Show total cost details
          </button>
          <button
            className="commission-status mb-4"
            onClick={() => handleShowCostEstimate()}
          >
            Show cost estimate details
          </button>
      </div>

      <div className="description">
        <div className="description-content">
          <b>ID Commission: {commissionDetails.id}</b>
          <p>Description: {commissionDetails.description}</p>
          <div>
          <h2>Client:</h2><p> {commissionDetails.client.name} {commissionDetails.client.surname} </p>
          </div>
          <div>
          <h2>Vehicle:</h2><p>{commissionDetails.vehicle.id}  {commissionDetails.vehicle.brand} {commissionDetails.vehicle.model} </p>
          </div>
          <div>
          <h2>Contractor:</h2><p> {commissionDetails.contractor.name} {commissionDetails.contractor.surname} </p>
          </div>
          <div>
          <h2>Create date:</h2><p> {new Date(commissionDetails.createDate).toLocaleString()} </p>
          </div>
        </div>
        <div className="image-container">
          <img src={car} alt="car image" />
        </div>
      </div>

      {commissionDetails.totalCost && (
        <div className="invoice-buttons">
          <button
            className="invoice-button"
            onClick={handleGenerateInvoiceSale}
          >
            Sales Invoice
          </button>
          <button className="invoice-button" onClick={handleGenerateInvoiceVAT}>
            VAT Invoice
          </button>
        </div>
      )}
    </div>
  );
};

export default CommissionDetails;
