import Sidebar from "../components/Sidebar/SideBar";
import Button from 'react-bootstrap/Button';
import { Link } from "react-router-dom";
import CommissionDetails from "../components/TableCommission/CommissionDetails";
import PATH from "../paths";

const Details = () => {
  const buttonStyle = {
    marginTop: "10px",
    fontSize: "32px", 
    fontFamily: "'Extra Bolt Italic', sans-serif"
  };

  const contentStyle = {
    width: "100%", 
    padding: "20px", 
    marginRight:"30px"
  };

  return (
    <div style={{ display: "flex" }}>
      <Sidebar />
      <div style={contentStyle}>
        <Link to={PATH.COMMISSION}>
          <Button variant="light" style={buttonStyle}>Back</Button>
        </Link>
        <CommissionDetails/>
      </div>
    </div>
  );
};

export default Details;
