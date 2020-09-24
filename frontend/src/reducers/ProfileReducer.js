const initialState = {
  data: null,
  profileErr: "",
  loading: false,
};

const profile = (state = initialState, action) => {
  switch (action.type) {
    case "PROFILE_LOADING":
      return {
        ...state,
        loading: true,
      };
    case "PROFILE_GET_DATA":
      return { ...state, data: action.payload, profileErr: "", loading: false };
    case "PROFILE_FAILURE":
      return { ...state, profileErr: "NO DATA FOUND", loading: false };
    case "PROFILE_END_LOADING":
      return {
        ...state,
        loading: false,
      };
    case "CLEAR_PROFILE":
      return { ...state, data: null, profileErr: "", loading: false };
    default:
      return state;
  }
};

export default profile;
