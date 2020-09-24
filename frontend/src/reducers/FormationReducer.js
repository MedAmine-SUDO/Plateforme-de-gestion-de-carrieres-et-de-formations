const initialState = {
    data: null,
    formationErr: "",
    loading: false,
  };
  
  const formation = (state = initialState, action) => {
    switch (action.type) {
      case "FORMATION_LOADING":
        return {
          ...state,
          loading: true,
        };
      case "FORMATION_GET_DATA":
        return { ...state, data: action.payload, formationErr: "", loading: false };
      case "FORMATION_FAILURE":
        return { ...state, formationErr: "NO DATA FOUND", loading: false };
      case "FORMATION_END_LOADING":
        return {
          ...state,
          loading: false,
        };
      case "CLEAR_FORMATION":
        return { ...state, data: null, formationErr: "", loading: false };
      default:
        return state;
    }
  };
  
  export default formation;
  