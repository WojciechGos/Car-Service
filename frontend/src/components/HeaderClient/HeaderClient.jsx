import React, { useState } from "react";
import Button from "react-bootstrap/Button";
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import { Link } from "react-router-dom";
import PATH from "../../paths";

const HeaderClient = ({ onFilterChange, onEditClient, onSaveEdit, isEditingClient }) => {
  const [searchName, setSearchName] = useState('');
  const [editedClientId, setEditedClientId] = useState(null);
  const [editedClientName, setEditedClientName] = useState("");

  const handleSearch = () => {
    onFilterChange(searchName);
  };

  const handleToggleEdit = () => {
    onEditClient(!isEditingClient); 
    setEditedClientId(null);
    setEditedClientName("");
  };

  const handleSaveEdit = () => {
    onSaveEdit(editedClientId, editedClientName);
    setEditedClientId(null);
    setEditedClientName("");
  };

  const buttonStyle = {
    marginTop: "10px",
    fontSize: "32px", 
    fontFamily: "'Extra Bolt Italic', sans-serif"
  };

  const buttonStyleClient = {
    fontSize: "32px", 
    marginTop: "10px",
    marginLeft:"700px",
    fontFamily: "'Extra Bolt Italic', sans-serif"
  };

  return (
    <div>
      <Button variant="danger" style={buttonStyle} >Clients</Button>{' '}
      <Link to={PATH.VEHICLE}>
        <Button variant="light" style={buttonStyle}>Vehicles</Button>{' '}
      </Link>
      <br/>
      <InputGroup className="inputSearch">
        <Form.Control
          placeholder="Enter name"
          aria-label="Enter name"
          aria-describedby="basic-addon2"
          value={searchName}
          onChange={(e) => setSearchName(e.target.value)}
        />
        <Button variant="secondary" id="button-addon2" onClick={handleSearch}>
          Search
        </Button>
      </InputGroup>
      <br />
      <Link to={PATH.CLIENTADD}>
        <Button variant="light" style={buttonStyle}>Add client</Button>
      </Link>
      <Button variant="light" style={buttonStyleClient} onClick={handleToggleEdit}>
        {isEditingClient ? 'Cancel Edit' : 'Edit Client'}
      </Button>{' '}

      {isEditingClient && (
        <div>
          <p>Editing Client: {editedClientId}</p>
          <form>
            <label>
              New Name:
              <input
                type="text"
                value={editedClientName}
                onChange={(e) => setEditedClientName(e.target.value)}
              />
            </label>
            <Button variant="primary" type="button" onClick={handleSaveEdit}>
              Save Changes
            </Button>
          </form>
        </div>
      )}
    </div>
  );
};

export default HeaderClient;
