import React from "react";
import { Switch, Route, Redirect } from "react-router-dom";
import UserList from "./containers/UserList";
import User from "./containers/User";
import NavBar from "./containers/layouts/NavBar";
import SignUp from "./containers/auth/SignUp";
import SignIn from "./containers/auth/SignIn";
import Profile from "./containers/user/Profile"
function App() {
  return (
    <div className="App">
      <nav>
        <NavBar />
      </nav>
      <Switch>
        <Route path={"/"} exact component={UserList} />
        <Route path="/signup" exact component={SignUp} />
        <Route path="/signin" exact component={SignIn} />
        <Route path={"/profile"} exact component={Profile} />


        <Route path={"/user/:user"} exact component={User} />
        <Redirect to={"/"} />
      </Switch>
    </div>
  );
}

export default App;
