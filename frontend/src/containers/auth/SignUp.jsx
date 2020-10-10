import React, { useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { NavLink } from "react-router-dom";
import { signUp } from "../../actions/AuthActions";
import Avatar from "@material-ui/core/Avatar";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import Checkbox from "@material-ui/core/Checkbox";
import Grid from "@material-ui/core/Grid";
import Box from "@material-ui/core/Box";
import LockOutlinedIcon from "@material-ui/icons/LockOutlined";
import Typography from "@material-ui/core/Typography";
import Container from "@material-ui/core/Container";
import { useStyles, Copyright } from "./Style";
import CircularProgress from "@material-ui/core/CircularProgress";

const SignUp = () => {
  const classes = useStyles();
  const dispatch = useDispatch();
  const authDetail = useSelector((state) => state.auth);
  const [username, setusername] = useState("");
  const [email, setemail] = useState("");
  const [password, setpassword] = useState("");
  const [confPassword, setconfPassword] = useState("");
  const [roles] = useState(["candidat"]);
  const [nameErr, setnameErr] = useState("");
  const [emailErr, setemailErr] = useState("");
  const [passwordErr, setpasswordErr] = useState("");
  const [confPasswordErr, setconfPasswordErr] = useState("");
  React.useEffect(() => {
    if(authDetail.signUpSuccess){
      document.getElementById("username").value = "";
      document.getElementById("email").value = "";
      document.getElementById("password").value = "";
      document.getElementById("confPassword").value = "";
      setusername("");
      setemail("");
      setpassword("");
      setconfPassword("");
    }
  }, [authDetail.signUpSuccess]);
  const handleChange = (e, name) => {
    const user = {};
    const emailRegEx = RegExp(
      /^[a-zA-Z0-9]+@(?:[a-zA-Z0-9]+\.)+[A-Za-z]+$/

    );
    user[name] = e.target.value;
    // validations
    switch (name) {
      case "username":
        setusername(user.username);
        user.username.length < 3
          ? setnameErr("Name must be at least 3 characters!")
          : setnameErr("");
        break;
      case "email":
        setemail(user.email);
        !emailRegEx.test(user.email)
          ? setemailErr("Invalid Email!")
          : setemailErr("");
        break;
      case "password":
        setpassword(user.password);
        user.password.length < 8
          ? setpasswordErr("Password must be at least 8 characters!")
          : setpasswordErr("");
        break;
      case "confPassword":
        setconfPassword(user.confPassword);
        user.confPassword !== password
          ? setconfPasswordErr("Passwords do not match!")
          : setconfPasswordErr("");
        break;
      default:
        break;
    }
  };

  const handleSignUp = async (e) => {
    e.preventDefault();
    if (
      username &&
      email &&
      password &&
      confPassword &&
      !nameErr &&
      !emailErr &&
      !passwordErr &&
      !confPasswordErr
    ) {
      await dispatch(signUp({ username, email, password, roles }));
    }
  };

  return (
    <Container component="main" maxWidth="xs">
      <br /><br /><br />
      <div className={classes.paper}>
        <Avatar className={classes.avatar}>
          <LockOutlinedIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Sign up
        </Typography>
        {authDetail.signUpErr && (
          <Typography variant="h6" className={classes.titleErr}>
            {authDetail.signUpErr}
          </Typography>
        )}
        {authDetail.signUpSuccess && (
          <Typography variant="h6" className={classes.titleSucc}>
            {authDetail.signUpSuccess}
          </Typography>
        )}
        <form className={classes.form} noValidate onSubmit={handleSignUp}>
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <TextField
                autoComplete="username"
                name="username"
                variant="outlined"
                required
                fullWidth
                id="username"
                label="Username"
                autoFocus
                onChange={(e) => handleChange(e, "username")}
              />
              {nameErr && (
                <small className={classes.errorText}>{nameErr}</small>
              )}
              <br></br>
            </Grid>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                id="email"
                label="Email Address"
                name="email"
                autoComplete="email"
                onChange={(e) => handleChange(e, "email")}
              />
              {emailErr && (
                <small className={classes.errorText}>{emailErr}</small>
              )}
            </Grid>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                name="password"
                label="Password"
                type="password"
                id="password"
                autoComplete="current-password"
                onChange={(e) => handleChange(e, "password")}
              />
              {passwordErr && (
                <small className={classes.errorText}>{passwordErr}</small>
              )}
            </Grid>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                name="confPassword"
                label="Confirm Password"
                type="password"
                id="confPassword"
                autoComplete="current-password"
                onChange={(e) => handleChange(e, "confPassword")}
              />
              {confPasswordErr && (
                <small className={classes.errorText}>{confPasswordErr}</small>
              )}
            </Grid>
            <Grid item xs={12}>
              <FormControlLabel
                control={<Checkbox value="allowExtraEmails" color="primary" />}
                label="I want to receive inspiration, marketing promotions and updates via email."
              />
            </Grid>
          </Grid>
          <Button
            type="submit"
            fullWidth
            variant="contained"
            color="primary"
            className={classes.submit}
          >
            Sign Up
          </Button>
          {authDetail.loading && <CircularProgress />}
          <Grid container justify="flex-end">
            <Grid item>
              <NavLink to="/signin" variant="body2" className={classes.link}>
                Already have an account? Sign in
              </NavLink>
            </Grid>
          </Grid>
        </form>
      </div>
      <Box mt={5}>
        <Copyright />
      </Box>
    </Container>
  );
};

export default SignUp;
