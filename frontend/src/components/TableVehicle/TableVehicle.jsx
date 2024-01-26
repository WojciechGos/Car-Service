import Cookies from "js-cookie";
import React, { useState, useEffect } from "react";
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';

const TableVehicle = ({ onEditVehicle, isEditingVehicle, filterText, onFilterChange }) => {
  const [vehicles, setVehicles] = useState([]);
  const [isEditing, setIsEditing] = useState(false);
  const [editedVehicleId, setEditedVehicleId] = useState(null);
  const [editedVehicle, setEditedVehicle] = useState({
    id: null,
    brand: "",
    model: "",
    vin: "",
    registrationNumber: "",
    registrationDate: ""
  });

  useEffect(() => {
    fetchVehicles();
  }, []);

  const fetchVehicles = async () => {
    try {
      const response = await fetch("http://localhost:5001/api/v1/vehicles", {
        headers: {
          'Authorization': `Bearer ${Cookies.get("jwt")}`,
        },
      });
      if (response.ok) {
        const data = await response.json();
        setVehicles(data);
        console.log(data);
      } else {
        console.error("Failed to fetch vehicles");
      }
    } catch (error) {
      console.error("Error fetching vehicles:", error);
    }
  };

  useEffect(() => {
    if (onFilterChange) {
      onFilterChange(filterText);
    }
  }, [filterText, onFilterChange]);

  const handleSaveEdit = async() => {
    try {
      const response = await fetch(`http://localhost:5001/api/v1/vehicles/${editedVehicleId}`, {
        method: "PUT",
        headers: {
          'Authorization': `Bearer ${Cookies.get("jwt")}`,
          "Content-Type": "application/json"
        },
        body: JSON.stringify(editedVehicle),
      });

      if (response.ok) {
        const updatedVehicles = vehicles.map((vehicle) => {
          if (vehicle.id === editedVehicleId) {
            return { ...vehicle, ...editedVehicle };
          }
          return vehicle;
        });

        setVehicles(updatedVehicles);
        setEditedVehicleId(null);
        onEditVehicle(false); 
        } else {
          console.error("Failed to save changes");
        }
      } catch (error) {
        console.error("Error saving changes:", error);
      }
    };
   
  const handleEditVehicle = (vehicleId) => {
    const vehicleToEdit = vehicles.find((vehicle) => vehicle.id === vehicleId);
    setEditedVehicleId(vehicleId);
    setEditedVehicle(vehicleToEdit);
    setIsEditing(true);
    onEditVehicle(true); 
  };

  const handleCancelEdit = () => {
    setEditedVehicleId(null);
    setEditedVehicle({
      id: null,
      brand: "",
      model: "",
      vin: "",
      licensePlate: "",
      year: ""
    });
    setIsEditing(false);
    onEditVehicle(false);
  };

  const filteredVehicles = vehicles.filter((vehicle) => {
    const brand = vehicle.brand ? vehicle.brand.toLowerCase() : "";
    const model = vehicle.model ? vehicle.model.toLowerCase() : "";
    const vin = vehicle.vin ? vehicle.vin.toLowerCase() : "";
    const registrationNumber = vehicle.registrationNumber ? vehicle.registrationNumber.toLowerCase() : "";
    const registrationDate = vehicle.registrationDate ? vehicle.registrationDate.toString() : "";
  
    return (
      brand.includes(filterText.toLowerCase()) ||
      model.includes(filterText.toLowerCase()) ||
      vin.includes(filterText.toLowerCase()) ||
      registrationNumber.includes(filterText.toLowerCase()) ||
      registrationDate.includes(filterText)
    );
  });

  return (
    <div className="table-container">
      <div className="overflow-container8">
        {isEditing ? (
          <div>
            <p>Editing Vehicle: {editedVehicleId}</p>
            <Form>
              <Form.Group controlId="formBrand">
                <Form.Label>New Brand:</Form.Label>
                <Form.Control
                  type="text"
                  value={editedVehicle.brand}
                  onChange={(e) => setEditedVehicle({ ...editedVehicle, brand: e.target.value })}
                />
              </Form.Group>

              <Form.Group controlId="formModel">
                <Form.Label>New Model:</Form.Label>
                <Form.Control
                  type="text"
                  value={editedVehicle.model}
                  onChange={(e) => setEditedVehicle({ ...editedVehicle, model: e.target.value })}
                />
              </Form.Group>

              <Form.Group controlId="formVin">
                <Form.Label>New VIN:</Form.Label>
                <Form.Control
                  type="text"
                  value={editedVehicle.vin}
                  onChange={(e) => setEditedVehicle({ ...editedVehicle, vin: e.target.value })}
                />
              </Form.Group>

              <Form.Group controlId="formRegistrationNumber">
                <Form.Label>New License Plate:</Form.Label>
                <Form.Control
                  type="text"
                  value={editedVehicle.registrationNumber}
                  onChange={(e) => setEditedVehicle({ ...editedVehicle, registrationNumber: e.target.value })}
                />
              </Form.Group>

              <Form.Group controlId="formRegistrationDate">
                <Form.Label>New Year:</Form.Label>
                <Form.Control
                  type="text"
                  value={editedVehicle.registrationDate}
                  onChange={(e) => setEditedVehicle({ ...editedVehicle, registrationDate: e.target.value })}
                />
              </Form.Group>

              <Button type="button" variant="danger" onClick={handleSaveEdit} style={{ marginTop: "10px", fontSize: "16px" }}>
                Save Changes
              </Button>
              <Button type="button" variant="secondary" onClick={handleCancelEdit} style={{ marginTop: "10px", fontSize: "16px" ,marginLeft:"5px"}}>
                Cancel
              </Button>
            </Form>
          </div>
        ) : (
          <table className="custom-table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Brand</th>
                <th>Model</th>
                <th>VIN</th>
                <th>License plate</th>
                <th>Year</th>
              </tr>
            </thead>
            <tbody>
              {filteredVehicles.map((vehicle) => (
                <tr
                  key={vehicle.id}
                >
                  <td>{vehicle.id}</td>
                  <td>{vehicle.brand}</td>
                  <td>{vehicle.model}</td>
                  <td>{vehicle.vin}</td>
                  <td>{vehicle.registrationNumber}</td>
                  <td>{vehicle.registrationDate}</td>
                  <td>
                    <button type="button" onClick={() => handleEditVehicle(vehicle.id)}>
                      Edit
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </div>
  );
};

export default TableVehicle;