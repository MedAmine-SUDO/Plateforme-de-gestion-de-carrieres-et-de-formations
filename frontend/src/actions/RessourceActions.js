import { apiCallRessource } from "../apiCall/index";

export const ressourceGetData = () => async (dispatch) => {
  try {
    const res = await apiCallRessource("/", "get");
    return res;
  } catch (err) {}
};
export const ressourcePostData = (ressource) => async (dispatch) => {
  try {
    console.log(ressource)

    dispatch({ type: "RESSOURCE_LOADING" });
    const res = await apiCallRessource("/add/", "POST",ressource);

    dispatch({ type: "RESSOURCE_GET_DATA", payload: res.data });

    return res;
  } catch (err) {
    dispatch({ type: "RESSOURCE_END_LOADING" });

  }
};


