import React from "react";
import PropTypes from "prop-types";
import { ThemeProvider, withStyles } from "@material-ui/core/styles";
import CssBaseline from "@material-ui/core/CssBaseline";
import Hidden from "@material-ui/core/Hidden";
import Navigator from "./Navigator";
import UserList from "./Content/UserList";
import TestList from "./Content/TestList";
import Header from "./Header";
import { stylesPaperbase, theme, Copyright } from "./styles/Styles";
import { Switch, Redirect, Route } from "react-router-dom";
import FormationList from "./Content/FormationList";
import ProfileList from "./Content/ProfileList";
import CompetenceList from "./Content/CompetenceList";

const drawerWidth = 256;

function AdminDashboard(props) {
  const { classes } = props;
  const [mobileOpen, setMobileOpen] = React.useState(false);

  const handleDrawerToggle = () => {
    setMobileOpen(!mobileOpen);
  };

  return (
    <ThemeProvider theme={theme}>
      <div className={classes.root}>
        <CssBaseline />
        <nav className={classes.drawer}>
          <Hidden smUp implementation="js">
            <Navigator
              PaperProps={{ style: { width: drawerWidth } }}
              variant="temporary"
              open={mobileOpen}
              onClose={handleDrawerToggle}
            />
          </Hidden>
          <Hidden xsDown implementation="css">
            <Navigator PaperProps={{ style: { width: drawerWidth } }} />
          </Hidden>
        </nav>
        <div className={classes.app}>
          <Header onDrawerToggle={handleDrawerToggle} />
          <main className={classes.main}>
            <Switch>
              <Route path={"/admin_dashboard/users"} exact component={UserList} />
              <Route path={"/admin_dashboard/tests"} exact component={TestList} />
              <Route path={"/admin_dashboard/formations"} exact component={FormationList} />
              <Route path={"/admin_dashboard/profiles"} exact component={ProfileList} />
              <Route path={"/admin_dashboard/competences"} exact component={CompetenceList} />

              <Redirect to={"/admin_dashboard/users"} />
            </Switch>
          </main>
          <footer className={classes.footer}>
            <Copyright />
          </footer>
        </div>
      </div>
    </ThemeProvider>
  );
}

AdminDashboard.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(stylesPaperbase)(AdminDashboard);
