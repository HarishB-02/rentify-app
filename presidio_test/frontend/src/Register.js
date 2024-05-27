import React, { useState, useEffect } from "react";
import { Modal, Button, Form } from "react-bootstrap";
import "./Register.css";
import axios from "axios";

export const Register = (props) => {
  const [showModal, setShowModal] = useState(props.isOpen);
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [password, setPassword] = useState("");
  const [userRole, setUserRole] = useState("");
  const [errors, setErrors] = useState({});

  useEffect(() => {
    setShowModal(props.isOpen);
  }, [props.isOpen]);

  const handleClose = () => {
    props.toggleModal();
    setShowModal(false);
  };

  const validateForm = () => {
    const newErrors = {};
    if (!firstName.match(/[a-zA-Z]/)) {
      newErrors.firstName = "First name must contain at least one letter.";
    }
    if (!lastName.match(/[a-zA-Z]/)) {
      newErrors.lastName = "Last name must contain at least one letter.";
    }
    if (!email.match(/^[^\s@]+@[^\s@]+\.[^\s@]+$/)) {
      newErrors.email = "Invalid email address.";
    }
    if (!phoneNumber.match(/^\d{10}$/)) {
      newErrors.phoneNumber = "Phone number must be a 10-digit number.";
    }
    if (password.length < 6) {
      newErrors.password = "Password must be at least 6 characters long.";
    }
    if (!userRole) {
      newErrors.userRole = "You must select a user role.";
    }
    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleRegister = () => {
    if (validateForm()) {
      handleClose();
      const buyer = userRole === "Buyer";
      const seller = userRole === "Seller";
      const payload = {
        firstName: firstName,
        lastName: lastName,
        email: email,
        phoneNumber: phoneNumber,
        password: password,
        buyer: buyer,
        seller: seller,
      };
      axios({
        url: "http://localhost:8084/register",
        method: "POST",
        headers: {
          "Access-Control-Allow-Origin": "http://localhost:8084/register",
        },
        data: payload,
      });
    }
  };

  return (
    <Modal show={showModal} onHide={handleClose} centered>
      <Modal.Header closeButton>
        <Modal.Title>Register</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form autoComplete="off">
          <div className="name-fields">
            <Form.Group controlId="formFirstName" className="form-group-half">
              <Form.Label>
                First Name <span className="required">*</span>
              </Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter first name"
                value={firstName}
                onChange={(e) => setFirstName(e.target.value)}
                isInvalid={!!errors.firstName}
              />
              <Form.Control.Feedback type="invalid">
                {errors.firstName}
              </Form.Control.Feedback>
            </Form.Group>

            <Form.Group controlId="formLastName" className="form-group-half">
              <Form.Label>
                Last Name <span className="required">*</span>
              </Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter last name"
                value={lastName}
                onChange={(e) => setLastName(e.target.value)}
                isInvalid={!!errors.lastName}
              />
              <Form.Control.Feedback type="invalid">
                {errors.lastName}
              </Form.Control.Feedback>
            </Form.Group>
          </div>

          <Form.Group controlId="formEmail">
            <Form.Label>
              Email address <span className="required">*</span>
            </Form.Label>
            <Form.Control
              type="email"
              placeholder="Enter email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              isInvalid={!!errors.email}
            />
            <Form.Control.Feedback type="invalid">
              {errors.email}
            </Form.Control.Feedback>
          </Form.Group>

          <Form.Group controlId="formPhoneNumber">
            <Form.Label>
              Phone Number <span className="required">*</span>
            </Form.Label>
            <Form.Control
              type="text"
              placeholder="Enter phone number"
              value={phoneNumber}
              onChange={(e) => setPhoneNumber(e.target.value)}
              isInvalid={!!errors.phoneNumber}
            />
            <Form.Control.Feedback type="invalid">
              {errors.phoneNumber}
            </Form.Control.Feedback>
          </Form.Group>

          <Form.Group controlId="formPassword">
            <Form.Label>
              Password <span className="required">*</span>
            </Form.Label>
            <Form.Control
              type="password"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              isInvalid={!!errors.password}
            />
            <Form.Control.Feedback type="invalid">
              {errors.password}
            </Form.Control.Feedback>
          </Form.Group>

          <Form.Group controlId="formUserRole">
            <Form.Label>
              User Role <span className="required">*</span>
            </Form.Label>
            <div>
              <Form.Check
                type="radio"
                id="seller"
                name="userRole"
                label="Seller"
                value="Seller"
                onChange={(e) => setUserRole(e.target.value)}
                isInvalid={!!errors.userRole}
              />
              <Form.Check
                type="radio"
                id="buyer"
                name="userRole"
                label="Buyer"
                value="Buyer"
                onChange={(e) => setUserRole(e.target.value)}
                isInvalid={!!errors.userRole}
              />
              <Form.Control.Feedback type="invalid">
                {errors.userRole}
              </Form.Control.Feedback>
            </div>
          </Form.Group>
        </Form>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={handleClose}>
          Close
        </Button>
        <Button variant="primary" onClick={handleRegister}>
          Register
        </Button>
      </Modal.Footer>
    </Modal>
  );
};
