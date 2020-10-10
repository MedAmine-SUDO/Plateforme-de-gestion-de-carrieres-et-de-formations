const initialState = {
    data: null,
    competenceErr: "",
    loading: false,
  };
  
  const competence = (state = initialState, action) => {
    switch (action.type) {
      case "COMPETENCE_LOADING":
        return {
          ...state,
          loading: true,
        };
      case "COMPETENCE_GET_DATA":
        return { ...state, data: action.payload, competenceErr: "", loading: false };
      case "COMPETENCE_FAILURE":
        return { ...state, competenceErr: "NO DATA FOUND", loading: false };
      case "COMPETENCE_END_LOADING":
        return {
          ...state,
          loading: false,
        };
      case "CLEAR_COMPETENCE":
        return { ...state, data: null, competenceErr: "", loading: false };
      default:
        return state;
    }
  };
  
  export default competence;
  