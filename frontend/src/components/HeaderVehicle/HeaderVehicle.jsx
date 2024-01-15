import React, { useState } from "react";
import Button from "react-bootstrap/Button";
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import { Link } from "react-router-dom";
import PATH from "../../paths";

const HeaderVehicle = ({ onFilterChange, onEditVehicle, onSaveEdit, isEditingVehicle }) => {
  const [searchBrand, setSearchBrand] = useState('');
  const [editedVehicleId, setEditedVehicleId] = useState(null);
  const [editedVehicleBrand, setEditedVehicleBrand] = useState("");

  const handleSearch = () => {
    if (onFilterChange) {
      onFilterChange(searchBrand);
    }
  };

  const handleToggleEdit = () => {
    if (onEditVehicle) {
      onEditVehicle(!isEditingVehicle); // Przełącz isEditingVehicle
      // Zresetuj pola edycji przy przełączaniu
      setEditedVehicleId(null);
      setEditedVehicleBrand("");
    }
  };

  const handleSaveEdit = () => {
    if (onSaveEdit) {
      onSaveEdit(editedVehicleId, editedVehicleBrand);
      // Po zapisaniu, zresetuj pola edycji
      setEditedVehicleId(null);
      setEditedVehicleBrand("");
    }
  };

  const buttonStyle = {
    marginTop: "10px",
    fontSize: "32px", 
    fontFamily: "'Extra Bolt Italic', sans-serif"
  };

  const buttonStyleVehicle = {
    fontSize: "32px", 
    marginTop: "10px",
    marginLeft:"650px",
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
      
      <Button variant="light" style={buttonStyleVehicle} onClick={handleToggleEdit}>
        {isEditingVehicle ? 'Cancel Edit' : 'Edit Vehicle'}
      </Button>{' '}

    </div>
  );
};

export default HeaderVehicle;
