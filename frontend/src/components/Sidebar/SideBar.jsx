import React from "react";
import Button from 'react-bootstrap/Button';
import { Link } from "react-router-dom";
import { FaUser, FaWarehouse, FaMoneyBill, FaIndustry } from 'react-icons/fa'; // Import Font Awesome icons
import PATH from "../../paths";
import Cookies from "js-cookie";

const Sidebar = () => {

    const handleLogOut =()=>{
        Cookies.remove('jwt');
    }

    return (
        <div className="slideBarContainer">
            <h4 className="whiteWritings">
                Car Service
            </h4>
            <br />
            <Link to={PATH.CLIENT}>
                <Button variant="outline-light">
                    <FaUser className="icon" /> Clients
                </Button>{' '}
            </Link>
            <br />
            <Link to={PATH.WAREHOUSE}>
                <Button variant="outline-light">
                    <FaWarehouse className="icon" /> Warehouse
                </Button>{' '}
            </Link>
            <br />
            <Link to={PATH.COMMISSION}>
                <Button variant="outline-light">
                    <FaMoneyBill className="icon" /> Commission
                </Button>{' '}
            </Link>
            <br />
            <Link to={PATH.WHOLESALERS}>
                <Button variant="outline-light">
                    <FaIndustry className="icon" /> Wholesalers
                </Button>{' '}
            </Link>
            <br />
            <Link to={PATH.LOGIN}>
                <Button variant="outline-light" className="logoutButton" onClick={handleLogOut}>
                    Log Out
                </Button>{' '}
            </Link>
        </div>
    );
};

export default Sidebar;
