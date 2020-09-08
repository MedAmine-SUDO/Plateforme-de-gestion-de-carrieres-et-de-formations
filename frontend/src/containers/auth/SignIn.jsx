import React, { useState } from 'react';
import { useSelector,  useDispatch } from "react-redux";
import { NavLink } from 'react-router-dom';
import { signIn } from "../../actions/AuthActions"
import { Redirect } from "react-router-dom";
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';
import Link from '@material-ui/core/Link';
import Grid from '@material-ui/core/Grid';
import Box from '@material-ui/core/Box';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Typography from '@material-ui/core/Typography';
import Container from '@material-ui/core/Container';
import { useStyles, Copyright } from "./Style"
import CircularProgress from '@material-ui/core/CircularProgress';

const SignIn = () => {
  const classes = useStyles();
  const dispatch = useDispatch();
  const state = useSelector((state) => state.auth);
  const [username, setusername] = useState('');
  const [password, setpassword] = useState('');
  const [nameErr, setnameErr] = useState('');
  const [passwordErr, setpasswordErr] = useState('');

  const handleChange = (e, name) => {
    const user = {};
    user[name] = e.target.value;
    // validations
    switch (name) {
      case 'username':
        setusername(user.username);
        user.username.length < 3 ? setnameErr('Name must be at least 3 characters!') : setnameErr('');
        break;
      case 'password':
        setpassword(user.password);
        user.password.length < 8 ? setpasswordErr('Password must be at least 8 characters!') : setpasswordErr('');
        break;
      default:
        break;
    }
  }

  const handleSignIn = async (e) => {
    e.preventDefault();
    if (username && password && !nameErr && !passwordErr) {
      dispatch(signIn({ username, password }));
    }
  }
  const authDetail = useSelector((state) => state.auth);
  if(authDetail.isAuthenticated)
    return <Redirect to={"/"} />
  else
  return (
    <Container component="main" maxWidth="xs">
      <br /><br /><br />
      <div className={classes.paper}>
        <Avatar className={classes.avatar}>
          <LockOutlinedIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Sign in
        </Typography>
         {state.signInErr && (
          <Typography variant="h6" className={classes.titleErr}>
            The username or password is incorrect! Try again.
          </Typography>
        )}
        <form className={classes.form} noValidate onSubmit={handleSignIn}>
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="username"
            label="Username"
            name="username"
            autoComplete="username"
            autoFocus
            onChange={(e) => handleChange(e, 'username')}
          />
          {nameErr && <small className={classes.errorText}>{nameErr}</small>}<br></br>
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            name="password"
            label="Password"
            type="password"
            id="password"
            autoComplete="current-password"
            onChange={(e) => handleChange(e, 'password')}
          />
          {passwordErr && <small className={classes.errorText}>{passwordErr}</small>}<br></br>
          <FormControlLabel
            control={<Checkbox value="remember" color="primary" />}
            label="Remember me"
          />
          <Button
            type="submit"
            fullWidth
            variant="contained"
            color="primary"
            className={classes.submit}
          >
            Sign In
          </Button>
          {state.loading &&  <CircularProgress />}
          <Grid container>
            <Grid item xs>
              <Link href="#" variant="body2">
                Forgot password?
              </Link>
            </Grid>
            <Grid item>
              <NavLink to="/signup" className={classes.link} variant="body2" >{"Don't have an account? Sign Up"}</NavLink>
            </Grid>
          </Grid>
        </form>
      </div>
      <Box mt={8}>
        <Copyright />
      </Box>
    </Container>
    
  )
}

export default SignIn
