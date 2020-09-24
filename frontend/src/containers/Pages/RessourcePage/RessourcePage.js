import React, { useState,useEffect } from "react";
import {useDispatch } from "react-redux";
import classNames from "classnames";
import { makeStyles } from "@material-ui/core/styles";
import styles from "../../../assets/jss/material-kit-react/views/accountPage.js";
import GridContainer from "../../../components/Grid/GridContainer.js";
import GridItem from "../../../components/Grid/GridItem.js";
import Footer from "../../../components/Footer/Footer.js";
import NavPills from "../../../components/NavPills/NavPills.js";
import Warning from "@material-ui/icons/Warning";
import SnackbarContent from "../../../components/Snackbar/SnackbarContent.js";
//import Dashboard from "@material-ui/icons/Dashboard";
//import Schedule from "@material-ui/icons/Schedule";
//import DeleteForeverTwoToneIcon from '@material-ui/icons/DeleteForeverTwoTone';
//import EditTwoToneIcon from '@material-ui/icons/EditTwoTone';
//import FormControl from "@material-ui/core/FormControl";
import Button from "../../../components/CustomButtons/Button.js";
//import DateTimePicker from "react-datetime-picker";
import Input from "@material-ui/core/Input";
import MaterialTable from "material-table";
//import axios from 'axios';
import PlaylistAddIcon from "@material-ui/icons/PlaylistAdd";
import PlaylistPlayIcon from "@material-ui/icons/PlaylistPlay";
import { ressourcePostData, ressourceGetAll } from "../../../actions/RessourceActions.js";
const useStyles = makeStyles(styles);

function RessourcePage() {
  const classes = useStyles();
  const dispatch = useDispatch();
  const [alert, setAlert] = useState(null);
  const [description, setDescription] = useState("");
  const [title, setTitle] = useState("");
  const [file, setFile] = useState("");
  const [image, setImage] = useState("");
  const [video, setVideo] = useState("");

  const handleChange = (e, name) => {
    const ressource = {};
    ressource[name] = e.target.value;
    // validations
    switch (name) {
      case "description":
        setDescription(ressource.description);
        break;
      case "title":
        setTitle(ressource.title);
        break;
        case "file":
        setFile(ressource.file);
        break;
        case "image":
        setImage(ressource.image);
        break;
        case "video":
        setVideo(ressource.video);
        break;
      default:
        break;
    }
  };
  const handleRessource = async (e) => {
    e.preventDefault();
      dispatch(ressourcePostData({description , title ,image , file, video}))
  };

  const [state, setState] = useState({
    columns: [
      { title: 'title', field: 'title' },
      { title: 'description', field: 'description' }
  
     
    ],
    data: [
     { title:"Has", description:"hfhgé"}
    ],
  });
  useEffect(() => {
    dispatch(ressourceGetAll()).then((res) => {
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
  return (
    <div>
      <div>
        <div className={classNames(classes.main, classes.mainRaised)}>
          <div>
            <div className={classes.container}>
              <GridContainer justify="center">
                <GridItem xs={12} sm={12}>
                  <NavPills
                    color="primary"
                    horizontal={{
                      tabsGrid: { xs: 12, sm: 4, md: 4 },
                      contentGrid: { xs: 12, sm: 8, md: 8 },
                    }}
                    tabs={[
                      {
                        tabButton: "My Ressources",
                        tabIcon: PlaylistPlayIcon,
                        tabContent: (
                          <>
                            <h1>My Ressources</h1>
                            <MaterialTable
                              title="My Ressources"
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
                          </>
                        ),
                      },
                      {
                        tabButton: "Add New Ressource",
                        tabIcon: PlaylistAddIcon,
                        tabContent: (
                          <>
                            <form onSubmit={handleRessource}>
                              <h1>Add New Ressource</h1>

                              <strong>Title :</strong>
                              <Input
                                id="title"
                                name="title"
                                inputProps={{
                                  placeholder: "Your title here",
                                }}
                                fullWidth
                                value={title}
                                onChange={(e) => handleChange(e, "title")}
                              />
                              <br />
                              <br />
                              <br />

                              <strong>Description :</strong>
                              <Input
                                id="description"
                                name="description"
                                inputProps={{
                                  placeholder: "your description here ",
                                }}
                                fullWidth
                                value={description}
                                onChange={(e) => handleChange(e, "description")}
                              />
                              <br />
                              <br />
                              <br />
                              <strong>Add file :</strong>
                              <Input
                                id="idfile"
                                name="file"
                                type="file"
                                inputProps={{
                                  placeholder: "File",
                                }}
                                onChange={(e) => handleChange(e, "file")}

                              />
                              <br />
                              <br />
                              <strong>Add Photo :</strong>
                              <Input
                                id="idPhoto"
                                name="image"
                                type="file"
                                inputProps={{
                                  placeholder: "Photo",
                                }}
                                onChange={(e) => handleChange(e, "image")}

                              />
                              <br />
                              <br />
                              <strong>Add Video:</strong>
                              <Input
                                id="idVideo"
                                name="video"
                                type="file"
                                inputProps={{
                                  placeholder: "Video",
                                }}
                                onChange={(e) => handleChange(e, "video")}

                              />

                              <br />
                              <br />
                              <Button color="primary" type="submit" round>
                                Save
                              </Button>
                              <Button round>Cancel</Button>
                              <br />
                              <br />
                            </form>
                          </>
                        ),
                      },
                    ]}
                  />
                </GridItem>
                {/* <GridItem xs={12} sm={12} md={6}>
                <div className={classes.profile}>
                  <div className={classes.name}>
                    <h2>write HERE</h2>
                  </div>
                </div>
              </GridItem> */}
              </GridContainer>
            </div>
          </div>
        </div>
        <Footer />
      </div>
      <div className={classNames(classes.main, classes.mainRaised)}></div>
      <Footer />
    </div>
  );
}

export default RessourcePage;
