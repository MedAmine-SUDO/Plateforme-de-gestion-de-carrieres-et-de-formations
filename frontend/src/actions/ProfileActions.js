import { apiCallProfile } from "../apiCall/index";

export const profileGetData = (idUser) => async (dispatch) => {
  try {
    dispatch({ type: "PROFILE_LOADING" });
    const res = await apiCallProfile("/"+idUser, "get");
    dispatch({ type: "PROFILE_GET_DATA", payload: res.data });
    return res;
  } catch (err) {
    dispatch({ type: "PROFILE_END_LOADING" });
    return dispatch({
      type: "PROFILE_FAILURE",
      payload: err,
    });
  }
};
export const profileUpdateData = (profile) => async (dispatch) => {
  try {
    dispatch({ type: "PROFILE_LOADING" });
    const res = await apiCallProfile("/", "put",profile);
    dispatch({ type: "PROFILE_GET_DATA", payload: res.data });
    return res;
  } catch (err) {
    dispatch({ type: "PROFILE_END_LOADING" });

  }
};
export const profilePostData = (profile) => async (dispatch) => {
  try {
    dispatch({ type: "PROFILE_LOADING" });
    const res = await apiCallProfile("/", "post",profile);
    dispatch({ type: "PROFILE_GET_DATA", payload: res.data });

    return res;
  } catch (err) {
    dispatch({ type: "PROFILE_END_LOADING" });

  }
};
