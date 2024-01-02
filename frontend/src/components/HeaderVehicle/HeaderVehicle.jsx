import React from "react"
import Button from "react-bootstrap/Button"
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import { Link } from "react-router-dom";
import PATH from "../../paths";


const HeaderVehicle = ()=>{

    const buttonStyle = {
        marginTop: "10px",
        fontSize: "32px", 
        fontFamily: "'Extra Bolt Italic', sans-serif"
      };

      const buttonStyleVehicle = {
        fontSize: "32px", 
        marginLeft: "260px",
        marginRight: "260px",
        fontFamily: "'Extra Bolt Italic', sans-serif"
      };


    return (
        <div >
            <Link to={PATH.CLIENT}>
            <Button variant="light" style={buttonStyle} >Clients</Button>{' '}
            </Link>
           
            <Button variant="danger" style={buttonStyle}>Vehicles</Button>{' '}
           
            <br/>


            <InputGroup className="inputSearch">
        <Form.Control
          placeholder="Enter name"
          aria-label="Enter name"
          aria-describedby="basic-addon2"
        />
        <Button variant="secondary" id="button-addon2">
          Search
        </Button>
      </InputGroup>
            
            <br />
            <Button variant="light" style={buttonStyle}>Add vehicle</Button>{' '}
            <Button variant="light" style={buttonStyleVehicle}>Edit vehicle</Button>{' '}
            <Button variant="light" style={buttonStyle}>Delete vehicle</Button>{' '}

        </div>
    )
 
}
export default HeaderVehicle
