import { apiCallFormation } from "../apiCall/index";

export const formationGetAll = () => async (dispatch) => {
    try {
      dispatch({ type: "FORMATION_LOADING" });
      const res = await apiCallFormation("/all", "get");
      dispatch({ type: "FORMATION_GET_DATA", payload: res.data });
      return res;
    } catch (err) {
      dispatch({ type: "FORMATION_END_LOADING" });
    }
  };
export const formationGetData = (id) => async (dispatch) => {
  try {
    dispatch({ type: "FORMATION_LOADING" });
    const res = await apiCallFormation("/"+id, "get");
    dispatch({ type: "FORMATION_GET_DATA", payload: res.data });
    return res;
  } catch (err) {
    dispatch({ type: "FORMATION_END_LOADING" });
  }
};
export const formationUpdateData = (formation) => async (dispatch) => {
  try {
    dispatch({ type: "FORMATION_LOADING" });
    const res = await apiCallFormation("/", "put",formation);
    dispatch({ type: "FORMATION_GET_DATA", payload: res.data });
    return res;
  } catch (err) {
    dispatch({ type: "FORMATION_END_LOADING" });
  }
};
export const formationPostData = (formation) => async (dispatch) => {
  try {
    dispatch({ type: "FORMATION_LOADING" });
    const res = await apiCallFormation("/", "post",formation);
    dispatch({ type: "FORMATION_GET_DATA", payload: res.data });

    return res;
  } catch (err) {
    dispatch({ type: "FORMATION_END_LOADING" });
  }
};
export const formationDeleteData = (id) => async (dispatch) => {
    try {
      dispatch({ type: "FORMATION_LOADING" });
      const res = await apiCallFormation("/"+id, "delete");
      dispatch({ type: "FORMATION_GET_DATA", payload: res.data });
  
      return res;
    } catch (err) {
      dispatch({ type: "FORMATION_END_LOADING" });
    }
  };
