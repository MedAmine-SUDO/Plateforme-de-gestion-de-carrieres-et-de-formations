// import React, { useEffect, useState } from "react";
// import ReactPlayer from 'react-player';
// import classNames from "classnames";
// import { useDispatch ,useSelector } from "react-redux";
// import { makeStyles } from "@material-ui/core/styles";

// import styles from "../../../assets/jss/material-kit-react/views/defaultPage.js";
// import GridContainer from "../../../components/Grid/GridContainer.js";
// import GridItem from "../../../components/Grid/GridItem.js";
// import Footer from "../../../components/Footer/Footer.js";

// import {
//   ressourceGetData,imageGetData,videoGetData,fileGetData,videoStreamGetData
// } from "../../../actions/RessourceActions.js";
// const useStyles = makeStyles(styles);
// function RessourceDetail(props){
//   const classes = useStyles();
//   const dispatch = useDispatch();
//   const ressourceData = useSelector((state) => state.ressourceData);
  
//   const [alert, setAlert] = useState(null);
//   const [description, setDescription] = useState("");
//   const [title,setTitle] = useState("");
//   const [file,setFile] = useState("");
//   const [image,setImage] = useState("");
//   const [imagecontent,setimagecontent]=useState("");
//   const [videocontent,setvideocontent]=useState("");
//   const [filecontent,setfilecontent]=useState("");
//   const [videocontentStream,setvideocontentStream]=useState("");


//   const [ video,setVideo] = useState("");
//   const [state, setstate] = useState(null);
//   const [cpt, setCpt] = useState(0);
//   useEffect(() => {

//     dispatch(ressourceGetData(props.match.params.id)).then((res) => {    
    
//       setVideo(res.data.idVideo[0]);
//       setFile(res.data.idFile[0]);
//       setTitle(res.data.title);
//       setDescription(res.data.description);
//      // console.log(ressourceData.data.image.match.params.idFile);
//      setImage(res.data.idPhoto[0])

//     })

//   }, [ressourceData,dispatch])
//   useEffect(() => {
//     if(image)
//     dispatch(imageGetData(image)).then ((imagecontent)=>{
//       setimagecontent(imagecontent.data.image.data);
//     });
//   }, [image])
  
//   useEffect(() => {
//     if(file)
//     dispatch(fileGetData(file)).then ((filecontent)=>{

//      setfilecontent(filecontent.data.file.data);
//     });
//   }, [file])
//   useEffect(() => {
//     if(video)
//     dispatch(videoGetData(video)).then ((videocontent)=>{

//       console.log(videocontent.data);

//     setvideocontent(videocontent.data);
//     });
//   }, [video])
//   useEffect(() => {
//     if(videocontent)
//     dispatch(videoStreamGetData(video)).then ((videocontentStream)=>{
//       console.log("data");

//       console.log(videocontentStream.data);

//     setvideocontentStream(videocontentStream.data);
//     });
//   }, [videocontent])
//   const handleclick = () =>{
//     setCpt(cpt+1)
//  }
//  //const classes = useStyles();
//   const imageClasses = classNames(
//     classes.imgRaised,
//     classes.imgRoundedCircle,
//     classes.imgFluid
//   );
//   // const imageData = async (e) => {
   
    
//   //   dispatch(imageGetData(image)).then((res) => console.log(res),
//   //   setimagecontent(res.image));
//   // };
//   const navImageClasses = classNames(classes.imgRounded, classes.imgGallery);
//   return (
//     <div>
 
       
  
    
//     <div>
    
//       <div className={classNames(classes.main, classes.mainRaised)}>
//       <meta http-equiv="content-type" content="text/html; charset=utf-8" />

//         <div>
//           <div className={classes.container}>
//             <GridContainer justify="center">
//             <GridItem xs={12} sm={12} md={6}>
//                 <div className={classes.profile} style={{content:"center", flex:"auto"}}>
//                     <h2>Ressource Details</h2>

//                     <strong>title:</strong><strong>{title}</strong><br />
//                     <strong>description :</strong><strong>{description}</strong><br /><br /><br />
//                     <div>
//                     <div>
//                     <br />
//                       <img  style={{ width:"500px"}} src= {`data:image/jpeg;base64,${imagecontent}`}></img>
//                       <br />

//                       <a href={`data:application/*;base64,${imagecontent}`} download image> Download Here </a>
//                       <br />
//                       <br />

//                       </div>
//                       <div>

//                       <img style={{ width:"500px"}}  src= {`data:application/*;base64,${filecontent}`}></img>
//                       <br />
//                       <br />

//                       <a href={filecontent} download file> Download Here </a>
                      
//                       <br />
//                       </div>
//                        <ReactPlayer url=
//                       {[ { src:{videocontentStream}, type: 'video/mp4'}]}     
//                                        ></ReactPlayer> 
                                       
//                       <ReactPlayer url='https://youtube.com/watch?v=2RGu_tLeqk4' style={{marginLeft: "10px"}} ></ReactPlayer>
//                       <br />
//                       <br />


//                     {state}
//                     </div>
//                 </div>
//               </GridItem>
              
//             </GridContainer>
//           </div>
//         </div>
//       </div>
//       <Footer />
//     </div>
// </div>
//   );
// }

// export default RessourceDetail;
