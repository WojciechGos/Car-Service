import React, { useState } from "react";
import { Button, Alert } from "react-bootstrap"; // Dodaj Alert z react-bootstrap
import PATH from "../../paths";
import userImage from "./user.png";
import keyImage from "./key.png";
import Cookies from "js-cookie";

const LoginForm = () => {
  const [email, setemail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null); // Dodaj stan do przechowywania błędu

  const handleLogin = async () => {
    try {
      const response = await fetch(`${process.env.REACT_APP_URL}/api/v1/auth/login`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ email, password }),
      });

      if (response.ok) {
        const data = await response.json();

        if (data) {
          Cookies.set("jwt", data.token);
          window.location.href = PATH.CLIENT;
        } else {
          console.error("Authentication failed");
        }
      } else {
        console.error("Server error:", response.status);

        if (response.status === 401) {
          setError("Invalid password"); // Ustaw błąd o błędnym haśle
        } else {
          setError("Server error: " + response.status);
        }
      }
    } catch (error) {
      console.error("Error during login", error);
      setError("Error during login");
    }
  };

  return (
    <div className="loginContainer">
      <div className="loginContent">
        <h1>Login</h1>
        <h6>Please enter your Login and your Password </h6>
          {error && <Alert variant="danger" className="smaller-error">{error}</Alert>}
        <div className="loginDiv">
          <img src={userImage} alt="User" />
          <input
            className="input"
            placeholder="E-mail"
            value={email}
            onChange={(e) => setemail(e.target.value)}
          />
        </div>
        <div className="passwordDiv">
          <img src={keyImage} alt="User" />
          <input
            className="input"
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <Button variant="outline-light" className="button4" onClick={handleLogin}>
          Login
        </Button>
      </div>
    </div>
  );
};

export default LoginForm;
