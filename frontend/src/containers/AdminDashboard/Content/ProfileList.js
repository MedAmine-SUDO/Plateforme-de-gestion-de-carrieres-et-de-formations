import React, { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import {
  profileDeleteData,
  profileGetAll,
  profilePostData,
  profileUpdateData,
} from "../../../actions/ProfileActions";
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

function ProfileList() {
  const dispatch = useDispatch();
  const profileData = useSelector((state) => state.profile);
  const [alert, setAlert] = useState(null);
  const [alertAdd, setAlertAdd] = useState(null);
  const [alertUpdate, setAlertUpdate] = useState(null);
  const [alertDelete, setAlertDelete] = useState(null);
  const [state, setState] = useState({
    columns: [
      { title: "Id", field: "id" },
      { title: "UserID", field: "userID" },
      { title: "First Name", field: "firstName" },
      { title: "Last Name", field: "lastName" },
      { title: "Birth Date", field: "birthDate", type: "date" },
      { title: "Address", field: "address" },
      { title: "Post Code", field: "postCode", type: "numeric" },
      { title: "Region", field: "region" },
      { title: "Telephone", field: "telephone", type: "numeric" },
      { title: "Country", field: "country" },
    ],
    data: [],
  });
  useEffect(() => {
    dispatch(profileGetAll()).then((res) => {
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
  const addProfile = (newData) => {
    setAlert(null);
    setAlertAdd(null);
    setAlertUpdate(null);
    setAlertDelete(null);

    if (
      !newData.userID ||
      !newData.firstName ||
      !newData.lastName ||
      !newData.birthDate ||
      !newData.address ||
      !newData.postCode ||
      !newData.region ||
      !newData.telephone ||
      !newData.country
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
      let userID = newData.userID;
      let firstName = newData.firstName;
      let lastName = newData.lastName;
      let birthDate = newData.birthDate;
      let address = newData.address;
      let postCode = newData.postCode;
      let region = newData.region;
      let telephone = newData.telephone;
      let country = newData.country;
      dispatch(
        profilePostData({
          userID,
          firstName,
          lastName,
          birthDate,
          address,
          postCode,
          region,
          telephone,
          country,
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
                  <b>SUCCESS ALERT:</b> Profile Added...
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
  const updateProfile = (newData, oldData) => {
    setAlert(null);
    setAlertAdd(null);
    setAlertUpdate(null);
    setAlertDelete(null);
    if (
      !newData.userID ||
      !newData.firstName ||
      !newData.lastName ||
      !newData.birthDate ||
      !newData.address ||
      !newData.postCode ||
      !newData.region ||
      !newData.telephone ||
      !newData.country
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
      let userID = newData.userID;
      let firstName = newData.firstName;
      let lastName = newData.lastName;
      let birthDate = newData.birthDate;
      let address = newData.address;
      let postCode = newData.postCode;
      let region = newData.region;
      let telephone = newData.telephone;
      let country = newData.country;
      dispatch(
        profileUpdateData({
          id,
          userID,
          firstName,
          lastName,
          birthDate,
          address,
          postCode,
          region,
          telephone,
          country,
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
                  <b>SUCCESS ALERT:</b> Profile Updated...
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
  const deleteProfile = (oldData) => {
    dispatch(profileDeleteData(oldData.id)).then((res) => {
      setAlert(null);
      setAlertAdd(null);
      setAlertUpdate(null);
      setAlertDelete(null);
      if (res)
        setAlertDelete(
          <SnackbarContent
            message={
              <span>
                <b>SUCCESS ALERT:</b> Profile Deleted...
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
  if (profileData.loading) return <CircularProgress />;
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
              tabButton: "Profiles List",
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
                          addProfile(newData);
                        }),
                      onRowUpdate: (newData, oldData) =>
                        new Promise((resolve) => {
                          resolve();
                          updateProfile(newData, oldData);
                        }),
                      onRowDelete: (oldData) =>
                        new Promise((resolve) => {
                          resolve();
                          deleteProfile(oldData);
                        }),
                    }}
                  />
                </>
              ),
            },
            {
              tabButton: "Profiles Settings",
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

ProfileList.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(stylesContent)(ProfileList);
