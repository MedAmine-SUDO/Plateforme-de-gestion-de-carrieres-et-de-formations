// import React, { useEffect } from "react";
// import MaterialTable from "material-table";
// import { useDispatch } from "react-redux";
// import { tableIcons } from "../../../tableFeatures/tableIcons";

// import {
//   testNiveauPostQuestion,
//   testNiveauGetQuestions,
//   testNiveauDeleteQuestion,
//   testNiveauUpdateQuestion,
// } from "../../../../actions/TestNiveauActions";

// export default function TestNiveauQuestionTable() {
//   const [state, setState] = React.useState({
//     columns: [
//       { title: "Question number", field: "questionNbr", type: "numeric" },
//       { title: "Description", field: "questionContent" },
//       // { title: "First Answer", field: "firstAnswer" },
//       // { title: "Second Answer", field: "secondAnswer" },
//       // { title: "Third Answer", field: "thirdAnswer" },
//       // { title: "Fourth Answer", field: "fourthAnswer" },
//       {
//         title: "Difficulty",
//         field: "difficulty",
//         lookup: { 1: "BASIC", 2: "INTERMEDIATE", 3: "HARD", 4: "PROFESSIONAL" },
//       },
//       {
//         title: "Correct Answer",
//         field: "correctAnswer",
//         lookup: {
//           1: "First Answer",
//           2: "Second Answer",
//           3: "Third Answer",
//           4: "Fourth Answer",
//         },
//       },
//     ],
//     data: [],
//   });

//   const dispatch = useDispatch();

//   const handleRowAdd = (newData, resolve) => {
//     let questionToAdd = {
//       questionNbr: "",
//       questionContent: "",
//       answers: [
//         { answer_id: "0", answerContent: "", correct: false },
//         { answer_id: "1", answerCotnent: "", correct: false },
//         { answer_id: "2", answerContent: "", correct: false },
//         { answer_id: "3", answerContent: "", correct: false },
//       ],
//       difficulty: "",
//     };

//     questionToAdd.questionNbr = newData.questionNbr;
//     questionToAdd.questionContent = newData.questionContent;

//     switch (newData.difficulty) {
//       case "1":
//         questionToAdd.difficulty = "BASIC";
//         break;
//       case "2":
//         questionToAdd.difficulty = "INTERMEDIATE";
//         break;
//       case "3":
//         questionToAdd.difficulty = "HARD";
//         break;
//       case "4":
//         questionToAdd.difficulty = "PROFESSIONAL";
//         break;
//       default:
//         break;
//     }

//     questionToAdd.answers[0].answerContent = newData.firstAnswer;
//     questionToAdd.answers[1].answerContent = newData.secondAnswer;
//     questionToAdd.answers[2].answerContent = newData.thirdAnswer;
//     questionToAdd.answers[3].answerContent = newData.fourthAnswer;

//     switch (newData.correctAnswer) {
//       case "1":
//         questionToAdd.answers[0].correct = true;
//         break;
//       case "2":
//         questionToAdd.answers[1].correct = true;
//         break;
//       case "3":
//         questionToAdd.answers[2].correct = true;
//         break;
//       case "4":
//         questionToAdd.answers[3].correct = true;
//         break;
//       default:
//         break;
//     }

//     dispatch(testNiveauPostQuestion(questionToAdd)).then((res) => {
//       resolve();
//       setState((prevState) => {
//         const data = [...prevState.data];
//         data.push(newData);
//         return { ...prevState, data };
//       });
//     });
//   };

//   const handleRowDelete = (oldData, resolve) => {
//     dispatch(testNiveauDeleteQuestion(oldData.id)).then((res) => {
//       resolve();
//       setState((prevState) => {
//         const data = [...prevState.data];
//         data.splice(data.indexOf(oldData), 1);
//         return { ...prevState, data };
//       });
//     });
//   };

//   const handleRowUpdate = (newData, oldData, resolve) => {
//     console.log(newData);

//     let questionToSend = {
//       id: oldData.id,
//       questionNbr: newData.questionNbr,
//       questionContent: newData.questionContent,
//       answers: [
//         { answer_id: "0", answerContent: newData.firstAnswer, correct: false },
//         { answer_id: "1", answerContent: newData.secondAnswer, correct: false },
//         { answer_id: "2", answerContent: newData.thirdAnswer, correct: false },
//         { answer_id: "3", answerContent: newData.fourthAnswer, correct: false },
//       ],
//       difficulty: "",
//     };

//     switch (newData.difficulty) {
//       case 1:
//         questionToSend.difficulty = "BASIC";
//         break;
//       case 2:
//         questionToSend.difficulty = "INTERMEDIATE";
//         break;
//       case 3:
//         questionToSend.difficulty = "HARD";
//         break;
//       case 4:
//         questionToSend.difficulty = "PROFESSIONAL";
//         break;
//       default:
//         break;
//     }

//     dispatch(testNiveauUpdateQuestion(oldData.id, questionToSend)).then(
//       (res) => {
//         resolve();
//         if (oldData) {
//           setState((prevState) => {
//             const data = [...prevState.data];
//             data[data.indexOf(oldData)] = newData;
//             return { ...prevState, data };
//           });
//         }
//       }
//     );
//   };

//   useEffect(() => {
//     dispatch(testNiveauGetQuestions()).then((res) => {
//       if (res) {
//         res.data.forEach((question) => {
//           let newQuestion = {
//             id: "",
//             questionNbr: "",
//             questionContent: "",
//             firstAnswer: "",
//             secondAnswer: "",
//             thirdAnswer: "",
//             fourthAnswer: "",
//             difficulty: "",
//           };

