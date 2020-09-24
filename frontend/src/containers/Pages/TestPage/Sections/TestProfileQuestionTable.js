import React, { useEffect } from "react";
import MaterialTable from "material-table";
import { useDispatch } from "react-redux";
import { tableIcons } from "../../../tableFeatures/tableIcons";

import {
  testNiveauPostQuestion,
  testNiveauGetQuestions,
  testNiveauDeleteQuestion,
  testNiveauUpdateQuestion,
} from "../../../../actions/TestNiveauActions";

export default function TestProfileQuestionTable() {
  const [state, setState] = React.useState({
    columns: [
      { title: "Question number", field: "questionNbr", type: "numeric" },
      { title: "Description", field: "questionContent" },
      { title: "First Answer", field: "firstAnswer" },
      { title: "Second Answer", field: "secondAnswer" },
      { title: "Third Answer", field: "thirdAnswer" },
      { title: "Fourth Answer", field: "fourthAnswer" },
      {
        title: "Difficulty",
        field: "difficulty",
        lookup: { 1: "BASIC", 2: "INTERMEDIATE", 3: "HARD", 4: "PROFESSIONAL" },
      },
      {
        title: "Correct Answer",
        field: "correctAnswer",
        lookup: {
          1: "First Answer",
          2: "Second Answer",
          3: "Third Answer",
          4: "Fourth Answer",
        },
      },
    ],
    data: [],
  });

  const dispatch = useDispatch();

  const handleRowAdd = (newData, resolve) => {
    let questionToAdd = {
      questionNbr: "",
      questionContent: "",
      answers: [
        { answer_id: "0", answerContent: "", correct: false },
        { answer_id: "1", answerCotnent: "", correct: false },
        { answer_id: "2", answerContent: "", correct: false },
        { answer_id: "3", answerContent: "", correct: false },
      ],
      difficulty: "",
    };

    questionToAdd.questionNbr = newData.questionNbr;
    questionToAdd.questionContent = newData.questionContent;

    switch (newData.difficulty) {
      case "1":
        questionToAdd.difficulty = "BASIC";
        break;
      case "2":
        questionToAdd.difficulty = "INTERMEDIATE";
        break;
      case "3":
        questionToAdd.difficulty = "HARD";
        break;
      case "4":
        questionToAdd.difficulty = "PROFESSIONAL";
        break;
      default:
        break;
    }

    questionToAdd.answers[0].answerContent = newData.firstAnswer;
    questionToAdd.answers[1].answerContent = newData.secondAnswer;
    questionToAdd.answers[2].answerContent = newData.thirdAnswer;
    questionToAdd.answers[3].answerContent = newData.fourthAnswer;

    switch (newData.correctAnswer) {
      case "1":
        questionToAdd.answers[0].correct = true;
        break;
      case "2":
        questionToAdd.answers[1].correct = true;
        break;
      case "3":
        questionToAdd.answers[2].correct = true;
        break;
      case "4":
        questionToAdd.answers[3].correct = true;
        break;
      default:
        break;
    }

    dispatch(testNiveauPostQuestion(questionToAdd)).then(res => {
      resolve();
      setState((prevState) => {
        const data = [...prevState.data];
        data.push(newData);
        return { ...prevState, data };
      })
    })


  };

  const handleRowDelete = (oldData, resolve) => {
    dispatch(testNiveauDeleteQuestion(oldData.id)).then(res => {
      resolve();
      setState((prevState) => {
        const data = [...prevState.data];
        data.splice(data.indexOf(oldData), 1);
        return { ...prevState, data };
      })
    })
  };

  const handleRowUpdate = (newData, oldData, resolve) => {

    let questionToSend = {
      id: oldData.id,
      questionNbr: newData.questionNbr,
      questionContent: newData.questionContent,
      answers: [
        { answer_id: "0", answerContent: newData.firstAnswer, correct: false },
        { answer_id: "1", answerContent: newData.secondAnswer, correct: false },
        { answer_id: "2", answerContent: newData.thirdAnswer, correct: false },
        { answer_id: "3", answerContent: newData.fourthAnswer, correct: false },
      ],
      difficulty: "",
    };

    switch (newData.difficulty) {
      case 1:
        questionToSend.difficulty = "BASIC";
        break;
      case 2:
        questionToSend.difficulty = "INTERMEDIATE";
        break;
      case 3:
        questionToSend.difficulty = "HARD";
        break;
      case 4:
        questionToSend.difficulty = "PROFESSIONAL";
        break;
      default:
        break;
    }

    dispatch(testNiveauUpdateQuestion(oldData.id, questionToSend)).then(res => {
      resolve();
      if (oldData) {
        setState((prevState) => {
          const data = [...prevState.data];
          data[data.indexOf(oldData)] = newData;
          return { ...prevState, data };
        });
      }
    })
  };

  useEffect(() => {
    
  }, []);

  return (
    <MaterialTable
      title="Questions"
      columns={state.columns}
      data={state.data}
      icons={tableIcons}
      editable={{
        onRowAdd: (newData) =>
          new Promise((resolve) => {
            handleRowAdd(newData, resolve);
          }),

        onRowUpdate: (newData, oldData) =>
          new Promise((resolve) => {
            handleRowUpdate(newData, oldData, resolve);
          }),
        onRowDelete: (oldData) =>
          new Promise((resolve) => {
            handleRowDelete(oldData, resolve);
          }),
      }}
    />
  );
}
