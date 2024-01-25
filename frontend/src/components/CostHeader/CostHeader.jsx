import React, { useState } from "react";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import InputGroup from "react-bootstrap/InputGroup";
import { Link, useParams } from "react-router-dom";
import PATH from "../../paths";

const CostHeader = ({ setSelected, selected, showChoice }) => {
  const { id } = useParams();
  const buttonStyle = {
    marginTop: "10px",
    fontSize: "32px",
    fontFamily: "'Extra Bolt Italic', sans-serif",
    marginRight:"20px",
  };

  const containerStyle = {
    display: "flex",
    alignItems: "center", 
  };

  const changeTab = (endPoint) => {
    setSelected(endPoint);
  };

  return (
    <div style={containerStyle}>
      <Link to={`${PATH.DETAILS}/${id}`}>
        <Button variant="light" style={buttonStyle}>
          Back
        </Button>
      </Link>
      {showChoice === true ? (
        <div>
          <Button
            variant={selected === "creator" ? "danger" : "light"}
            style={buttonStyle}
            onClick={() => changeTab("creator")}
          >
            Cost Creator
          </Button>

          <Button
            variant={selected === "details" ? "danger" : "light"}
            style={buttonStyle}
            onClick={() => changeTab("details")}
          >
            Cost details
          </Button>
        </div>
      ) : (
        <></>
      )}
    </div>
  );
};

export default CostHeader;
