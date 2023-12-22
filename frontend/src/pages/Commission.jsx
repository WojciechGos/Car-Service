import Sidebar from "../components/Sidebar/SideBar"
import Header from "../components/Header/Header"
import ClientTable from "../components/ClientTable/ClientTable"

const Commission = ()=>{
    return (
        <div style={{display: "flex"}}>
            <Sidebar />
            
            <div>
            <Header />
           <ClientTable />
           
           </div>
        </div>
       
           
            
    )
}

export default Commission