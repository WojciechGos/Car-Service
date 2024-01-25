
import React, { useState, useEffect } from "react";
import Cookies from "js-cookie";

const TableWorker = () => {
  const [workers, setWorkers] = useState([]);

  useEffect(() => {

    fetchWorkers();
  }, []);


  const fetchWorkers = async () => {
    try {
      const response = await fetch("http://localhost:5001/api/v1/workers", {
        headers: {
          'Authorization': `Bearer ${Cookies.get("jwt")}`,
        },
      });
      if (response.ok) {
        const data = await response.json();
        console.log(data)
        setWorkers(data)
      } else {
        console.error("Failed to fetch workers");
      }
    } catch (error) {
      console.error("Error fetching workers:", error);
    }
  };



  return (
    <div className="table-container">
      <table className="custom-table">
        <thead>
          <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Surname</th>
            <th>role</th>
            <th>email</th>
          </tr>
        </thead>
        <tbody>
          {workers.map((item) => (
            <tr
              key={item.id}
            >
              <td>{item.id}</td>
              <td>{item.name}</td>
              <td>{item.surname}</td>
              <td>{item.role}</td>
              <td>{item.email}</td>

            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default TableWorker;
