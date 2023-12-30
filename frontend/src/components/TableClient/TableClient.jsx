import React from "react"
import Table from 'react-bootstrap/Table';

const TableClient = ()=>{

    return (

      <Table className="grayTable" bordered hover variant="secondary" style={{ marginTop: "10px" }}>
      <thead>
        <tr>
        <th>id</th>
            <th>Name</th>
            <th>Surname</th>
            <th>E-mail</th>
            <th>Phone</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>1</td>
          <td>Mark</td>
          <td>Otto</td>
          <td>Motto@gmail.com</td>
          <td>123456789</td>
        </tr>
        <tr>
          <td>2</td>
          <td>Alex</td>
          <td>Cat</td>
          <td>Cato@sgd.en</td>
          <td>987654321</td>
        </tr>
        <tr>
          <td>3</td>
          <td>Marta</td>
          <td>Black</td>
          <td>black@wp.pl</td>
          <td>235423432</td>
        </tr>
      </tbody>
    </Table>
       
    )
 
}
export default TableClient