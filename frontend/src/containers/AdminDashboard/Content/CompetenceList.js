import React, { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import {
  competenceDeleteData,
  competenceGetAll,
  competencePostData,
  competenceUpdateData,
} from "../../../actions/CompetenceActions";
import MaterialTable from "material-table";
import PropTypes from "prop-types";
import { withStyles } from "@material-ui/core/styles";
import { stylesContent } from "../styles/Styles";
import { tableIcons } from "../../tableFeatures/tableIcons";
import NavPills from "../../../components/NavPills/NavPills.js";
import Dashboard from "@material-ui/icons/Dashboard";
import Schedule from "@material-ui/icons/Schedule";
import Warning from "@material-ui/icons/Warning";
import Check from "@material-ui/icons/Check";
import CircularProgress from "@material-ui/core/CircularProgress";
import SnackbarContent from "../../../components/Snackbar/SnackbarContent.js";

function CompetenceList() {
  const dispatch = useDispatch();
  const competenceData = useSelector((state) => state.competence);
  const [alert, setAlert] = useState(null);
  const [alertAdd, setAlertAdd] = useState(null);
  const [alertUpdate, setAlertUpdate] = useState(null);
  const [alertDelete, setAlertDelete] = useState(null);
  const [state, setState] = useState({
    columns: [
      { title: "Id", field: "id" },
      { title: "Id Candidat", field: "idCandidat" },
      { title: "list", field: "list.join()" },
    ],
    data: [],
  });
  useEffect(() => {
    dispatch(competenceGetAll()).then((res) => {
      if (res) {
        setState({
          columns: state.columns,
          data: res.data,
        });
        setAlert(null);
      } else
        setAlert(
          <SnackbarContent
            message={
              <span>
                <b>WARNING ALERT:</b> Could not reach data... Refresh Page...
              </span>
            }
            close
            color="warning"
            icon={Warning}
          />
        );
    });
  }, [dispatch, state.columns]);
  const addCompetence = (newData) => {
    setAlert(null);
    setAlertAdd(null);
    setAlertUpdate(null);
    setAlertDelete(null);
    if (!newData.list[0]) {
      setAlertAdd(
        <SnackbarContent
          message={
            <span>
              <b>WARNING ALERT:</b> Empty field(s)...
            </span>
          }
          close
          color="warning"
          icon={Warning}
        />
      );
    } else {
      let list = [newData.list[0]];
      dispatch(
        competencePostData({
          list,
        })
      ).then((res) => {
        if (!res)
          setAlertAdd(
            <SnackbarContent
              message={
                <span>
                  <b>WARNING ALERT:</b> Bad Request...
                </span>
              }
              close
              color="warning"
              icon={Warning}
            />
          );
        else
          setAlertAdd(
            <SnackbarContent
              message={
                <span>
                  <b>SUCCESS ALERT:</b> Competence Added...
                </span>
              }
              close
              color="success"
              icon={Check}
            />
          );
      });
    }
  };
  const updateCompetence = (newData, oldData) => {
    setAlert(null);
    setAlertAdd(null);
    setAlertUpdate(null);
    setAlertDelete(null);
    console.log(newData, oldData);
    if (!newData.list[0]) {
      setAlertUpdate(
        <SnackbarContent
          message={
            <span>
              <b>WARNING ALERT:</b> Empty field(s)...
            </span>
          }
          close
          color="warning"
          icon={Warning}
        />
      );
    } else {
      let id = oldData.id;
      let list = [newData.list[0]];
      dispatch(
        competenceUpdateData({
          id,
          list,
        })
      ).then((res) => {
        if (!res)
          setAlertUpdate(
            <SnackbarContent
              message={
                <span>
                  <b>WARNING ALERT:</b> Bad Request...
                </span>
              }
              close
              color="warning"
              icon={Warning}
            />
          );
        else
          setAlertUpdate(
            <SnackbarContent
              message={
                <span>
                  <b>SUCCESS ALERT:</b> Competence Updated...
                </span>
              }
              close
              color="success"
              icon={Check}
            />
          );
      });
    }
  };
  const deleteCompetence = (oldData) => {
    dispatch(competenceDeleteData(oldData.id)).then((res) => {
      setAlert(null);
      setAlertAdd(null);
      setAlertUpdate(null);
      setAlertDelete(null);
      if (res)
        setAlertDelete(
          <SnackbarContent
            message={
              <span>
                <b>SUCCESS ALERT:</b> Competence Deleted...
              </span>
            }
            close
            color="success"
            icon={Check}
          />
        );
      else
        setAlertDelete(
          <SnackbarContent
            message={
              <span>
                <b>WARNING ALERT:</b> Server ERROR...
              </span>
            }
            close
            color="warning"
            icon={Warning}
          />
        );
    });
  };
  if (competenceData.loading) return <CircularProgress />;
  else
    return (
      <>
        {alert}
        {alertAdd}
        {alertUpdate}
        {alertDelete}
        <NavPills
          color="adminDashboard"
          tabs={[
            {
              tabButton: "Competences List",
              tabIcon: Dashboard,
              tabContent: (
                <>
                  <MaterialTable
                    title="Editable Example"
                    columns={state.columns}
                    icons={tableIcons}
                    data={state.data}
                    editable={{
                      onRowAdd: (newData) =>
                        new Promise((resolve) => {
                          resolve();
                          addCompetence(newData);
                        }),
                      onRowUpdate: (newData, oldData) =>
                        new Promise((resolve) => {
                          resolve();
                          updateCompetence(newData, oldData);
                        }),
                      onRowDelete: (oldData) =>
                        new Promise((resolve) => {
                          resolve();
                          deleteCompetence(oldData);
                        }),
                    }}
                  />
                </>
              ),
            },
            {
              tabButton: "Competences Settings",
              tabIcon: Schedule,
              tabContent: (
                <>
                  <h1>Empty</h1>
                </>
              ),
            },
          ]}
        />
      </>
    );
}

CompetenceList.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(stylesContent)(CompetenceList);
