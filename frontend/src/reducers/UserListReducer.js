const DefaultState = {
  loading: false,
  data: [],
  errorMsg:""
};
const UserListReducer = (state = DefaultState, action) => {
  switch (action.type) {
      case "USER_LIST_LOADING":
          return {
              ...state,
              loading: true
          };
      case "USER_LIST_SUCCESS":
        return {
            ...state,
            loading: false,
            data: action.payload,
            errorMsg:""
        };
      case "USER_LIST_FAIL":
        return {
            ...state,
            loading: false,
            errorMsg: "unable to get user"
        };
    default:
      return state;
  }
};
export default UserListReducer;