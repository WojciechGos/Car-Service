import React from "react";
import Button from 'react-bootstrap/Button';
import { Link } from "react-router-dom";
import PATH from "../../paths";

const Sidebar = ()=>{
    return (
         <div className="slideBarContainer">

            <h4 className="whiteWritings">
                Car Service
            </h4>

            <br />
            <Link to={PATH.CLIENT}>
                <Button variant="outline-light">Clients</Button>{' '}
            </Link>
            
            <br />
            <Link to={PATH.WAREHOUSE}>
                <Button variant="outline-light">Warehouse</Button>{' '}
            </Link>

            <br />
            <Link to={PATH.COMMISSION}>
                <Button variant="outline-light">Commission</Button>{' '}
            </Link>
        </div>
 
    )
}
export default Sidebar