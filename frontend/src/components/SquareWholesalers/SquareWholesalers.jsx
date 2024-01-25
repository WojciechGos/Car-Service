import React from "react";
import { Link } from "react-router-dom";
import PATH from "../../paths";

const SquareWholesalers = () => {
    const cardContainerStyle = {
        display: "flex",
    };

    return (
        <div style={cardContainerStyle}>
            
              <div className="cardStyle">
                <Link to={PATH.IPART}>
                  <button className="selectButton">Select</button>
                </Link>
              </div>
           
              <div className="cardStyle2">
                <Link to={PATH.STARHURT}>
                  <button className="selectButton">Select</button>
                </Link>
              </div>
        </div>
    );
}

export default SquareWholesalers;
