import { apiCallAuth } from "../apiCall/index";
import Cookies from "js-cookie";
import jwtDecode from "jwt-decode";

export const signUp = (user) => async (dispatch) => {
  try {
    dispatch({ type: "AUTH_LOADING" });
    const res = await apiCallAuth("/auth/signup", "post", user);
    dispatch({ type: "SIGNUP_USER_SUCCESS", payload: res.data });
    return res;
  } catch (err) {
    dispatch({ type: "AUTH_END_LOADING" });
    return dispatch({
      type: "SIGNUP_USER_FAILURE",
      payload: err.response.data,
    });
  }
};

export const setCurrentUser = (Cookies, jwtDecode) => async (dispatch) => {
  try {
    dispatch({ type: "AUTH_LOADING" });
    const payload = jwtDecode(Cookies.get("token"));
    dispatch({ type: "SET_CURRENT_USER_SUCCESS", payload });
  } catch (err) {
    dispatch({ type: "AUTH_END_LOADING" });
    dispatch({ type: "SET_CURRENT_USER_FAILURE", payload: err.response.data });
  }
};

export const signIn = (user) => async (dispatch) => {
  try {
    dispatch({ type: "AUTH_LOADING" });
    const res = await apiCallAuth("/auth/signin", "post", user);
    dispatch({ type: "SIGNIN_USER_SUCCESS", payload: res.data });
    Cookies.set("token", res.data.accessToken);
    dispatch(setCurrentUser(Cookies, jwtDecode));
    return res;
  } catch (err) {
    dispatch({ type: "AUTH_END_LOADING" });
    return dispatch({
      type: "SIGNIN_USER_FAILURE",
      payload: err.response.data,
    });
  }
};
export const logoutUser = () => async (dispatch) => {
  try {
    dispatch({ type: "AUTH_LOADING" });
    dispatch({ type: "LOGOUT_USER_SUCCESS" });
  } catch (err) {
    dispatch({ type: "AUTH_END_LOADING" });
    dispatch({ type: "SET_CURRENT_USER_FAILURE", payload: err.response.data });
  }
};
