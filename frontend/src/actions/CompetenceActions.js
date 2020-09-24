import { apiCallCompetence } from "../apiCall/index";

export const competenceGetAll = () => async (dispatch) => {
    try {
      dispatch({ type: "COMPETENCE_LOADING" });
      const res = await apiCallCompetence("/all", "get");
      dispatch({ type: "COMPETENCE_GET_DATA", payload: res.data });
      return res;
    } catch (err) {
      dispatch({ type: "COMPETENCE_END_LOADING" });
    }
  };
export const competenceGetData = (id) => async (dispatch) => {
  try {
    dispatch({ type: "COMPETENCE_LOADING" });
    const res = await apiCallCompetence("/"+id, "get");
    dispatch({ type: "COMPETENCE_GET_DATA", payload: res.data });
    return res;
  } catch (err) {
    dispatch({ type: "COMPETENCE_END_LOADING" });
  }
};
export const competenceUpdateData = (competence) => async (dispatch) => {
  try {
    dispatch({ type: "COMPETENCE_LOADING" });
    const res = await apiCallCompetence("/", "put",competence);
    dispatch({ type: "COMPETENCE_GET_DATA", payload: res.data });
    return res;
  } catch (err) {
    dispatch({ type: "COMPETENCE_END_LOADING" });
  }
};
export const competencePostData = (competence) => async (dispatch) => {
  try {
    dispatch({ type: "COMPETENCE_LOADING" });
    const res = await apiCallCompetence("/", "post",competence);
    dispatch({ type: "COMPETENCE_GET_DATA", payload: res.data });

    return res;
  } catch (err) {
    dispatch({ type: "COMPETENCE_END_LOADING" });
  }
};
export const competenceDeleteData = (id) => async (dispatch) => {
    try {
      dispatch({ type: "COMPETENCE_LOADING" });
      const res = await apiCallCompetence("/"+id, "delete");
      dispatch({ type: "COMPETENCE_GET_DATA", payload: res.data });
  
      return res;
    } catch (err) {
      dispatch({ type: "COMPETENCE_END_LOADING" });
    }
  };
