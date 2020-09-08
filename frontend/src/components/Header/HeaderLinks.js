/*eslint-disable*/
import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
// react components for routing our app without refresh
import { Link, NavLink } from "react-router-dom";
import Cookies from "js-cookie";
import jwtDecode from "jwt-decode";
import { setCurrentUser } from "../../actions/AuthActions";
import { logoutUser } from "../../actions/AuthActions";
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";

// @material-ui/icons
import { SupervisedUserCircle, MeetingRoom ,ExitToApp,AddBox} from "@material-ui/icons";

// core components
import CustomDropdown from "../CustomDropdown/CustomDropdown.js";
import Button from "../CustomButtons/Button.js";
import styles from "../../assets/jss/material-kit-react/components/headerLinksStyle.js";

const useStyles = makeStyles(styles);

export default function HeaderLinks(props) {
  const dispatch = useDispatch();
  const authDetail = useSelector((state) => state.auth);
  useEffect(() => {
    const userCheck = () => {
      dispatch(setCurrentUser(Cookies, jwtDecode));
    };
    if (Cookies.get("token")) {
      userCheck();
    }
  }, [dispatch, authDetail.isAuthenticated]);

  const handleLogout = () => {
    dispatch(logoutUser());
    Cookies.set("token", "");
  };
  const classes = useStyles();
  return (
    <List className={classes.list}>
      {authDetail.user ? (
        <>
          <ListItem className={classes.listItem}>
            <CustomDropdown
              noLiPadding
              buttonText="Profile"
              buttonProps={{
                className: classes.navLink,
                color: "transparent",
              }}
              buttonIcon={SupervisedUserCircle}
              dropdownList={[
                <NavLink to="/profile" className={classes.dropdownLink}>
                  See Profile
                </NavLink>,
                 <NavLink to="/account" className={classes.dropdownLink}>
                 Account
               </NavLink>,
              ]}
            />
          </ListItem>
          <ListItem className={classes.listItem}>
            <Button
              onClick={handleLogout}
              color="transparent"
              target="_blank"
              className={classes.navLink}
            >
              <MeetingRoom className={classes.icons} /> Logout
            </Button>
          </ListItem>
        </>
      ) : (
        <>
          <ListItem className={classes.listItem}>
            <NavLink to="/signin" className={classes.link}>
              <Button
                color="transparent"
                target="_blank"
                className={classes.navLink}
              >
                <ExitToApp className={classes.icons} /> Sign In
              </Button>
            </NavLink>
          </ListItem>
          <ListItem className={classes.listItem}>
            <NavLink to="/signup" className={classes.link}>
              <Button
                color="transparent"
                target="_blank"
                className={classes.navLink}
              >
                <AddBox className={classes.icons} /> Sign Up
              </Button>
            </NavLink>
          </ListItem>
        </>
      )}
    </List>
  );
}
