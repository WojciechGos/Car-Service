import React from "react"
import Button from "react-bootstrap/Button"
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import { Link } from "react-router-dom";
import PATH from "../../paths";

const HeaderOrder = ()=>{

    const buttonStyle = {
        marginTop: "10px",
        fontSize: "32px", 
        fontFamily: "'Extra Bolt Italic', sans-serif",
        
      };

      const buttonStyleWarehouse = {
        fontSize: "32px", 
        marginLeft: "500px",
        fontFamily: "'Extra Bolt Italic', sans-serif"
      };


    return (
        <div >
             <Link to={PATH.WAREHOUSE}>
            <Button variant="light" style={buttonStyle} >List</Button>
            </Link>
            <Button variant="danger" style={buttonStyle}>Order</Button>
            <br/>

            <div style={{display: "flex"}}>
            <InputGroup className="inputSearch">

                <Form.Control
                placeholder="Enter name"
                aria-label="Enter name"
                aria-describedby="basic-addon2"
             />
            <Button variant="secondary" id="button-addon2">Search</Button>

            </InputGroup>
            
            <Button variant="secondary" style={buttonStyleWarehouse} >delete</Button>
            </div>

        </div>
    )
 
}
export default HeaderOrder