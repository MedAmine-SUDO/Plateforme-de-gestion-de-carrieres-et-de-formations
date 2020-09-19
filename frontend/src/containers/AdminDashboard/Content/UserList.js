import React, { useState, useEffect, useRef } from "react";
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
import CircularProgress from "@material-ui/core/CircularProgress";

function UserList(props) {
  const dispatch = useDispatch();
  const authDetail = useSelector((state) => state.auth);
  const [state, setState] = useState({
    columns: [
      { title: "ID", field: "id" },
      { title: "Username", field: "username" },
      // { title: "Role", field: "roles" },
      { title: "Email", field: "email" },
      { title: "Password", field: "password" },
    ],
    data: [],
  });
  useEffect(() => {
    dispatch(getAllUsers()).then((res) => {
      if(res)
      setState(
        {
          columns:state.columns,
          data:res.data
        }
      );
    });
  }, []);
  if (authDetail.loading) return <CircularProgress />;
  else
    return (
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
    );
}

UserList.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(stylesContent)(UserList);
