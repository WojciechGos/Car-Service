import React from "react"
import Button from "react-bootstrap/Button"
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import { Link } from "react-router-dom";
import PATH from "../../paths";

const HeaderCartWarehouse = ()=>{

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
            <Link to={PATH.ORDER}>
              <Button variant="light" style={buttonStyle}>Orders</Button>
            </Link>
            <Button variant="danger" style={buttonStyle}>Cart</Button>
            <br/>
            <div style={{height: "60px"}}/>

        </div>
    )
 
}
export default HeaderCartWarehouse