import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import PATH from "../../paths";

const LoginForm = () => {
  return (
    <div className="centerDiv">
      <h1>Logowanie</h1>
      <Link to={PATH.CLIENT}>
        <Button variant="primary">Zaloguj</Button>
      </Link>
    </div>
  );
};

export default LoginForm;
