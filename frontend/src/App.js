import React from "react";
import { useSelector } from "react-redux";
import { Switch, Redirect, Route } from "react-router-dom";
import CssBaseline from "@material-ui/core/CssBaseline";
import NavBar from "./containers/layouts/NavBar";
import WiemRoutes from "./containers/routes/WiemRoutes";
import RanimRoutes from "./containers/routes/RanimRoutes";
import AdminDashboard from "./containers/AdminDashboard/AdminDashboard";
import SignUp from "./containers/auth/SignUp";
import SignIn from "./containers/auth/SignIn";
import ProfilePage from "./containers/Pages/ProfilePage/ProfilePage";
import AccountPage from "./containers/Pages/AccountPage/AccountPage";
import LandingPage from "./containers/Pages/LandingPage/LandingPage";
import DashboardPage from "./containers/Pages/DashboardPage/DashboardPage";
import DefaultPage from "./containers/Pages/Default/DefaultPage";
import TestNiveauAdmin from "./containers/Pages/TestPage/TestNiveauAdmin";
import RessourcePage from "./containers/Pages/RessourcePage/RessourcePage";
import FormationPage from "./containers/Pages/FormationPage/FormationPage";
import RessourceDetail from "./containers/Pages/RessourcePage/RessourceDetail";
import Header from "./containers/AdminDashboard/Header";

function App() {
  const authDetail = useSelector((state) => state.auth);
  if (!authDetail.user)
    return (
      <div className="App">
      
        <CssBaseline />
        
        <nav>
          <NavBar />
         
        </nav>
        
        <Switch>
           {/* Landing page*/}
          <Route path="/" exact component={LandingPage} />
          <Route path="/signup" exact component={SignUp} />
          <Route path="/signin" exact component={SignIn} />
        </Switch>
       
      </div>
    );
  else if (authDetail.user.roles[0] === "ROLE_ADMIN")
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
          {/* Landing */}
          <Route path="/" exact component={LandingPage} />
          {/* dashboard page*/}
          <Route path="/dashboard" exact component={DashboardPage} />
          {/* authentication pages*/}
          <Route path="/signup" exact component={SignUp} />
          <Route path="/signin" exact component={SignIn} />
          {/* condidat profile pages */}
          <Route path={"/profile"} exact component={ProfilePage} />
          <Route path={"/account"} exact component={AccountPage} />
          {/* formations pages */}
          <Route path={"/formations"} exact component={FormationPage} />
          {/* default */}
          <Route path={"/default"} exact component={DefaultPage} />

          <Route path="/test" exact component={TestNiveauAdmin} />
          <Route path="/uploadRessource" exact component={RessourcePage} />
          <Route path="/uploadRessource/:id" exact component={RessourceDetail} />

          <WiemRoutes />
          <RanimRoutes />
          {/* redirect */}
          <Redirect to={"/"} />
        </Switch>
      </div>
    );
}

export default App;
