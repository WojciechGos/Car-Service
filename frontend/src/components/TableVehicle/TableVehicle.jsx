import React, { useState, useEffect } from "react";
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';

const TableVehicle = ({ onEditVehicle, isEditingVehicle, filterText, onFilterChange }) => {
  const [vehicles, setVehicles] = useState([]);
  const [selectedVehicleId, setSelectedVehicleId] = useState(null);
  const [editedVehicleId, setEditedVehicleId] = useState(null);
  const [editedVehicle, setEditedVehicle] = useState({
    id: null,
    brand: "",
    model: "",
    vin: "",
    licensePlate: "",
    year: ""
  });

  useEffect(() => {
    fetchVehicles();
  }, []);

  const fetchVehicles = async () => {
    try {
      const response = await fetch("http://localhost:5001/api/v1/vehicles");
      if (response.ok) {
        const data = await response.json();
        setVehicles(data);
      } else {
        console.error("Failed to fetch vehicles");
      }
    } catch (error) {
      console.error("Error fetching vehicles:", error);
    }
  };

  useEffect(() => {
    // Jeśli zmienia się filterText, wykonaj odpowiednie działania
    if (onFilterChange) {
      onFilterChange(filterText);
    }
  }, [filterText, onFilterChange]);

  const handleSelectVehicle = (vehicleId) => {
    if (!isEditingVehicle) {
      setSelectedVehicleId((prevSelectedVehicleId) =>
        prevSelectedVehicleId === vehicleId ? null : vehicleId
      );
    }
  };

  const handleSaveEdit = () => {
    const updatedVehicles = vehicles.map((vehicle) => {
      if (vehicle.id === editedVehicleId) {
        return { ...vehicle, ...editedVehicle };
      }
      return vehicle;
    });

    setVehicles(updatedVehicles);
    setEditedVehicleId(null);
    onEditVehicle(false); 
  };

  const handleEditVehicle = (vehicleId) => {
    const vehicleToEdit = vehicles.find((vehicle) => vehicle.id === vehicleId);
    setEditedVehicleId(vehicleId);
    setEditedVehicle(vehicleToEdit);
    onEditVehicle(true); 
  };

  const filteredVehicles = vehicles.filter((vehicle) => {
    const brand = vehicle.brand ? vehicle.brand.toLowerCase() : "";
    const model = vehicle.model ? vehicle.model.toLowerCase() : "";
    const vin = vehicle.vin ? vehicle.vin.toLowerCase() : "";
    const licensePlate = vehicle.licensePlate ? vehicle.licensePlate.toLowerCase() : "";
    const year = vehicle.year ? vehicle.year.toString() : "";
  
    return (
      brand.includes(filterText.toLowerCase()) ||
      model.includes(filterText.toLowerCase()) ||
      vin.includes(filterText.toLowerCase()) ||
      licensePlate.includes(filterText.toLowerCase()) ||
      year.includes(filterText)
    );
  });

  return (
    <div className="table-container">
      <div className="overflow-container2">
        {isEditingVehicle ? (
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

              <Form.Group controlId="formLicensePlate">
                <Form.Label>New License Plate:</Form.Label>
                <Form.Control
                  type="text"
                  value={editedVehicle.licensePlate}
                  onChange={(e) => setEditedVehicle({ ...editedVehicle, licensePlate: e.target.value })}
                />
              </Form.Group>

              <Form.Group controlId="formYear">
                <Form.Label>New Year:</Form.Label>
                <Form.Control
                  type="text"
                  value={editedVehicle.year}
                  onChange={(e) => setEditedVehicle({ ...editedVehicle, year: e.target.value })}
                />
              </Form.Group>

              <Button type="button" onClick={handleSaveEdit}>
                Save Changes
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
                  onClick={() => handleSelectVehicle(vehicle.id)}
                  className={selectedVehicleId === vehicle.id ? "selected-row" : ""}
                >
                  <td>{vehicle.id}</td>
                  <td>{vehicle.brand}</td>
                  <td>{vehicle.model}</td>
                  <td>{vehicle.vin}</td>
                  <td>{vehicle.licensePlate}</td>
                  <td>{vehicle.year}</td>
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
