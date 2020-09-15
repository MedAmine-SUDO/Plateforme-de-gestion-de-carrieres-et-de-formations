import { apiCallRessource } from "../apiCall/index";

export const ressourceGetData = () => async (dispatch) => {
  try {
    const res = await apiCallRessource("/", "get");
    return res;
  } catch (err) {}
};
