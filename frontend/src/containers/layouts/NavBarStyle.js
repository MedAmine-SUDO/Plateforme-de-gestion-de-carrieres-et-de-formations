import React from "react"
import { makeStyles } from '@material-ui/core/styles';
const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
  },
  menuButton: {
    marginRight: theme.spacing(2),
  },
  title: {
    flexGrow: 1,
  },
  link:{
    textDecoration: "none",
    color: "white"
  },
  linkMenu:{
    textDecoration: "none",
    color: "black"
  }
}));
export { useStyles }
