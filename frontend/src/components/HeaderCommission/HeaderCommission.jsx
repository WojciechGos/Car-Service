import React from "react"
import Button from "react-bootstrap/Button"
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';


const HeaderCommission = ()=>{

    const buttonStyle = {
        marginTop: "0px",
        fontSize: "32px", 
        fontFamily: "'Extra Bolt Italic', sans-serif"
      };

      const buttonStyleCommission = {
        fontSize: "32px", 
        marginLeft: "150px",
        marginRight: "150px",
        fontFamily: "'Extra Bolt Italic', sans-serif"
      };


    return (
        <div >
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
           
            <p style={buttonStyle}>Filters:</p>
            <Button variant="light" style={buttonStyle}>id</Button>{' '}
            <Button variant="light" style={buttonStyle}>id Vehicle</Button>{' '}
            <Button variant="light" style={buttonStyle}>id client</Button>{' '}
         

            <br />
            <Button variant="light" style={buttonStyle}>Add commission</Button>{' '}
            <Button variant="light" style={buttonStyleCommission}>Edit commission</Button>{' '}
            <Button variant="light" style={buttonStyle}>Delete commission</Button>{' '}

        </div>
    )
 
}
export default HeaderCommission
