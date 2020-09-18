import React from "react";
import { Switch, Redirect } from "react-router-dom";
import CssBaseline from "@material-ui/core/CssBaseline";
import NavBar from "./containers/layouts/NavBar";
import AkremRoutes from "./containers/routes/AkremRoutes";
import AmineRoutes from "./containers/routes/AmineRoutes";
import WiemRoutes from "./containers/routes/WiemRoutes";
import RanimRoutes from "./containers/routes/RanimRoutes";
import AdminDashboard from "./containers/AdminDashboard/AdminDashboard";

function App() {
  const admin = true;
  if (admin)
    return (
      <div className="App">
        <AdminDashboard />
      </div>
    );
  else
    return (
      <div className="App">
        <CssBaseline />
        <nav>
          <NavBar />
        </nav>
        <Switch>
          <AkremRoutes />
          <AmineRoutes />
          <WiemRoutes />
          <RanimRoutes />
          {/* redirect */}
          <Redirect to={"/"} />
        </Switch>
      </div>
    );
}

export default App;
