import React from "react"
import Table from 'react-bootstrap/Table';

const TableCommission = ()=>{

    return (

        <Table className="grayTable" bordered hover variant="secondary" style={{ marginTop: "10px" }}>
        <thead>
          <tr>
            <th>id</th>
          <th>Create date</th>
          <th>End date</th>
          <th>ID vehicle</th>
          <th>ID client</th>
          <th>Cost estimate</th>
          <th>Contractor</th>
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
          </tr>
          <tr>
            <td>2</td>
            <td>Focus</td>
            <td>Ford</td>
            <td>1</td>
            <td>400</td>
            <td>1</td>
            <td>1</td>
          </tr>
          <tr>
            <td>3</td>
            <td>Seicento</td>
            <td>Fiat</td>
            <td>1</td>
            <td>1</td>
            <td>1</td>
            <td>900</td>
          </tr>
        </tbody>
      </Table>
           

       
    )
 
}
export default TableCommission