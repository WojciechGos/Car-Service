import React, { useRef, useState } from 'react';
import { useParams } from 'react-router-dom';
import Cookies from "js-cookie";
import Button from 'react-bootstrap/Button';
import { Link } from "react-router-dom";
import PATH from "../paths";

const Invoice = () => {
  const { type, id } = useParams();
  const invoiceRef = useRef();
  const [generatedHTML, setGeneratedHTML] = useState(null);

  const buttonStyle = {
    marginTop: "10px",
    fontSize: "26px", 
    fontFamily: "'Extra Bolt Italic', sans-serif"
  };

  const generatePDF = async () => {
    try {
      const url = type === 'Sales'
        ? `http://localhost:5001/api/v1/invoices/sale/generate/pdf/${id}`
        : type === 'VAT'
          ? `http://localhost:5001/api/v1/invoices/vat/generate/pdf/${id}`
          : '';

      if (!url) {
        console.error('Invalid invoice type');
        return;
      }

      const response = await fetch(url, {
        headers: {
          'Authorization': `Bearer ${Cookies.get("jwt")}`,
        },
      });

      if (response.ok) {
        const blob = await response.blob();

        const url = window.URL.createObjectURL(new Blob([blob]));
        const link = document.createElement('a');
        link.href = url;
        link.setAttribute('download', 'invoice.pdf');

        document.body.appendChild(link);
        link.click();

        document.body.removeChild(link);
      } else {
        console.error("Failed to generate PDF Invoice");
      }
    } catch (error) {
      console.error("Error generating PDF invoice:", error);
    }
  };

  const generateHTML = async () => {
    try {
      const url = type === 'Sales'
        ? `http://localhost:5001/api/v1/invoices/sale/generate/html/${id}`
        : type === 'VAT'
          ? `http://localhost:5001/api/v1/invoices/vat/generate/html/${id}`
          : '';

      if (!url) {
        console.error('Invalid invoice type');
        return;
      }

      const response = await fetch(url, {
        headers: {
          'Authorization': `Bearer ${Cookies.get("jwt")}`,
        },
      });

      if (response.ok) {
        const htmlContent = await response.text();
        setGeneratedHTML(htmlContent);
      } else {
        console.error("Failed to generate HTML Invoice");
      }
    } catch (error) {
      console.error("Error generating HTML invoice:", error);
    }
  };

  return (
    <div ref={invoiceRef} className="invoice-container">
      <h2 className="invoice-title">Invoice Generator</h2>
      <p className="invoice-type">Invoice Type: {type} Invoice</p>
      <div className="button-container">
        <button className="invoice-button2" onClick={generatePDF}>Generate PDF</button>
        <button className="invoice-button2" onClick={generateHTML}>Generate HTML</button>
      </div>
      {generatedHTML && (
        <div className="generated-html-container">
           <h3>Generated {type === 'Sales' ? 'Sales' : type === 'VAT' ? 'VAT' : ''} Invoice</h3>
          <div dangerouslySetInnerHTML={{ __html: generatedHTML }} />
        </div>
      )}
       <Link to={`${PATH.DETAILS}/${id}`}>
        <Button variant="light" style={buttonStyle}>Back</Button>{' '}
      </Link>
    </div>
  );
};

export default Invoice;
