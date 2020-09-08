import React from "react";
import { Switch, Route, Redirect } from "react-router-dom";
import CssBaseline from "@material-ui/core/CssBaseline";
import NavBar from "./containers/layouts/NavBar";
import SignUp from "./containers/auth/SignUp";
import SignIn from "./containers/auth/SignIn";
import ProfilePage from "./containers/Pages/ProfilePage/ProfilePage";
import LandingPage from "./containers/Pages/LandingPage/LandingPage";
import DashboardPage from "./containers/Pages/DashboardPage/DashboardPage";
import AccountPage from "./containers/Pages/AccountPage/AccountPage";
function App() {
  return (
    <div className="App">
      <CssBaseline />
      <nav>
        <NavBar />
      </nav>
      <Switch>
        {/* Landing */}
        <Route path="/" exact component={LandingPage} />
        {/* dashboard */}
        <Route path="/dashboard" exact component={DashboardPage} />
        {/* authentication */}
        <Route path="/signup" exact component={SignUp} />
        <Route path="/signin" exact component={SignIn} />
        {/* condidat profile */}
        <Route path={"/profile"} exact component={ProfilePage} />
        <Route path={"/account"} exact component={AccountPage} />
        {/* redirect */}
        <Redirect to={"/"} />
      </Switch>
    </div>
  );
}

export default App;
