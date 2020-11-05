import React, { useEffect, useState } from "react";
import PropTypes from 'prop-types';
import ExitToAppIcon from "@material-ui/icons/ExitToApp";
import AppBar from '@material-ui/core/AppBar';
import Avatar from '@material-ui/core/Avatar';
import Grid from '@material-ui/core/Grid';
import Hidden from '@material-ui/core/Hidden';
import IconButton from '@material-ui/core/IconButton';
import Link from '@material-ui/core/Link';
import MenuIcon from '@material-ui/icons/Menu';
import NotificationsIcon from '@material-ui/icons/Notifications';
import Toolbar from '@material-ui/core/Toolbar';
import Tooltip from '@material-ui/core/Tooltip';
import { withStyles } from '@material-ui/core/styles';
import { stylesHeader } from "./styles/Styles"
import { logoutUser } from "../../actions/AuthActions";
import Cookies from "js-cookie";
import { Badge } from "@material-ui/core";
import { notificationByUSer } from "../../actions/NotificationActions";
import { useDispatch, useSelector } from "react-redux";

function Header(props) {
  const { classes, onDrawerToggle } = props;
  const dispatch = useDispatch();
  const authData = useSelector((state) => state.auth);
  const notyData = useSelector((state) => state.notification);
  const [dataNoSeen, setDataNoSeen] = useState([]);
  const [countNoti, setCountNoti] = useState(0);
  const handleLogout = () => {
    dispatch(logoutUser());
    Cookies.set("token", "");
  };

  useEffect(() => {
    if (!notyData.data) dispatch(notificationByUSer(authData.user.id));
      setInterval(
        () => dispatch(notificationByUSer(authData.user.id)),
        60000
      );
  }, [setInterval]); // eslint-disable-line react-hooks/exhaustive-deps
  useEffect(() => {
    if (notyData.data) {
      if (notyData.data.length !== dataNoSeen.length) {
        let dataNoSeenArray = [];
        notyData.data.forEach((item) => {
          if (item.seen === false) dataNoSeenArray = [...dataNoSeenArray, item];
        });
        setDataNoSeen(dataNoSeenArray);
        setCountNoti(dataNoSeenArray.length);
      }
    }
  }, [notyData]); // eslint-disable-line react-hooks/exhaustive-deps

  return (
    <React.Fragment>
      <AppBar color="primary" position="sticky" elevation={0}>
        <Toolbar>
          <Grid container spacing={1} alignItems="center">
            <Hidden smUp>
              <Grid item>
                <IconButton
                  color="inherit"
                  aria-label="open drawer"
                  onClick={onDrawerToggle}
                  className={classes.menuButton}
                >
                  <MenuIcon />
                </IconButton>
              </Grid>
            </Hidden>
            <Grid item xs />
            <Grid item>
              <Tooltip title={countNoti>0?"  nombres des notifications "+countNoti:"No Notifications"}>
                <IconButton color="inherit">
                  <Badge badgeContent={countNoti} max={10} color="primary">
                    <NotificationsIcon />
                  </Badge>
                </IconButton>
              </Tooltip>
            </Grid>
            <Grid item>
              <Link onClick={handleLogout} className={classes.link} href="#" variant="body2">
                Logout
              </Link>
            </Grid>
            <Grid item>
              <Tooltip title="Alerts • No alerts">
                <IconButton color="inherit">
                  <NotificationsIcon />
                  
                </IconButton>
              </Tooltip>
            </Grid>
            <Grid item>
              <IconButton color="inherit" className={classes.iconButtonAvatar}>
                <Avatar src="/static/images/avatar/1.jpg" alt="My Avatar" />
              </IconButton>
            </Grid>
          </Grid>
        </Toolbar>
      </AppBar>
    </React.Fragment>
  );
}

Header.propTypes = {
  classes: PropTypes.object.isRequired,
  onDrawerToggle: PropTypes.func.isRequired,
};

export default withStyles(stylesHeader)(Header);
