import React, { useState } from "react";
import { Modal, Button, Form, FormControl, FormLabel, FormGroup } from "react-bootstrap";
import axios from "axios";
import "./Dashboard.css";

export const Dashboard = ({ userDetails, properties, getProperties }) => {
  const [searchTerm, setSearchTerm] = useState("");
  const [sqFtFilter, setSqFtFilter] = useState("");
  const [costFilter, setCostFilter] = useState("");
  const [houseTypeFilter, setHouseTypeFilter] = useState("");
  const [likes, setLikes] = useState('');
  const [interested, setInterested] = useState({});
  const [sellerDetails, setSellerDetails] = useState({});
  const [showAddModal, setShowAddModal] = useState(false);
  const [showDeleteModal, setShowDeleteModal] = useState(false);
  const [currentProperty, setCurrentProperty] = useState(null);
  const [formValues, setFormValues] = useState({
    propertyName: "",
    address: "",
    sqFt: "",
    bedrooms: "",
    bathrooms: "",
    propertyRent: "",
    nearbyLandmark: "",
  });

  const handleLike = (propertyId) => {
    const payload = {
      propertyId: propertyId,
      userId: userDetails.id
    }
    axios({
      url: "http://localhost:8084/buyer/like",
      method: "PUT",
      headers: {
        "Access-Control-Allow-Origin": "*",
      },
      data: payload
    }).then((res) => {
      setLikes(res.likeCount);
      getProperties(userDetails);
    });
  };

  const handleInterest = (property) => {
    setInterested((prevInterested) => ({
      ...prevInterested,
      [property.propertyId]: !prevInterested[property.propertyId],
    }));

    axios({
      url: "http://localhost:8084/seller/property/" + property.sellerId,
      method: "GET",
      headers: {
        "Access-Control-Allow-Origin": "*",
      },
    }).then((res) => {
      setSellerDetails(res.data);
    });
  };

  const handleAddProperty = () => {
    setShowAddModal(true);
    setCurrentProperty(null);
    setFormValues({
      propertyName: "",
      address: "",
      sqFt: "",
      bedrooms: "",
      bathrooms: "",
      propertyRent: "",
      nearbyLandmark: "",
    });
  };

  const handleEditProperty = (property) => {
    setShowAddModal(true);
    setCurrentProperty(property);
    setFormValues({
      propertyName: property.propertyName || "",
      address: property.address || "",
      sqFt: property.sqFt || "",
      bedrooms: property.bedrooms || "",
      bathrooms: property.bathrooms || "",
      propertyRent: property.propertyRent || "",
      nearbyLandmark: property.nearbyLandmark || "",
    });
  };

  const handleDeleteProperty = (property) => {
    setShowDeleteModal(true);
    setCurrentProperty(property);
  };

  const handleDeleteConfirm = () => {
    axios({
        url: "http://localhost:8084/seller/property/deleteProperty/" + currentProperty.propertyId + '/' + userDetails.email,
        method: "DELETE",
        headers: {
          "Access-Control-Allow-Origin": "http://localhost:8084/seller/property/deleteProperty/",
        },
      })
      .then(() => {
        setShowDeleteModal(false);
        getProperties(userDetails);
      });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    for (let key in formValues) {
      if (!formValues[key]) {
        alert(`Please enter ${key}`);
        return;
      }
    }

    const propertyData = {
      ...formValues,
      sellerEmail: userDetails.email,
      sellerId: userDetails.id
    };

    let url = "http://localhost:8084/seller/property/addProperty";
    let method = "POST";

    if (currentProperty) {
      url = "http://localhost:8084/seller/property/updateProperty";
      method = "PUT";
      propertyData.propertyId = currentProperty.propertyId
    }

    axios({
      url: url,
      method: method,
      headers: {
        "Access-Control-Allow-Origin": url,
      },
      data: propertyData,
    })
      .then(() => {
        setShowAddModal(false);
        getProperties(userDetails);
      });
  };

  const filterProperties = (property) => {
    const matchesSearchTerm = (field) => field.toLowerCase().includes(searchTerm.toLowerCase());
    const matchesSqFtFilter = !sqFtFilter || (
      (sqFtFilter === "1000-1999" && property.sqFt >= 1000 && property.sqFt < 2000) ||
      (sqFtFilter === "2000-2999" && property.sqFt >= 2000 && property.sqFt < 3000) ||
      (sqFtFilter === ">3000" && property.sqFt >= 3000)
    );

    const matchesCostFilter = !costFilter || (
      (costFilter === "10000-19999" && property.propertyRent >= 10000 && property.propertyRent < 20000) ||
      (costFilter === "20000-29999" && property.propertyRent >= 20000 && property.propertyRent < 30000) ||
      (costFilter === ">30000" && property.propertyRent >= 30000)
    );

    const matchesHouseTypeFilter = !houseTypeFilter || (
      (houseTypeFilter === "1 bhk" && property.bedrooms === 1) ||
      (houseTypeFilter === "2 bhk" && property.bedrooms === 2) ||
      (houseTypeFilter === "3 bhk" && property.bedrooms === 3) ||
      (houseTypeFilter === "4 bhk" && property.bedrooms === 4) ||
      (houseTypeFilter === "5 bhk and above" && property.bedrooms >= 5)
    );

    const matchesSearch = [
      property.propertyName,
      property.sqFt,
      property.bedrooms,
      property.propertyRent,
      property.nearbyLandmark,
      property.bathrooms,
      property.address
    ].some(field => matchesSearchTerm(field.toString()));

    return matchesSearch && matchesSqFtFilter && matchesCostFilter && matchesHouseTypeFilter;
  };

  const filteredProperties = properties.filter(filterProperties);

  return (
    <div className="dashboard">
      <h1 className="welcome-text">Hello {userDetails.firstName}!</h1>
      <div className="search-filter">
        <input
          className="search"
          type="text"
          placeholder="Search properties..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
        <select
          className="filter"
          value={sqFtFilter}
          onChange={(e) => setSqFtFilter(e.target.value)}
        >
          <option value="">All Sq.ft</option>
          <option value="1000-1999">1000-1999</option>
          <option value="2000-2999">2000-2999</option>
          <option value=">3000">{">3000"}</option>
        </select>
        <select
          className="filter"
          value={costFilter}
          onChange={(e) => setCostFilter(e.target.value)}
        >
          <option value="">All Costs</option>
          <option value="10000-19999">10000-19999</option>
          <option value="20000-29999">20000-29999</option>
          <option value=">30000">{">30000"}</option>
        </select>
        <select
          className="filter"
          value={houseTypeFilter}
          onChange={(e) => setHouseTypeFilter(e.target.value)}
        >
          <option value="">All House Types</option>
          <option value="1 bhk">1 bhk</option>
          <option value="2 bhk">2 bhk</option>
          <option value="3 bhk">3 bhk</option>
          <option value="4 bhk">4 bhk</option>
          <option value="5 bhk and above">5 bhk and above</option>
        </select>
      </div>
      {userDetails.seller && (
        <Button className="add-property-button" onClick={handleAddProperty}>
          Add a Property
        </Button>
      )}
      <div className="horizontal-line"></div>
      <div className="property-grid">
        {filteredProperties.map((property) => (
          <div key={property.propertyId} className="property-card">
            <div className="property-name">{property.propertyName}</div>
            <div className="property-row">
              <div className="property-column">
                <div className="property-value">{property.sqFt}</div>
                <div className="property-key">Sq.ft</div>
              </div>
              <div className="property-column">
                <div className="property-value">{property.address}</div>
                <div className="property-key">Address</div>
              </div>
              <div className="property-column">
                <div className="property-value">{property.bedrooms} bhk</div>
                <div className="property-key">House type</div>
              </div>
            </div>
            <div className="property-row">
              <div className="property-column">
                <div className="property-value">{property.propertyRent}</div>
                <div className="property-key">Cost</div>
              </div>
              <div className="property-column">
                <div className="property-value">{property.nearbyLandmark}</div>
                <div className="property-key">Nearby Landmark</div>
              </div>
              <div className="property-column">
                <div className="property-value">{property.bathrooms}</div>
                <div className="property-key">Bathrooms</div>
              </div>
            </div>
            {userDetails.buyer && (
              <div className="property-buttons">
                <span className="like-count">
                  {property.likes} likes
                </span>
                <Button
                  className="like-button"
                  variant="success"
                  onClick={() => handleLike(property.propertyId)}
                >
                  Like
                </Button>
                <Button
                  className="interested-button"
                  variant="info"
                  onClick={() => handleInterest(property)}
                >
                  I'm Interested
                </Button>
              </div>
            )}
            {userDetails.seller && (
              <div className="property-buttons">
                <Button
                  className="edit-button"
                  variant="warning"
                  onClick={() => handleEditProperty(property)}
                >
                  Edit
                </Button>
                <Button
                  className="delete-button"
                  variant="danger"
                  onClick={() => handleDeleteProperty(property)}
                >
                  Delete
                </Button>
              </div>
            )}
            {interested[property.propertyId] && (
              <div className="interest-details">
                <div className="interest-header">Seller Details</div>
                <div className="interest-row">
                  <div className="interest-column">
                    <div className="interest-value">
                      {sellerDetails.firstName} {sellerDetails.lastName}
                    </div>
                    <div className="interest-key">Name</div>
                  </div>
                  <div className="interest-column">
                    <div className="interest-value">{sellerDetails.email}</div>
                    <div className="interest-key">Email</div>
                  </div>
                  <div className="interest-column">
                    <div className="interest-value">
                      {sellerDetails.phoneNumber}
                    </div>
                    <div className="interest-key">Mobile</div>
                  </div>
                </div>
              </div>
            )}
          </div>
        ))}
      </div>

      <Modal show={showAddModal} onHide={() => setShowAddModal(false)}>
        <Modal.Header closeButton>
          <Modal.Title>{currentProperty ? "Edit Property" : "Add a Property"}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form onSubmit={handleSubmit}>
            <FormGroup>
              <FormLabel>Property Name</FormLabel>
              <FormControl
                type="text"
                name="propertyName"
                value={formValues.propertyName}
                onChange={(e) =>
                  setFormValues({
                    ...formValues,
                    propertyName: e.target.value,
                  })
                }
              />
            </FormGroup>
            <FormGroup>
              <FormLabel>Address</FormLabel>
              <FormControl
                type="text"
                name="address"
                value={formValues.address}
                onChange={(e) =>
                  setFormValues({ ...formValues, address: e.target.value })
                }
              />
            </FormGroup>
            <FormGroup>
              <FormLabel>House Sq.ft</FormLabel>
              <FormControl
                type="number"
                name="sqFt"
                value={formValues.sqFt}
                onChange={(e) =>
                  setFormValues({ ...formValues, sqFt: e.target.value })
                }
              />
            </FormGroup>
            <FormGroup>
              <FormLabel>No. of Bedrooms</FormLabel>
              <FormControl
                type="number"
                name="bedrooms"
                value={formValues.bedrooms}
                onChange={(e) =>
                  setFormValues({ ...formValues, bedrooms: e.target.value })
                }
              />
            </FormGroup>
            <FormGroup>
              <FormLabel>No. of Bathrooms</FormLabel>
              <FormControl
                type="number"
                name="bathrooms"
                value={formValues.bathrooms}
                onChange={(e) =>
                  setFormValues({ ...formValues, bathrooms: e.target.value })
                }
              />
            </FormGroup>
            <FormGroup>
              <FormLabel>Rent</FormLabel>
              <FormControl
                type="number"
                name="propertyRent"
                value={formValues.propertyRent}
                onChange={(e) =>
                  setFormValues({ ...formValues, propertyRent: e.target.value })
                }
              />
            </FormGroup>
            <FormGroup>
              <FormLabel>Nearby Landmark</FormLabel>
              <FormControl
                type="text"
                name="nearbyLandmark"
                value={formValues.nearbyLandmark}
                onChange={(e) =>
                  setFormValues({
                    ...formValues,
                    nearbyLandmark: e.target.value,
                  })
                }
              />
            </FormGroup>
            {currentProperty && (
              <FormGroup>
                <FormLabel>Seller Email</FormLabel>
                <FormControl
                  type="email"
                  name="sellerEmail"
                  value={userDetails.email}
                  disabled
                />
              </FormGroup>
            )}
            <Modal.Footer>
              <Button variant="secondary" onClick={() => setShowAddModal(false)}>
                Cancel
              </Button>
              <Button variant="success" type="submit">
                Save
              </Button>
            </Modal.Footer>
          </Form>
        </Modal.Body>
      </Modal>

      <Modal show={showDeleteModal} onHide={() => setShowDeleteModal(false)}>
        <Modal.Header closeButton>
          <Modal.Title>Confirm Deletion</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <p>Do you want to delete the property?</p>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShowDeleteModal(false)}>
            Cancel
          </Button>
          <Button variant="danger" onClick={handleDeleteConfirm}>
            Delete
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
};
