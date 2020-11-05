const initialState = {
    data: null,
    notificationErr: "",
    loading: false,
  };
  
  const notification = (state = initialState, action) => {
    switch (action.type) {
      case "NOTIFICATION_LOADING":
        return {
          ...state,
          loading: true,
        };
      case "NOTIFICATION_GET_DATA":
        return { ...state, data: action.payload, notificationErr: "", loading: false };
      case "NOTIFICATION_FAILURE":
        return { ...state, notificationErr: "NO DATA FOUND", loading: false };
      case "NOTIFICATION_END_LOADING":
        return {
          ...state,
          loading: false,
        };
      case "CLEAR_NOTIFICATION":
        return { ...state, data: null, notificationErr: "", loading: false };
      default:
        return state;
    }
  };
  
  export default notification;
  