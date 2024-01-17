import React from "react"
import Table from 'react-bootstrap/Table';

const TableSalersOrders = ()=>{

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
          <th>ordered quantity</th>
          <th>producer</th>
          <th>parameters</th>
          <th>price</th>
          </tr>

        </thead>
        <tbody>
          <tr>
            <td>1</td>
            <td>kierownica</td>
            <td>3</td>
            <td>xyz</td>
            <td>wełna</td>
            <td>120</td> 
          </tr>

          <tr>
            <td>2</td>
            <td>kierownica z manetkami</td>
            <td>5</td>
            <td>xxx</td>
            <td>plastik+carbon</td>
            <td>130</td>
          </tr>

          <tr>
            <td>3</td>
            <td>okno</td>
            <td>6</td>
            <td>vvv</td>
            <td>obszycie z alkantarą</td>
            <td>400</td>
          </tr>
        </tbody>
      </Table>
           

       
    )
 
}
export default TableSalersOrders