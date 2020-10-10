const initialState = {
    data: null,
    ressourceErr: "",
    loading: false,
  };
  
  const ressource = (state = initialState, action) => {
    switch (action.type) {
      case "RESSOURCE_LOADING":
        return {
          ...state,
          loading: true,
        };
      case "RESSOURCE_GET_DATA":
        return { ...state, data: action.payload, ressourceErr: "", loading: false };
      case "RESSOURCE_FAILURE":
        return { ...state, ressourceErr: "NO DATA FOUND", loading: false };
      case "RESSOURCE_END_LOADING":
        return {
          ...state,
          loading: false,
        };
      default:
        return state;
    }
  };
  
  export default ressource;
  