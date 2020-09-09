import React from "react";
import { Route } from "react-router-dom";
import SignUp from "../auth/SignUp";
import SignIn from "../auth/SignIn";
import ProfilePage from "../Pages/ProfilePage/ProfilePage";
import AccountPage from "../Pages/AccountPage/AccountPage";
import LandingPage from "../Pages/LandingPage/LandingPage";
import DashboardPage from "../Pages/DashboardPage/DashboardPage";
import DefaultPage from "../Pages/Default/DefaultPage";

function AkremRoutes() {
  return (
    <>
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
      {/* default */}
      <Route path={"/default"} exact component={DefaultPage} />

    </>
  );
}

export default AkremRoutes;
