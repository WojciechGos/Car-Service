import React from "react"
import Button from "react-bootstrap/Button"
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import { Link } from "react-router-dom";
import PATH from "../../paths";


const HeaderClient = ()=>{

    const buttonStyle = {
        marginTop: "10px",
        fontSize: "32px", 
        fontFamily: "'Extra Bolt Italic', sans-serif"
      };

      const buttonStyleClient = {
        fontSize: "32px", 
        marginLeft: "300px",
        marginRight: "300px",
        fontFamily: "'Extra Bolt Italic', sans-serif"
      };


    return (
        <div >
            <Button variant="danger" style={buttonStyle} >Clients</Button>{' '}

            <Link to={PATH.VEHICLE}>
            <Button variant="light" style={buttonStyle}>Vehicles</Button>{' '}
            </Link>
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
            <Link to={PATH.CLIENTADD}>
              <Button variant="light" style={buttonStyle}>Add client</Button>
            </Link>
            <Button variant="light" style={buttonStyleClient}>Edit client</Button>{' '}
            <Button variant="light" style={buttonStyle}>Delete client</Button>{' '}

        </div>
    )
 
}
export default HeaderClient
