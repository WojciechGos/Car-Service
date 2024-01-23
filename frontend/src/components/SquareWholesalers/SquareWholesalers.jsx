import React from "react"
import Button from "react-bootstrap/Button"
import { Link } from "react-router-dom";
import PATH from "../../paths";
import Card from 'react-bootstrap/Card';


const SquareWholesalers = ()=>{

    const buttonStyle = {
        fontSize: "32px", 
        fontFamily: "'Extra Bolt Italic', sans-serif",
        marginTop: 100,
        marginRight: 90,
        marginLeft: 90,
        marginBottom: 30,

      };
      const textCard ={
        fontSize: '3rem', 
        fontFamily: "'Extra Bolt Italic', sans-serif",
      }
      const cardStyle = {
        backgroundColor: '#D1D1D1',
        fontsize: "100px",
        margin: '20px',
        marginTop: 100,
        marginLeft: 150
      };

    return (
        <div style={{display: "flex"}}>
        <Card className="text-center" style={cardStyle}>
        <Card.Header style={textCard}> IPART </Card.Header>
        <Card.Body>

        <Link to={PATH.IPART}>
                <Button variant="warning"style ={buttonStyle}>Select</Button>
        </Link>
        </Card.Body>
      </Card>

      <Card className="text-center" style={cardStyle}>
        <Card.Header style={textCard}> StarHurt </Card.Header>
        <Card.Body>
            
        <Link to={PATH.STARHURT}>
             <Button variant="warning"style ={buttonStyle}>Select</Button>
        </Link>
        </Card.Body>
      </Card>


      </div>
    )
 
}
export default SquareWholesalers
