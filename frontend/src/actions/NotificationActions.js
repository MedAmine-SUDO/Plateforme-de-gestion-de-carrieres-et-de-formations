import { apiCallNotification } from "../apiCall/index";

export const notificationGetAll = () => async (dispatch) => {
    try {
      dispatch({ type: "NOTIFICATION_LOADING" });
      const res = await apiCallNotification("/all", "get");
      dispatch({ type: "NOTIFICATION_GET_DATA", payload: res.data });
      return res;
    } catch (err) {
      dispatch({ type: "NOTIFICATION_END_LOADING" });
    }
  };
export const notificationGetData = (id) => async (dispatch) => {
  try {
    dispatch({ type: "NOTIFICATION_LOADING" });
    const res = await apiCallNotification("/"+id, "get");
    dispatch({ type: "NOTIFICATION_GET_DATA", payload: res.data });
    return res;
  } catch (err) {
    dispatch({ type: "NOTIFICATION_END_LOADING" });
  }
};

export const notificationPostData = (notification) => async (dispatch) => {
  try {
    dispatch({ type: "NOTIFICATION_LOADING" });
    const res = await apiCallNotification("/add", "post",notification);
    dispatch({ type: "NOTIFICATION_GET_DATA", payload: res.data });

    return res;
  } catch (err) {
    dispatch({ type: "NOTIFICATION_END_LOADING" });
  }
};
export const notificationDeleteData = (id) => async (dispatch) => {
    try {
      dispatch({ type: "NOTIFICATION_LOADING" });
      const res = await apiCallNotification("/"+id, "delete");
      dispatch({ type: "NOTIFICATION_GET_DATA", payload: res.data });
  
      return res;
    } catch (err) {
      dispatch({ type: "NOTIFICATION_END_LOADING" });
    }
  };
  export const notificationByUSer = (id) => async (dispatch) => {
    try {
      dispatch({ type: "NOTIFICATION_LOADING" });
      const res = await apiCallNotification("/receiver/"+id, "get");
      dispatch({ type: "NOTIFICATION_GET_DATA", payload: res.data });
  
      return res;
    } catch (err) {
      dispatch({ type: "NOTIFICATION_END_LOADING" });
    }
  };
