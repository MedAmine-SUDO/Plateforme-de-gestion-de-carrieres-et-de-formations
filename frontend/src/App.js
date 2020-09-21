import React from "react";
import { useSelector } from "react-redux";
import { Switch, Redirect, Route } from "react-router-dom";
import CssBaseline from "@material-ui/core/CssBaseline";
import NavBar from "./containers/layouts/NavBar";
import AkremRoutes from "./containers/routes/AkremRoutes";
import AmineRoutes from "./containers/routes/AmineRoutes";
import WiemRoutes from "./containers/routes/WiemRoutes";
import RanimRoutes from "./containers/routes/RanimRoutes";
import AdminDashboard from "./containers/AdminDashboard/AdminDashboard";
import SignUp from "./containers/auth/SignUp";
import SignIn from "./containers/auth/SignIn";

function App() {
  // const authDetail = useSelector((state) => state.auth);
  // if (!authDetail.user)
  //   return (
  //     <div className="App">
  //       <CssBaseline />
  //       <nav>
  //         <NavBar />
  //       </nav>
  //       <Switch>
  //         <Route path="/signup" exact component={SignUp} />
  //         <Route path="/signin" exact component={SignIn} />
  //         <Redirect to={"/signin"} />
  //       </Switch>
  //     </div>
  //   );
  // else if (authDetail.user.roles[0] === "ROLE_ADMIN")
    return (
      <div className="App">
        <AdminDashboard />
      </div>
    );
  // else
  //   return (
  //     <div className="App">
  //       <CssBaseline />
  //       <nav>
  //         <NavBar />
  //       </nav>
  //       <Switch>
  //         <AkremRoutes />
  //         <AmineRoutes />
  //         <WiemRoutes />
  //         <RanimRoutes />
  //         {/* redirect */}
  //         <Redirect to={"/"} />
  //       </Switch>
  //     </div>
  //   );
}

export default App;
