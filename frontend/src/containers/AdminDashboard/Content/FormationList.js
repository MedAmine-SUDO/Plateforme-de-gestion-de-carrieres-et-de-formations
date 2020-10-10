import React, { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import {
  formationDeleteData,
  formationGetAll,
  formationPostData,
  formationUpdateData,
} from "../../../actions/FormationActions";
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

function FormationList() {
  const dispatch = useDispatch();
  const formationData = useSelector((state) => state.formation);
  const [alert, setAlert] = useState(null);
  const [alertAdd, setAlertAdd] = useState(null);
  const [alertUpdate, setAlertUpdate] = useState(null);
  const [alertDelete, setAlertDelete] = useState(null);
  const [state, setState] = useState({
    columns: [
      { title: "Id", field: "id" },
      { title: "Title", field: "title" },
      { title: "Description", field: "description" },
      //{ title: "idFormateurs", field: "idFormateurs[0]" },
      { title: "Niveau", field: "niveau" },
      //{ title: "Competence", field: "competence[0]" },
      { title: "Begin Date", field: "beginDate", type: "date" },
      { title: "End Date", field: "endDate", type: "date" },
      { title: "Hours", field: "nbrHours", type: "numeric" },
      { title: "Price", field: "price", type: "numeric" },
      {
        title: "Type",
        field: "type",
        lookup: { "a distance": "a distance", "presentiel": "presentiel" },
      },
    ],
    data: [],
  });
  useEffect(() => {
    dispatch(formationGetAll()).then((res) => {
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
  const addFormation = (newData) => {
    setAlert(null);
    setAlertAdd(null);
    setAlertUpdate(null);
    setAlertDelete(null);
    if (
      !newData.title ||
      !newData.description ||
      !newData.niveau ||
      !newData.beginDate ||
      !newData.endDate ||
      !newData.nbrHours ||
      !newData.type
    ) {
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
      let title = newData.title;
      let description = newData.description;
      let niveau = newData.niveau;
      let beginDate = newData.beginDate;
      let endDate = newData.endDate;
      let nbrHours = newData.nbrHours;
      let price = newData.price;
      let type = newData.type;
      dispatch(
        formationPostData({
          title,
          description,
          niveau,
          beginDate,
          endDate,
          nbrHours,
          price,
          type,
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
                  <b>SUCCESS ALERT:</b> Formation Added...
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
  const updateFormation = (newData, oldData) => {
    setAlert(null);
    setAlertAdd(null);
    setAlertUpdate(null);
    setAlertDelete(null);
    console.log(newData, oldData)
    if (
      !newData.title ||
      !newData.description ||
      !newData.niveau ||
      !newData.beginDate ||
      !newData.endDate ||
      !newData.nbrHours ||
      !newData.type
    ) {
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
      let title = newData.title;
      let description = newData.description;
      let niveau = newData.niveau;
      let beginDate = newData.beginDate;
      let endDate = newData.endDate;
      let nbrHours = newData.nbrHours;
      let price = newData.price;
      let type = newData.type;
      dispatch(
        formationUpdateData({id,
          title,
          description,
          niveau,
          beginDate,
          endDate,
          nbrHours,
          price,
          type,
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
                  <b>SUCCESS ALERT:</b> Formation Updated...
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
  const deleteFormation = (oldData) => {
    dispatch(formationDeleteData(oldData.id)).then((res) => {
      setAlert(null);
      setAlertAdd(null);
      setAlertUpdate(null);
      setAlertDelete(null);
      if (res)
        setAlertDelete(
          <SnackbarContent
            message={
              <span>
                <b>SUCCESS ALERT:</b> Formation Deleted...
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
  if (formationData.loading) return <CircularProgress />;
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
              tabButton: "Formations List",
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
                          addFormation(newData);
                        }),
                      onRowUpdate: (newData, oldData) =>
                        new Promise((resolve) => {
                          resolve();
                          updateFormation(newData, oldData);
                        }),
                      onRowDelete: (oldData) =>
                        new Promise((resolve) => {
                          resolve();
                          deleteFormation(oldData);
                        }),
                    }}
                  />
                </>
              ),
            },
            {
              tabButton: "Formations Settings",
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

FormationList.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(stylesContent)(FormationList);
