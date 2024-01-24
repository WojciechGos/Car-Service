import React, { useState } from "react"
import Button from "react-bootstrap/Button"
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import { Link } from "react-router-dom";
import PATH from "../../paths";


const HeaderWarehouse = ({ onFilterChange })=>{
    const [searchName, setSearchName] = useState('');

    const buttonStyle = {
        marginTop: "10px",
        fontSize: "32px", 
        fontFamily: "'Extra Bolt Italic', sans-serif"
      };

      const buttonStyleWarehouse = {
        fontSize: "32px", 
        marginLeft: "500px",
        fontFamily: "'Extra Bolt Italic', sans-serif"
      };

      const handleSearch = () => {
        onFilterChange(searchName);
      };

    return (
        <div >
            <Button variant="danger" style={buttonStyle} >List</Button>
           
            <Link to={PATH.ORDER}>
            <Button variant="light" style={buttonStyle}>Orders</Button>
            </Link>

            <Link to={PATH.CARTWAREHOUSE}>
             <Button variant="light" style={buttonStyle}>Cart</Button>
            </Link>
            <br/>

            <div style={{display: "flex"}}>
            <InputGroup className="inputSearch">

                <Form.Control
                placeholder="Enter name"
                aria-label="Enter name"
                aria-describedby="basic-addon2"
                value={searchName}
                onChange={(e) => setSearchName(e.target.value)}
             />
            <Button variant="secondary" id="button-addon2" onClick={handleSearch}>Search</Button>

            </InputGroup>
            
            {/* <Button variant="secondary" style={buttonStyleWarehouse} onClick={handleSearch}>order</Button>{' '} */}
            </div>

        </div>
    )
 
}
export default HeaderWarehouse
