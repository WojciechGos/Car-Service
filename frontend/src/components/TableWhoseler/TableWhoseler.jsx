import React from "react"
import Table from 'react-bootstrap/Table';

const TableWhoseler = ()=>{

    return (
        <Table className="grayTable" bordered hover variant="secondary" style={{ marginTop: "10px" }}>
        <thead>
          <tr>
            <th>id</th>
            <th>Whoseler name</th>
          <th>Name</th>
          <th>Vehicle brand</th>
          <th>Count</th>
          <th>Cost</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>1</td>
            <td>Wholeseler1</td>
            <td>Stinger</td>
            <td>Kia</td>
            <td>1</td>
            <td>1200</td>
          </tr>
          <tr>
            <td>2</td>
            <td>Wholeseler1</td>
            <td>Focus</td>
            <td>Ford</td>
            <td>1</td>
            <td>400</td>
          </tr>
          <tr>
            <td>3</td>
            <td>Wholeseler3</td>
            <td>Seicento</td>
            <td>Fiat</td>
            <td>1</td>
            <td>900</td>
          </tr>
        </tbody>
      </Table>
    )
 
}
export default TableWhoseler