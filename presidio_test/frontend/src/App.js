import React, { useState } from "react";
import "./App.css";
import { LoginCard } from "./LoginCard";
import { Dashboard } from "./Dashboard";
import axios from "axios";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [user, setUser] = useState("");
  const [userDetails, setUserDetails] = useState({});
  const [properties, setProperties] = useState([]);

  const handleLogin = (userName, password) => {
    setUser(userName);
    axios({
      url:
        "http://localhost:8084/login?email=" +
        userName +
        "&password=" +
        password,
      method: "GET",
      headers: {
        "Access-Control-Allow-Origin": "http://localhost:8084/login",
      },
    }).then((res) => {
      setUserDetails(res.data);
      getProperties(res.data);
      setIsLoggedIn(!res.data.error);
    });
  };

  const getProperties = (data) => {
    if (data.seller) {
      axios({
        url:
          "http://localhost:8084/seller/property/getProperty/all/" + data.email,
        method: "GET",
        headers: {
          "Access-Control-Allow-Origin":
            "http://localhost:8084/seller/property/getProperty/all/",
        },
      }).then((res) => {
        setProperties(res.data);
      });
    } else {
      axios({
        url: "http://localhost:8084/buyer/allProperty",
        method: "GET",
        headers: {
          "Access-Control-Allow-Origin":
            "http://localhost:8084/buyer/allProperty",
        },
      }).then((res) => {
        setProperties(res.data);
      });
    }
  };
  return (
    <div className="App">
      <header className="App-header">Rentify - Where Renting Meets Simplicity</header>
      {!isLoggedIn ? (
        <LoginCard onLogin={handleLogin} />
      ) : (
        <Dashboard userDetails={userDetails} properties={properties} getProperties={getProperties} />
      )}
    </div>
  );
}

export default App;
