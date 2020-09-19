import React, { useState, useEffect } from "react";
import MaterialTable from "material-table";
import { forwardRef } from "react";
import { useDispatch } from "react-redux";

import {
  testNiveauPostQuestion,
  testNiveauGetQuestions,
} from "../../../../actions/TestNiveauActions";

import AddBox from "@material-ui/icons/AddBox";
import ArrowDownward from "@material-ui/icons/ArrowDownward";
import Check from "@material-ui/icons/Check";
import ChevronLeft from "@material-ui/icons/ChevronLeft";
import ChevronRight from "@material-ui/icons/ChevronRight";
import Clear from "@material-ui/icons/Clear";
import DeleteOutline from "@material-ui/icons/DeleteOutline";
import Edit from "@material-ui/icons/Edit";
import FilterList from "@material-ui/icons/FilterList";
import FirstPage from "@material-ui/icons/FirstPage";
import LastPage from "@material-ui/icons/LastPage";
import Remove from "@material-ui/icons/Remove";
import SaveAlt from "@material-ui/icons/SaveAlt";
import Search from "@material-ui/icons/Search";
import ViewColumn from "@material-ui/icons/ViewColumn";

const tableIcons = {
  Add: forwardRef((props, ref) => <AddBox {...props} ref={ref} />),
  Check: forwardRef((props, ref) => <Check {...props} ref={ref} />),
  Clear: forwardRef((props, ref) => <Clear {...props} ref={ref} />),
  Delete: forwardRef((props, ref) => <DeleteOutline {...props} ref={ref} />),
  DetailPanel: forwardRef((props, ref) => (
    <ChevronRight {...props} ref={ref} />
  )),
  Edit: forwardRef((props, ref) => <Edit {...props} ref={ref} />),
  Export: forwardRef((props, ref) => <SaveAlt {...props} ref={ref} />),
  Filter: forwardRef((props, ref) => <FilterList {...props} ref={ref} />),
  FirstPage: forwardRef((props, ref) => <FirstPage {...props} ref={ref} />),
  LastPage: forwardRef((props, ref) => <LastPage {...props} ref={ref} />),
  NextPage: forwardRef((props, ref) => <ChevronRight {...props} ref={ref} />),
  PreviousPage: forwardRef((props, ref) => (
    <ChevronLeft {...props} ref={ref} />
  )),
  ResetSearch: forwardRef((props, ref) => <Clear {...props} ref={ref} />),
  Search: forwardRef((props, ref) => <Search {...props} ref={ref} />),
  SortArrow: forwardRef((props, ref) => <ArrowDownward {...props} ref={ref} />),
  ThirdStateCheck: forwardRef((props, ref) => <Remove {...props} ref={ref} />),
  ViewColumn: forwardRef((props, ref) => <ViewColumn {...props} ref={ref} />),
};

export default function QuestionTable() {
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
  const [questionNbr, setquestionNbr] = useState("");
  const [questionContent, setquestionContent] = useState("");
  const [answers, setanswers] = useState([]);
  const [difficulty, setdifficulty] = useState("");

  const handleRowAdd = async (newData) => {
    let questionToAdd = {
      questionNbr: "",
      questionContent: "",
      answers: [
        { answerContent: "" },
        { answerCotnent: "" },
        { answerContent: "" },
        { answerContent: "" },
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

    console.log(questionToAdd);

    dispatch(testNiveauPostQuestion(questionToAdd));

    setState((prevState) => {
      const data = [...prevState.data];
      data.push(newData);
      return { ...prevState, data };
    });
  };

  useEffect(() => {
    dispatch(testNiveauGetQuestions()).then((res) => {
      let newQuestion = {
        questionNbr: "",
        questionContent: "",
        firstAnswer: "",
        secondAnswer: "",
        thirdAnswer: "",
        fourthAnswer: "",
        difficulty: "",
      };

      switch (res.data[0].difficulty) {
        case "BASIC":
          res.data[0].difficulty = 1;
          break;
        case "INTERMEDIATE":
          res.data[0].difficulty = 2;
          break;
        case "HARD":
          res.data[0].difficulty = 3;
          break;
        case "PROFESSIONAL":
          res.data[0].difficulty = 4;
          break;
        default:
          break;
      }

      newQuestion.questionNbr = res.data[0].questionNbr;
      newQuestion.questionContent = res.data[0].questionContent;
      newQuestion.difficulty = res.data[0].difficulty;
      newQuestion.firstAnswer = res.data[0].answers[0].answerContent;
      newQuestion.secondAnswer = res.data[0].answers[1].answerContent;
      newQuestion.thirdAnswer = res.data[0].answers[2].answerContent;
      newQuestion.fourthAnswer = res.data[0].answers[3].answerContent;

      setState((prevState) => {
        const data = [...prevState.data];
        data.push(newQuestion);
        return { ...prevState, data };
      });
    });
  }, []);

  return (
    <MaterialTable
      title="Editable Example"
      columns={state.columns}
      data={state.data}
      editable={{
        onRowAdd: (newData) =>
          new Promise((resolve) => {
            setTimeout(() => {
              resolve();
              setState((prevState) => {
                const data = [...prevState.data];
                data.push(newData);
                return { ...prevState, data };
              });
            }, 600);
          }),
        onRowUpdate: (newData, oldData) =>
          new Promise((resolve) => {
            setTimeout(() => {
              resolve();
              if (oldData) {
                setState((prevState) => {
                  const data = [...prevState.data];
                  data[data.indexOf(oldData)] = newData;
                  return { ...prevState, data };
                });
              }
            }, 600);
          }),
        onRowDelete: (oldData) =>
          new Promise((resolve) => {
            setTimeout(() => {
              resolve();
              setState((prevState) => {
                const data = [...prevState.data];
                data.splice(data.indexOf(oldData), 1);
                return { ...prevState, data };
              });
            }, 600);
          }),
      }}
    />
  );
}
