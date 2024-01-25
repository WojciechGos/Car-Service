import React from "react"
import Button from "react-bootstrap/Button"
import { Link } from "react-router-dom";
import PATH from "../../paths";

const HeaderOrder = ()=>{

    const buttonStyle = {
        marginTop: "10px",
        fontSize: "32px", 
        fontFamily: "'Extra Bolt Italic', sans-serif",
        
      };

    return (
        <div >
             <Link to={PATH.WAREHOUSE}>
            <Button variant="light" style={buttonStyle} >List</Button>
            </Link>
            <Button variant="danger" style={buttonStyle}>Orders</Button>
            <Link to={PATH.CARTWAREHOUSE}>
             <Button variant="light" style={buttonStyle}>Cart</Button>
            </Link>
            <br/>
            <div style={{height: "60px"}}/>
        </div>
    )
 
}
export default HeaderOrder