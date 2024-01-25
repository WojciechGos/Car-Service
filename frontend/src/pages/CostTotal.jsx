import { useParams } from "react-router-dom";
import Sidebar from "../components/Sidebar/SideBar";
import TotalCostTable from "../components/TotalCostTable/TotalCostTable";
import { Link } from "react-router-dom";
import { Button } from "react-bootstrap";
import PATH from "../paths";

const CostTotal = () => {
  const { id } = useParams();

  console.log("commission " + id);

  const buttonStyle = {
    marginTop: "10px",
    fontSize: "32px", 
    fontFamily: "'Extra Bolt Italic', sans-serif"
  };

  return (
    <div style={{ display: "flex" }}>
      <Sidebar />

      <div>
        <Link to={`${PATH.DETAILS}/${id}`}>
          <Button variant="light" style={buttonStyle}>
            Back
          </Button>
        </Link>
        <TotalCostTable costType="total" commissionId={id} filteredText="" />
      </div>
    </div>
  );
};

export default CostTotal;
