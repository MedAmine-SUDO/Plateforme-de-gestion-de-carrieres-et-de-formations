import { apiCalltestNiveau } from "../apiCall/index";

export const testNiveauPostQuestion = (question) => async (dispatch)  => {
    const res = await apiCalltestNiveau("/question", "post",question);
    return res;
};

export const testNiveauGetQuestions = () => async (dispatch) => {
      const res = await apiCalltestNiveau("/question", "get");
      return res;
};

export const testNiveauDeleteQuestion = (idQuestion) => async (dispatch) => {
    const res = await apiCalltestNiveau("/question/"+idQuestion, "delete");
    return res;
}
