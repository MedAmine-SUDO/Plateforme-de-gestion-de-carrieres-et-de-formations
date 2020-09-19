import React, { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { getAllUsers } from "../../../actions/AuthActions";
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
import { signUp } from "../../../actions/AuthActions";

function UserList(props) {
  const dispatch = useDispatch();
  const authDetail = useSelector((state) => state.auth);
  const [alert, setAlert] = useState(null);
  const [empty, setEmpty] = useState(null);
  const [roleErr, setRoleErr] = useState(null);
  const [addSuccess, setAddSuccess] = useState(null);
  const [badRequest, setBadRequest] = useState(null);

  const [state, setState] = useState({
    columns: [
      { title: "ID", field: "id" },
      { title: "Username", field: "username" },
      { title: "Role", field: "roles[0].name" },
      { title: "Email", field: "email" },
      { title: "Password", field: "password" },
    ],
    data: [],
  });
  useEffect(() => {
    dispatch(getAllUsers())
      .then((res) => {
        if (res)
          setState({
            columns: state.columns,
            data: res.data,
          });
        setAlert(null);
      })
      .catch((err) => {
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
  }, [dispatch, addSuccess , state.columns]);
  const addUser = (newData) => {
    setEmpty(null);
    setAddSuccess(null);
    setAlert(null);
    setRoleErr(null);
    setBadRequest(null);
    if (
      !newData.username ||
      !newData.email ||
      !newData.password ||
      !newData.roles
    ) {
      setEmpty(
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
    } else if (
      !["admin", "candidat", "mentor", "instructor"].includes(
        newData.roles[0].name
      )
    ) {
      setRoleErr(
        <SnackbarContent
          message={
            <span>
              <b>ERROR ALERT:</b> Unknown role
              (["admin","candidat","mentor","instructor"])...
            </span>
          }
          close
          color="warning"
          icon={Warning}
        />
      );
    } else {
      let username = newData.username;
      let email = newData.email;
      let password = newData.password;
      let roles = [newData.roles[0].name];
      dispatch(signUp({ username, email, password, roles }))
        .then((res) => {
          if (res.type === "SIGNUP_USER_FAILURE")
            setBadRequest(
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
          setAddSuccess(
            <SnackbarContent
              message={
                <span>
                  <b>SUCCESS ALERT:</b> User Added...
                </span>
              }
              close
              color="success"
              icon={Check}
            />
          );
        })
        .catch((err) => {
          setAlert(
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
    }
  };
  if (authDetail.loading) return <CircularProgress />;
  else
    return (
      <>
        {alert}
        {empty}
        {roleErr}
        {addSuccess}
        {badRequest}
        <NavPills
          color="adminDashboard"
          tabs={[
            {
              tabButton: "Users List",
              tabIcon: Dashboard,
              tabContent: (
                <>
                  <MaterialTable
                    title="Users List"
                    columns={state.columns}
                    data={state.data}
                    icons={tableIcons}
                    editable={{
                      onRowAdd: (newData) =>
                        new Promise((resolve) => {
                          resolve();
                          addUser(newData);
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
                </>
              ),
            },
            {
              tabButton: "Account Settings",
              tabIcon: Schedule,
              tabContent: (
                <>
                  <h1>blabla</h1>
                </>
              ),
            },
          ]}
        />
      </>
    );
}

UserList.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(stylesContent)(UserList);
