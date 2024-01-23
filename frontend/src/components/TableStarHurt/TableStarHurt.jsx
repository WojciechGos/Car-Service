import React from "react"
import Table from 'react-bootstrap/Table';
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import Button from 'react-bootstrap/Button';

const TableStarHurt = ()=>{
   
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
            <td>pedal gazu</td>
            <td>32</td>
            <td>xyz</td>
            <td>plastik+alkantara</td>
            <td>50</td>
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
            <td>hamulec ręczny</td>
            <td>15</td>
            <td>zzz</td>
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
            <td>3</td>
            <td>rrr</td>
            <td>szklo premium</td>
            <td>200</td>
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
export default TableStarHurt