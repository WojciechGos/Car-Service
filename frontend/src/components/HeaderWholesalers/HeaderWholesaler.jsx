import React from "react"
import Button from "react-bootstrap/Button"
import { Link } from "react-router-dom";
import PATH from "../../paths";


const HeaderWholesalers = ()=>{

    const buttonStyle = {
        marginTop: "10px",
        fontSize: "32px", 
        fontFamily: "'Extra Bolt Italic', sans-serif",
        
      };

    return (
        <div >
             <Link to={PATH.WHOLESALERS}>
            <Button variant="danger" style={buttonStyle} >Salers</Button>
            </Link>
            <Link to={PATH.SALERSORDERS}>
            <Button variant="light" style={buttonStyle}>Orders</Button>
            </Link>
            <Link to={PATH.CART}>
            <Button variant="light" style={buttonStyle}>Cart</Button>
            </Link>
           
        </div>
    )
 
}
export default HeaderWholesalers
