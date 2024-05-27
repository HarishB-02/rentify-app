import React, { useState } from 'react';
import './LoginCard.css';
import { Register } from './Register';

export const LoginCard = ({ onLogin }) => {
  const [openRegister, setOpenRegister] = useState(false);
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = (e) => {
    e.preventDefault();
    onLogin(email, password);
  };

  const toggleModal = () => {
    setOpenRegister(!openRegister);
  };

  return (
    <div className="container1">
      {openRegister && (
        <Register isOpen={openRegister} toggleModal={toggleModal} />
      )}
      <div className="card card-align">
        <h2>Login</h2>
        <form onSubmit={handleLogin}>
          <div className="input-group">
            <label>Email:</label>
            <input
              type="email"
              name="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          </div>
          <div className="input-group">
            <label>Password:</label>
            <input
              type="password"
              name="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>
          <button type="submit">Login</button>
        </form>
        <div className="register-link">
          <p>
            Don't have an account?{' '}
            <span className="register-button" onClick={() => setOpenRegister(true)}>
              Register
            </span>
          </p>
        </div>
      </div>
    </div>
  );
};
