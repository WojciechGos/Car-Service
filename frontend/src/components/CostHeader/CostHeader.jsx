import React, { useState } from "react";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import InputGroup from "react-bootstrap/InputGroup";
import { Link } from "react-router-dom";
import PATH from "../../paths";

const CostHeader = ({ setSelected, selected }) => {



  const buttonStyle = {
    marginTop: "10px",
    fontSize: "32px",
    fontFamily: "'Extra Bolt Italic', sans-serif",
  };

  const buttonStyleClient = {
    fontSize: "32px",
    marginTop: "10px",
    marginLeft: "700px",
    fontFamily: "'Extra Bolt Italic', sans-serif",
  };

  const changeTab = (endPoint) => {
    setSelected(endPoint)
  };

  return (
    <div>
      <Button variant={selected ==='creator' ? "danger": "light" } style={buttonStyle} onClick={()=>changeTab('creator')}>
        Cost Creator
      </Button>

      <Button variant={selected ==='details' ? "danger": "light" } style={buttonStyle} onClick={()=>changeTab('details')}>
        Cost details
      </Button>

      <br />
      <InputGroup className="inputSearch">
        <Form.Control
          placeholder="Enter name"
          aria-label="Enter name"
          aria-describedby="basic-addon2"
          //   value={searchName}
          //   onChange={(e) => setSearchName(e.target.value)}
        />
        <Button
          variant="secondary"
          id="button-addon2"
          // onClick={handleSearch}
        >
          Search
        </Button>
      </InputGroup>
      <br />
    </div>
  );
};

export default CostHeader;
