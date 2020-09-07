import React from "react";
import { Switch, Route, Redirect } from "react-router-dom";
import NavBar from "./containers/layouts/NavBar";
import SignUp from "./containers/auth/SignUp";
import SignIn from "./containers/auth/SignIn";
import ProfilePage from "./containers/Pages/ProfilePage";
import LandingPage from "./containers/Pages/LandingPage/LandingPage";
function App() {
  return (
    <div className="App">
      <nav>
        <NavBar />
      </nav>
      <Switch>
        <Route path="/" exact component={LandingPage} />
        <Route path="/signup" exact component={SignUp} />
        <Route path="/signin" exact component={SignIn} />
        <Route path={"/profile"} exact component={ProfilePage} />
        <Redirect to={"/"} />
      </Switch>
    </div>
  );
}

export default App;