//           switch (question.difficulty) {
//             case "BASIC":
//               question.difficulty = 1;
//               break;
//             case "INTERMEDIATE":
//               question.difficulty = 2;
//               break;
//             case "HARD":
//               question.difficulty = 3;
//               break;
//             case "PROFESSIONAL":
//               question.difficulty = 4;
//               break;
//             default:
//               break;
//           }

//           newQuestion.id = question.id;
//           newQuestion.questionNbr = question.questionNbr;
//           newQuestion.questionContent = question.questionContent;
//           newQuestion.difficulty = question.difficulty;
//           newQuestion.firstAnswer = question.answers[0].answerContent;
//           newQuestion.secondAnswer = question.answers[1].answerContent;
//           newQuestion.thirdAnswer = question.answers[2].answerContent;
//           newQuestion.fourthAnswer = question.answers[3].answerContent;

//           setState((prevState) => {
//             const data = [...prevState.data];
//             data.push(newQuestion);
//             return { ...prevState, data };
//           });
//         });
//       }
//     });
//   }, [dispatch]);

//   return (
//     <MaterialTable
//       title="Questions"
//       columns={state.columns}
//       data={state.data}
//       icons={tableIcons}
//       editable={{
//         onRowAdd: (newData) =>
//           new Promise((resolve) => {
//             handleRowAdd(newData, resolve);
//           }),

//         onRowUpdate: (newData, oldData) =>
//           new Promise((resolve) => {
//             handleRowUpdate(newData, oldData, resolve);
//           }),
//         onRowDelete: (oldData) =>
//           new Promise((resolve) => {
//             handleRowDelete(oldData, resolve);
//           }),
//       }}
//     />


//   );
// }


/////////////////////////////////////////////////////


import React from 'react';
import PropTypes from 'prop-types';
import { makeStyles } from '@material-ui/core/styles';
import Box from '@material-ui/core/Box';
import Collapse from '@material-ui/core/Collapse';
import IconButton from '@material-ui/core/IconButton';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Typography from '@material-ui/core/Typography';
import Paper from '@material-ui/core/Paper';
import KeyboardArrowDownIcon from '@material-ui/icons/KeyboardArrowDown';
import KeyboardArrowUpIcon from '@material-ui/icons/KeyboardArrowUp';

const useRowStyles = makeStyles({
  root: {
    '& > *': {
      borderBottom: 'unset',
    },
  },
});

function createData(questionNbr, questionContent, difficulty, correctAnswer, answers) {
  return {
    questionNbr,
    questionContent,
    difficulty,
    correctAnswer,
    answers: [
      { answer_id: '0', answerContent: '11091700', correct: false },
      { answer_id: '1', answerContent: '11091700', correct: false },
      { answer_id: '2', answerContent: '11091700', correct: false },
      { answer_id: '3', answerContent: '11091700', correct: false },
    ],
  };
}

function Row(props) {
  const { row } = props;
  const [open, setOpen] = React.useState(false);
  const classes = useRowStyles();

  return (
    <React.Fragment>
      <TableRow className={classes.root}>
        <TableCell>
          <IconButton aria-label="expand row" size="small" onClick={() => setOpen(!open)}>
            {open ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
          </IconButton>
        </TableCell>
        <TableCell component="th" scope="row">
          {row.questionNbr}
        </TableCell>
        <TableCell align="right">{row.questionContent}</TableCell>
        <TableCell align="right">{row.difficulty}</TableCell>
        <TableCell align="right">{row.correctAnswer}</TableCell>
      </TableRow>
      <TableRow>
        <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={6}>
          <Collapse in={open} timeout="auto" unmountOnExit>
            <Box margin={1}>
              <Typography variant="h6" gutterBottom component="div">
                Answers
              </Typography>
              <Table size="small" aria-label="purchases">
                <TableHead>
                  <TableRow>
                    <TableCell>answerContent</TableCell>
                    <TableCell align="right">correct</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {row.answers.map((historyRow) => (
                    <TableRow key={historyRow.answer_id}>
                      <TableCell component="th" scope="row">
                        {historyRow.answerContent}
                      </TableCell>
                      <TableCell>{historyRow.correct}</TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </Box>
          </Collapse>
        </TableCell>
      </TableRow>
    </React.Fragment>
  );
}

Row.propTypes = {
  row: PropTypes.shape({
    questionNbr: PropTypes.number.isRequired,
    questionContent: PropTypes.string.isRequired,
    difficulty: PropTypes.string.isRequired,
    answers: PropTypes.arrayOf(
      PropTypes.shape({
        answer_id: PropTypes.string.isRequired,
        answerContent: PropTypes.string.isRequired,
        correct: PropTypes.bool.isRequired,
      }),
    ).isRequired,
    correctAnswer: PropTypes.string.isRequired,
  }).isRequired,
};

const rows = [
  createData(1, "First Question", "Basic", "First Answer", 4.0, 3.99),
];

export default function CollapsibleTable() {
  return (
    <TableContainer component={Paper}>
      <Table aria-label="collapsible table">
        <TableHead>
          <TableRow>
            <TableCell />
            <TableCell>Question number</TableCell>
            <TableCell align="right">Question content</TableCell>
            <TableCell align="right">Difficulty</TableCell>
            <TableCell align="right">Correct answer</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {rows.map((row) => (
            <Row key={row.name} row={row} />
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}
