import axios from "axios";

export const GetUserList = () => async (dispatch) => {
  try {
    dispatch({
      type: "USER_LIST_LOADING",
    });
    const res = await axios.get("http://localhost:8081/api/");
    dispatch({
      type: "USER_LIST_SUCCESS",
      payload: res.data,
    });
  } catch (e) {
    dispatch({
      type: "USER_LIST_FAIL",
    });
  }
};
