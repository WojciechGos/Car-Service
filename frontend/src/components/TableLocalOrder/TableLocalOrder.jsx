import React from "react"
import Table from 'react-bootstrap/Table';
import { Badge } from "react-bootstrap";

const TableLocalOrder = ()=>{

    return (

        <Table className="grayTable" bordered hover variant="secondary" style={{ marginTop: "10px" }}>
        <thead>
          <tr>
            <th>id</th>
          <th>Name</th>
          <th>Vehicle brand</th>
          <th>Price</th>
          <th>Cost</th>
          <th>Mechanic</th>
          <th>Date order</th>
          <th>Status</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>1</td>
            <td>Stinger</td>
            <td>Kia</td>
            <td>1</td>
            <td>1200</td>
            <td>1</td>
            <td>1</td>
            <td>
              <h6>
                <Badge bg="danger">cancelled</Badge>
              </h6>
            </td>
          </tr>
          <tr>
            <td>2</td>
            <td>Focus</td>
            <td>Ford</td>
            <td>1</td>
            <td>400</td>
            <td>1</td>
            <td>1</td>
            <td>
              <h6>
                <Badge bg="success">success</Badge>
              </h6>
            </td>
          </tr>
          <tr>
            <td>3</td>
            <td>Seicento</td>
            <td>Fiat</td>
            <td>1</td>
            <td>900</td>
            <td>1</td>
            <td>1</td>
            <td>
              <h6>
                <Badge bg="warning">in progress</Badge>
              </h6>
            </td>
          </tr>
        </tbody>
      </Table>
           

       
    )
 
}
export default TableLocalOrder