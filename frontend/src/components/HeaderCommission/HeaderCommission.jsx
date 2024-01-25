
import React, { useState } from "react";
import Button from "react-bootstrap/Button";
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import Cookies from "js-cookie";
import { Link } from "react-router-dom";
import PATH from "../../paths";

const HeaderCommission = ({ selectedCommissionId, onFilterChange,rerender,setrerender }) => {
  const [searchName, setSearchName] = useState('');

  const buttonStyle = {
    marginTop: "0px",
    fontSize: "32px",
    fontFamily: "'Extra Bolt Italic', sans-serif"
  };

  const handleDeleteClick = async () => {
    try {
      if (selectedCommissionId !== null) {
        const response = await fetch(`http://localhost:5001/api/v1/commissions/${selectedCommissionId}`, {
          method: "DELETE",
          headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${Cookies.get("jwt")}`
          },
        });

        if (response.ok) {
          setrerender(!rerender);
        } else {
          console.error("Server error:", response.status);
        }
      } else {
        console.warn("No commission selected for deletion.");
      }
    } catch (error) {
      console.error("Error during deleting commission", error);
    }
  };

  const handleSearch = () => {
    onFilterChange(searchName);
  };

  return (
    <div>
      <InputGroup className="inputSearch">
        <Form.Control
          placeholder="Enter name"
          aria-label="Enter name"
          aria-describedby="basic-addon2"
          value={searchName}
          onChange={(e) => setSearchName(e.target.value)}
        />
        <Button variant="secondary" id="button-addon2" onClick={handleSearch}>
          Search
        </Button>
      </InputGroup>

      <br />
      <br />
      <Link to={PATH.COMMISSIONADD}>
        <Button variant="light" style={buttonStyle} >Add commission</Button>
      </Link>
      <Button variant="light" style={{ fontSize: "32px", marginLeft: "150px", marginRight: "150px", fontFamily: "'Extra Bolt Italic', sans-serif" }}>Edit commission</Button>{' '}
      <Button variant="light" style={buttonStyle} onClick={handleDeleteClick}>
        Delete commission
      </Button>{' '}
    </div>
  );
};

export default HeaderCommission;
