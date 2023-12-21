import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import PATH from "../../paths";
const LoginForm = () => {
  return (
    <div className="centerDiv">
      <h1>LoginForm</h1>
      <Link to={PATH.PANEL}>
        <Button variant="primary">Zaloguj</Button>
      </Link>
    </div>
  );
};

export default LoginForm;
