const CostTable = ({costData}) => {

    console.log(costData)
  return (
    <div className="table-container">
      <table className="custom-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Create date</th>
            <th>End date</th>
            <th>ID vehicle</th>
            <th>ID client</th>
            <th>Cost estimate</th>
            <th>Contractor</th>
          </tr>
        </thead>
        <tbody>
          {costData.spareParts.map((item) => (
            <tr
              key={item.id}
            //   onClick={() => handleitemClick(item.id)}
            >
              <td>{item.id}</td>
              <td>
                {item.createDate instanceof Date
                  ? item.createDate.toLocaleString()
                  : item.createDate}
              </td>
              <td>
                {item.endDate instanceof Date
                  ? item.endDate.toLocaleString()
                  : item.endDate}
              </td>
              <td>{item.vehicle.id}</td>
              <td>{item.client.id}</td>
              <td>{item.costEstimate}</td>
              <td>
                {item.contractor.name} {item.contractor.surname}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default CostTable;
