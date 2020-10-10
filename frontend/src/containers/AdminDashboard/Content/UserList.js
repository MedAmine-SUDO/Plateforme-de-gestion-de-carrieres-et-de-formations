import React, { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { getAllUsers, signUp, deleteUser } from "../../../actions/AuthActions";
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

function UserList() {
  const dispatch = useDispatch();
  const authDetail = useSelector((state) => state.auth);
  const [alert, setAlert] = useState(null);
  const [alertAdd, setalertAdd] = useState(null);
  const [alertDelete, setalertDelete] = useState(null);

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
    dispatch(getAllUsers()).then((res) => {
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
  const addUser = (newData) => {
    setAlert(null);
    setalertDelete(null)
    if (
      !newData.username ||
      !newData.email ||
      !newData.password ||
      !newData.roles
    ) {
      setalertAdd(
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
      setalertAdd(
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
          setalertAdd(
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
          setalertAdd(
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
          setalertAdd(
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
  const deleteOneUser = (oldData) => {
    dispatch(deleteUser(oldData.id)).then((res) => {
      setAlert(null);
      setalertAdd(null)
      if (res)
      setalertDelete(
          <SnackbarContent
            message={
              <span>
                <b>SUCCESS ALERT:</b> User Deleted...
              </span>
            }
            close
            color="success"
            icon={Check}
          />
        );
      else
      setalertDelete(
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
  if (authDetail.loading) return <CircularProgress />;
  else
    return (
      <>
        {alert}
        {alertAdd}
        {alertDelete}
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
                          resolve();
                          deleteOneUser(oldData);
                        }),
                    }}
                  />
                </>
              ),
            },
            {
              tabButton: "Users Settings",
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

UserList.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(stylesContent)(UserList);
