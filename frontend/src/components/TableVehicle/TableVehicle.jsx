import React from "react"
import Table from 'react-bootstrap/Table';

const TableVehicle = ()=>{

    return (

      <Table className="grayTable" bordered hover variant="secondary" style={{ marginTop: "10px" }}>
      <thead>
        <tr>
        <th>id</th>
            <th>Brand</th>
            <th>Model</th>
            <th>VIN</th>
            <th>License plate</th>
            <th>Year</th>
            
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>1</td>
          <td>Mark</td>
          <td>Otto</td>
          <td>WBB21432</td>
          <td>123456789</td>
          <td>1243</td>
        </tr>
        <tr>
          <td>2</td>
          <td>Alex</td>
          <td>Cat</td>
          <td>WBA235325</td>
          <td>987654321</td>
          <td>2341</td>
        </tr>
        <tr>
          <td>3</td>
          <td>Marta</td>
          <td>Black</td>
          <td>WBD235423524</td>
          <td>235423432</td>
          <td>3421</td>
        </tr>
      </tbody>
    </Table>
       
    )
 
}
export default TableVehicle