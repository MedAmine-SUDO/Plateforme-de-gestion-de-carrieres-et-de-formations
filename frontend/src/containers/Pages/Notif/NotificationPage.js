import React, { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import {
    notificationByUSer,
    notificationDeleteData,
  notificationGetAll,
  notificationPostData,
} from "../../../actions/NotificationActions";
import MaterialTable from "material-table";
import PropTypes from "prop-types";
import { withStyles } from "@material-ui/core/styles";
import { tableIcons } from "../../tableFeatures/tableIcons";
import NavPills from "../../../components/NavPills/NavPills.js";
import Dashboard from "@material-ui/icons/Dashboard";
import Schedule from "@material-ui/icons/Schedule";
import Warning from "@material-ui/icons/Warning";
import Check from "@material-ui/icons/Check";
import CircularProgress from "@material-ui/core/CircularProgress";
import SnackbarContent from "../../../components/Snackbar/SnackbarContent.js";

function NotificationPage() {
    const authDetail = useSelector((state) => state.auth);

  const dispatch = useDispatch();
  const notificationData = useSelector((state) => state.notification);
  const [alert, setAlert] = useState(null);

  const [alertDelete, setAlertDelete] = useState(null);
  const [state, setState] = useState({
    columns: [
      { title: "Id", field: "id" },
      { title: "Title", field: "title" },
      { title: "Description", field: "description" },
      //{ title: "idFormateurs", field: "idFormateurs[0]" },
      { title: "Receiver", field: "Receiver" },
      //{ title: "Competence", field: "competence[0]" },
      { title: "Sender", field: "Sender", type: "date" },
      { title: "Type", field: "Type"},
      { title: "Date", field: "Date" },
      { title: "Seen", field: "Seen"},
      
    ],
    data: [],
  });
  useEffect(() => {
    dispatch(notificationByUSer(authDetail.user.id)).then((res) => {
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
 
 
  const deleteNotification = (oldData) => {
    dispatch(notificationDeleteData(oldData.id)).then((res) => {
    
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
//   if (notificationData.lo) return <CircularProgress />;
//   else
    return (
      <>
        {alert}
       
        {alertDelete}
        <NavPills
          color="adminDashboard"
          tabs={[
            {
              tabButton: "Notification List",
              tabIcon: Dashboard,
              tabContent: (
                <>
                  <MaterialTable
                    title="Editable Example"
                    columns={state.columns}
                    icons={tableIcons}
                    data={state.data}
                    editable={{
                      onRowDelete: (oldData) =>
                        new Promise((resolve) => {
                          resolve();
                          deleteNotification(oldData);
                        }),
                    }}
                  />
                </>
              ),
            },
            
          ]}
        />
      </>
    );
}

// NotificationPage.propTypes = {
//   classes: PropTypes.object.isRequired,
// };

export default NotificationPage;
