import React, { useState } from "react";
import Button from "react-bootstrap/Button";
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import { Link } from "react-router-dom";
import PATH from "../../paths";

const HeaderVehicle = ({ onFilterChange, onEditVehicle, onSaveEdit, isEditingVehicle }) => {
  const [searchBrand, setSearchBrand] = useState('');

  const handleSearch = () => {
    if (onFilterChange) {
      onFilterChange(searchBrand);
    }
  };

  const buttonStyle = {
    marginTop: "10px",
    fontSize: "32px", 
    fontFamily: "'Extra Bolt Italic', sans-serif"
  };

  return (
    <div>
      <Link to={PATH.CLIENT}>
        <Button variant="light" style={buttonStyle} >Clients</Button>
      </Link>
           
      <Button variant="danger" style={buttonStyle}>Vehicles</Button>
           
      <br/>
      
      <InputGroup className="inputSearch">
        <Form.Control
          placeholder="Enter brand"
          aria-label="Enter brand"
          aria-describedby="basic-addon2"
          value={searchBrand}
          onChange={(e) => setSearchBrand(e.target.value)}
        />
        <Button variant="secondary" id="button-addon2" onClick={handleSearch}>
          Search
        </Button>
      </InputGroup>
            
      <br />
      
      <Link to={PATH.VEHICLEADD}>
        <Button variant="light" style={buttonStyle}>Add vehicle</Button>
      </Link>
    </div>
  );
};

export default HeaderVehicle;
