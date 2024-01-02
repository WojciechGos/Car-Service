import React from "react"
import Table from 'react-bootstrap/Table';

const TableWarehouse = ()=>{

    return (

        <Table className="grayTable" bordered hover variant="secondary" style={{ marginTop: "10px" }}>
        <thead>
          <tr>
            <th>id</th>
          <th>Name</th>
          <th>Vehicle brand</th>
          <th>Count</th>
          <th>Cost</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>1</td>
            <td>Stinger</td>
            <td>Kia</td>
            <td>1</td>
            <td>1200</td>
          </tr>
          <tr>
            <td>2</td>
            <td>Focus</td>
            <td>Ford</td>
            <td>1</td>
            <td>400</td>
          </tr>
          <tr>
            <td>3</td>
            <td>Seicento</td>
            <td>Fiat</td>
            <td>1</td>
            <td>900</td>
          </tr>
        </tbody>
      </Table>
           

       
    )
 
}
export default TableWarehouse