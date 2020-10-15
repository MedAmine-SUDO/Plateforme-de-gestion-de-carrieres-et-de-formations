import React, { useEffect, useState } from "react";
import classNames from "classnames";
import { useDispatch ,useSelector } from "react-redux";
import { makeStyles } from "@material-ui/core/styles";
import Parallax from "../../../components/Parallax/Parallax.js";

import styles from "../../../assets/jss/material-kit-react/views/defaultPage.js";
import GridContainer from "../../../components/Grid/GridContainer.js";
import GridItem from "../../../components/Grid/GridItem.js";
import Footer from "../../../components/Footer/Footer.js";
import {
  ressourceGetData,imageGetData
} from "../../../actions/RessourceActions.js";
const useStyles = makeStyles(styles);
function RessourceDetail(props){
  const classes = useStyles();
  const dispatch = useDispatch();
  const ressourceData = useSelector((state) => state.ressourceData);
  
  const [alert, setAlert] = useState(null);
  const [description, setDescription] = useState("");
  const [title, setTitle] = useState("");
  const [file, setFile] = useState("");
  const [image,setImage] = useState("");
  const [imagecontent,setimagecontent]=useState("");
  const [ video,setVideo] = useState("");
  const [state, setstate] = useState(null);
  const [cpt, setCpt] = useState(0);
  useEffect(() => {

    dispatch(ressourceGetData(props.match.params.id)).then((res) => {
      console.log(res.data);
      console.log('hi');
    
    
      // setVideo(ressourceData.data.video);
      // setFile(ressourceData.file);
      setTitle(res.data.title);
      setDescription(res.data.description);
     // console.log(ressourceData.data.image.match.params.idFile);
     setImage(res.data.idPhoto[0])

    })

  }, [ressourceData,dispatch])
  useEffect(() => {
    if(image)
    dispatch(imageGetData(image)).then ((imagecontent)=>{
      setimagecontent(imagecontent.data.image.data);
    });
  }, [image])
  const handleclick = () =>{
    setCpt(cpt+1)
 }
 //const classes = useStyles();
  const imageClasses = classNames(
    classes.imgRaised,
    classes.imgRoundedCircle,
    classes.imgFluid
  );
  // const imageData = async (e) => {
   
    
  //   dispatch(imageGetData(image)).then((res) => console.log(res),
  //   setimagecontent(res.image));
  // };
  const navImageClasses = classNames(classes.imgRounded, classes.imgGallery);
  return (
    <div>
 
       
  
    
    <div>
    
      <div className={classNames(classes.main, classes.mainRaised)}>
        <div>
          <div className={classes.container}>
            <GridContainer justify="center">
            <GridItem xs={12} sm={12} md={6}>
                <div className={classes.profile}>
                    <h2>{props.match.params.id}</h2>
                    <strong>title:</strong><strong>{title}</strong><br />
                    <strong>description :</strong><strong>{description}</strong><br />
                    <div style={{marginTop: "100px", flex:"auto"}}>
                      <img  style={{marginLeft: "10px"}} src= {`data:image/jpeg;base64,${imagecontent}`}></img>
                      <img style={{marginLeft: "10px"}}  src= {`data:image/jpeg;base64,${imagecontent}`}></img>
                      <img style={{marginLeft: "10px"}}  src= {`data:image/jpeg;base64,${imagecontent}`}></img>
                    </div>
                    <button onClick={handleclick}>click</button>
                    {state}
                </div>
              </GridItem>
              
            </GridContainer>
          </div>
        </div>
      </div>
      <Footer />
    </div>
</div>
  );
}

export default RessourceDetail;
