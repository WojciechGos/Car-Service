import React from "react"
import Button from "react-bootstrap/Button"
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';

const Footer = ()=>{

    const buttonStyle = {
        marginTop: "10px",
        fontSize: "32px", 
        fontFamily: "'Extra Bolt Italic', sans-serif"
      };

      const buttonStyleClient = {
        marginBottom: "10px",
        fontSize: "32px", 
        marginLeft: "300px",
        marginRight: "300px",
        fontFamily: "'Extra Bolt Italic', sans-serif"
      };


    return (
        <div >
            <Button variant="danger" style={buttonStyle} >Clients</Button>{' '}
            <Button variant="light" style={buttonStyle}>Vehicles</Button>{' '}
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
            <Button variant="light" style={buttonStyle}>Add client</Button>{' '}
            <Button variant="light" style={buttonStyleClient}>Edit client</Button>{' '}
            <Button variant="light" style={buttonStyle}>Delete client</Button>{' '}

        </div>
    )
 
}
export default Footer
