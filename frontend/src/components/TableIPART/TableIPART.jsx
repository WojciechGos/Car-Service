import React from "react"
import Table from 'react-bootstrap/Table';
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import Button from 'react-bootstrap/Button';

const TableIPART = ()=>{

    const tableStyle = {
        fontSize: '1.5rem', 
        marginTop: '40px',
        textAlign: 'center'
      };

    return (

        <Table className="grayTable" bordered hover variant="secondary" style={tableStyle}>
        <thead>
          <tr>
            <th>id</th>
          <th>name</th>
          <th>quantity</th>
          <th>producer</th>
          <th>parameters</th>
          <th>price</th>
          <th></th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>1</td>
            <td>kierka</td>
            <td>3</td>
            <td>xyz</td>
            <td>wełna</td>
            <td>120</td>
            <td>
            <InputGroup className="mb-3">
        <Form.Control
          placeholder="quantity"
          aria-label="quantity"
        />
        <Button variant="primary" id="button-addon2">
          add
        </Button>
      </InputGroup>
            </td>
          </tr>
          <tr>
            <td>2</td>
            <td>kierka</td>
            <td>5</td>
            <td>xxx</td>
            <td>plastik</td>
            <td>130</td>
            <td>
            <InputGroup className="mb-3">
        <Form.Control
          placeholder="quantity"
          aria-label="quantity"
        />
        <Button variant="primary" id="button-addon2">
          add
        </Button>
      </InputGroup>
            </td>
          </tr>
          <tr>
            <td>3</td>
            <td>okno</td>
            <td>6</td>
            <td>vvv</td>
            <td>obszycie alkantara</td>
            <td>400</td>
            <td>
            <InputGroup className="mb-3">
        <Form.Control
          placeholder="quantity"
          aria-label="quantity"
        />
        <Button variant="primary" id="button-addon2">
          add
        </Button>
      </InputGroup>
            </td>
          </tr>
        </tbody>
      </Table>
           

       
    )
 
}
export default TableIPART