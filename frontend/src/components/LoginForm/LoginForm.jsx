import React, { useState } from "react";
import { Button } from "react-bootstrap";
import PATH from "../../paths";
import userImage from "./user.png";
import keyImage from "./key.png";

const LoginForm = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async () => {
    try {
      const response = await fetch("http://localhost:5001/api/v1/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ username, password }),
      });
      
      if (response.ok) {
        const data = await response.json();
        
        if (data.success) {
          window.location.href = PATH.CLIENT;
        } else {
          console.error("Authentication failed");
        }
      } else {
        console.error("Server error:", response.status);
      }
    } catch (error) {
      console.error("Error during login", error);
    }
  };

  return (
    <div className="centerDiv">
      <h1>Login</h1>
      <h6>Please enter your Login and your Password </h6>
      <div className="loginDiv">
        <img src={userImage} alt="User" />
        <input
          className="input"
          placeholder="Username or E-mail"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
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
      <Button variant="outline-light" className="button" onClick={handleLogin}>
        Login
      </Button>
    </div>
  );
};

export default LoginForm;
