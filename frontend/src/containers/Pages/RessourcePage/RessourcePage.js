import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import classNames from "classnames";
import { makeStyles } from "@material-ui/core/styles";
import styles from "../../../assets/jss/material-kit-react/views/accountPage.js";
import GridContainer from "../../../components/Grid/GridContainer.js";
import GridItem from "../../../components/Grid/GridItem.js";
import Footer from "../../../components/Footer/Footer.js";
import NavPills from "../../../components/NavPills/NavPills.js";
import Check from "@material-ui/icons/Check";


import CircularProgress from "@material-ui/core/CircularProgress";
import {
  notificationByUSer,
  notificationDeleteData,
notificationGetAll,
notificationPostData,
} from "../../../actions/NotificationActions";
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
import {
  ressourcePostData,
  ressourceGetAll,
  ressourceDeleteData,
} from "../../../actions/RessourceActions.js";
import { tableIcons } from "../../tableFeatures/tableIcons";
import { useHistory } from "react-router-dom";
import RessourceDetail from "./RessourceDetail.js";

const useStyles = makeStyles(styles);

function RessourcePage() {
  const [alertDelete, setAlertDelete] = useState(null);
  const ressourceData = useSelector((state) => state.ressource);
  //const [notif,setnotifData] = useState((state)=>state.notification);

  const history = useHistory();
  const classes = useStyles();
  const dispatch = useDispatch();
  const [alert, setAlert] = useState(null);
  const [description, setDescription] = useState("");
  const [title, setTitle] = useState("");
  const [setFile] = useState("");
  const [setImage] = useState("");
  const [setVideo] = useState("");
  const [ressource, setRessourceData] = useState(new FormData());

  const authDetail = useSelector((state) => state.auth);
  const [titleNot,setTitleNotif]= useState("");
  const [descriptionNot,setDescriptionNotif]= useState("");
  const [sender,setSender]= useState("");
  const [receiver,setReceiver]= useState("");

  const [type,setTypeNotif]= useState("Ressource Added");
  const [seen,setSeen]= useState("FALSE");
  const [date,setDate]=useState("");
  const notif=useState("");
  const handleChange = (e, name) => {
 
    const ressource = {};
    ressource[name] = e.target.value;
    // validations
    switch (name) {
      case "description":
        setDescription(ressource.description);
        setDescriptionNotif(notif.setDescriptionNotif);
        break;
      case "title":
        setTitle(ressource.title);
        setTitleNotif(notif.titleNot);
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
  const handleUploadClick3 = (event) => {
    let video = event.target.files[2];
    ressource.append("video", video);

    setRessourceData(ressource);
   
   
  };
  const handleUploadClick2 = (event) => {
    let image = event.target.files[1];
    ressource.append("image", image);
    setRessourceData(ressource);
  };
  const handleUploadClick1 = (event) => {
    let file = event.target.files[0];
    ressource.append("file", file);

    let image = event.target.files[0];
    ressource.append("image", image);

    let video = event.target.files[0];

    ressource.append("video", video);

    setRessourceData(ressource);

  };
  const handleRessource = async (e) => {
    e.preventDefault();
    ressource.append("title", title);
    ressource.append("description", description);
    dispatch(ressourcePostData(ressource)).then((res) => {
     
      console.log(res);
      
      let titleNot = notif.title;
      let descriptionNot = notif.description;
      let niveau = notif.niveau;
      let date = new Date();
      let type = notif.type;
      let body  = notif.body;
      let idReceiver = notif.receiver;
      let idSender = notif.sender;
      let seen = notif.seen;
      let object=notif.object;

	     dispatch(
        notificationPostData({
          titleNot,
          descriptionNot,
          idReceiver,
          idSender,
          date,
          object,
          seen,
          type,
          body
        })
      ).then((resn) => {
        console.log(resn)
      });

    });
    
  
  };

  const [state, setState] = useState({
    columns: [
      { title: "title", field: "title" },
      { title: "description", field: "description" },
    ],
    data: [],
  });
  const deleteRessource = (oldData) => {
    dispatch(ressourceDeleteData(oldData.id)).then((res) => {
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
  if (ressourceData.loading)
    return (
      <div className={classNames(classes.main, classes.mainRaised)}>
        <div>
          <div className={classes.container}>
            <CircularProgress />
          </div>
        </div>
      </div>
    );
  else
    return (
      <div>
        {alert}
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
                                icons={tableIcons}
                                onRowClick={(e, data) => {
                                  history.push("/uploadRessource/" + data.id);
                                }}
                                editable={{
                                  onRowDelete: (oldData) =>
                                    new Promise((resolve) => {
                                      setTimeout(() => {
                                        resolve();
                                        deleteRessource(oldData);

                                        // setState((prevState) => {
                                        //   const data = [...prevState.data];
                                        //   data.splice(data.indexOf(oldData), 1);
                                        //   return { ...prevState, data };
                                        // });
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
                                  onChange={(e) =>
                                    handleChange(e, "description")
                                  }
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
                                  onChange={handleUploadClick1}
                                />
                                <br />
                                <br />
                                <strong>Add Photo :</strong>
                                <input
                                  id="idPhoto"
                                  name="image"
                                  type="file"
                                  inputProps={{
                                    placeholder: "Photo",
                                  }}
                                  accept="image/*"
                                  onChange={handleUploadClick2}
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
                                  onChange={handleUploadClick3}
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
