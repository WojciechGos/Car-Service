import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import PATH from "../../paths";
import userImage from './user.png';
import keyImage from './key.png';


const LoginForm = () => {
  return (
    <div className="centerDiv">
      <h1>Login</h1>
      <h6>Please enter your Login and your Password </h6>
      <div className="loginDiv">
        <img src={userImage} alt="User" />
        <input
            className="input"
            placeholder="Username or E-mail"
          ></input>
      </div>
      <div className="passwordDiv">
        <img src={keyImage} alt="User" />
        <input
            className="input"
            type="password"
            placeholder="Password"
          ></input>
      </div>
      <Link to={PATH.CLIENT}>
      <Button variant="outline-light" className="button">
        Login
      </Button>
    </Link>
    </div>
  );
};

export default LoginForm;
