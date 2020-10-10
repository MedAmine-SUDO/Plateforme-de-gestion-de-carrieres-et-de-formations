import { apiCalltestNiveau } from "../apiCall/index";

export const testNiveauPostQuestion = (question) => async (dispatch) => {
  const res = await apiCalltestNiveau("/question", "post", question);
  return res;
};

export const testNiveauGetQuestions = () => async (dispatch) => {
  try {
    dispatch({ type: "TEST_NIVEAU_LOADING" });
    const res = await apiCalltestNiveau("/question", "get");
    dispatch({ type: "TEST_NIVEAU_END_LOADING" });

    return res;
  } catch (err) {
    dispatch({ type: "TEST_NIVEAU_END_LOADING" });
  }
};

export const testNiveauDeleteQuestion = (idQuestion) => async (dispatch) => {
  const res = await apiCalltestNiveau("/question/" + idQuestion, "delete");
  return res;
};

export const testNiveauUpdateQuestion = (idQuestion, question) => async (
  dispatch
) => {
  const res = await apiCalltestNiveau(
    "/question/" + idQuestion,
    "put",
    question
  );
  return res;
};
