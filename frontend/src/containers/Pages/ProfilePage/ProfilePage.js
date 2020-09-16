import React, { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
// nodejs library that concatenates classes
import classNames from "classnames";
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
// @material-ui/icons
import Camera from "@material-ui/icons/Camera";
import Palette from "@material-ui/icons/Palette";
import Favorite from "@material-ui/icons/Favorite";
import FacebookIcon from "@material-ui/icons/Facebook";
import LinkedInIcon from "@material-ui/icons/LinkedIn";
import InstagramIcon from "@material-ui/icons/Instagram";
// core components
import Footer from "../../../components/Footer/Footer.js";
import Button from "../../../components/CustomButtons/Button.js";
import GridContainer from "../../../components/Grid/GridContainer.js";
import GridItem from "../../../components/Grid/GridItem.js";
import NavPills from "../../../components/NavPills/NavPills.js";
import Parallax from "../../../components/Parallax/Parallax.js";

import profile from "../../../assets/img/faces/christian.jpg";

import studio1 from "../../../assets/img/examples/studio-1.jpg";
import studio2 from "../../../assets/img/examples/studio-2.jpg";
import studio3 from "../../../assets/img/examples/studio-3.jpg";
import studio4 from "../../../assets/img/examples/studio-4.jpg";
import studio5 from "../../../assets/img/examples/studio-5.jpg";
import work1 from "../../../assets/img/examples/olu-eletu.jpg";
import work2 from "../../../assets/img/examples/clem-onojeghuo.jpg";
import work3 from "../../../assets/img/examples/cynthia-del-rio.jpg";
import work4 from "../../../assets/img/examples/mariya-georgieva.jpg";
import work5 from "../../../assets/img/examples/clem-onojegaw.jpg";

import styles from "../../../assets/jss/material-kit-react/views/profilePage.js";
import { profileGetData } from "../../../actions/ProfileActions";
import CircularProgress from "@material-ui/core/CircularProgress";

const useStyles = makeStyles(styles);

export default function ProfilePage() {
  const profileData = useSelector((state) => state.profile);
  const authDetail = useSelector((state) => state.auth);

  const dispatch = useDispatch();
  const [firstName, setfirstName] = useState("");
  const [lastName, setlastName] = useState("");
  const [birthDate, setBirthDate] = useState(new Date("2000-01-01").getDate().toString());
  const [telephone, settelephone] = useState("");
  const [address, setaddress] = useState("");
  const [postCode, setpostCode] = useState("");
  const [country, setcountry] = useState("");
  const [region, setregion] = useState("");

  useEffect(() => {
    if (authDetail.user && !profileData.data) {
      dispatch(profileGetData(authDetail.user.id));
    }
    if (profileData.data) {
      setfirstName(profileData.data.firstName);
      setlastName(profileData.data.lastName);
      setBirthDate(new Date(profileData.data.birthDate).toDateString());
      settelephone(profileData.data.telephone);
      setaddress(profileData.data.address);
      setpostCode(profileData.data.postCode);
      setcountry(profileData.data.country);
      setregion(profileData.data.region);
    }
  }, [profileData.data, authDetail.user,dispatch]);
  const classes = useStyles();
  const imageClasses = classNames(
    classes.imgRaised,
    classes.imgRoundedCircle,
    classes.imgFluid
  );
  const navImageClasses = classNames(classes.imgRounded, classes.imgGallery);
  return (
    <div>
      <Parallax
        small
        filter
        image={require("../../../assets/img/profile-bg.jpg")}
      />
      <div className={classNames(classes.main, classes.mainRaised)}>
        <div>
          <div className={classes.container}>
            <GridContainer justify="center">
              <GridItem xs={12} sm={12} md={6}>
                <div className={classes.profile}>
                  <div>
                    <img src={profile} alt="..." className={imageClasses} />
                  </div>
                  <div className={classes.name}>
                    {profileData.data ? (
                      <>
                        <h3 className={classes.title}>
                          {firstName + " " + lastName}
                        </h3><br />
                        Date of Birth :<strong>{birthDate}</strong><br />
                        Telephone :<strong>{telephone}</strong><br />
                        Address :<strong>{address}</strong><br />
                        Post Code :<strong>{postCode}</strong><br />
                        Country :<strong>{country}</strong><br />
                        Region :<strong>{region}</strong><br />
                        <Button justIcon link className={classes.margin5}>
                          <InstagramIcon color="primary" />
                        </Button>
                        <Button justIcon link className={classes.margin5}>
                          <LinkedInIcon color="primary" />
                        </Button>
                        <Button justIcon link className={classes.margin5}>
                          <FacebookIcon color="primary" />
                        </Button>
                      </>
                    ) : (profileData.loading ?
                      <CircularProgress /> : 
                      "Go to settings"
                    )}
                  </div>
                </div>
              </GridItem>
            </GridContainer>
            <div className={classes.description}>
              <p>
                An artist of considerable range, Chet Faker — the name taken by
                Melbourne-raised, Brooklyn-based Nick Murphy — writes, performs
                and records all of his own music, giving it a warm, intimate
                feel with a solid groove structure.{" "}
              </p>
            </div>
            <GridContainer justify="center">
              <GridItem xs={12} sm={12} md={8} className={classes.navWrapper}>
                <NavPills
                  alignCenter
                  color="primary"
                  tabs={[
                    {
                      tabButton: "Studio",
                      tabIcon: Camera,
                      tabContent: (
                        <GridContainer justify="center">
                          <GridItem xs={12} sm={12} md={4}>
                            <img
                              alt="..."
                              src={studio1}
                              className={navImageClasses}
                            />
                            <img
                              alt="..."
                              src={studio2}
                              className={navImageClasses}
                            />
                          </GridItem>
                          <GridItem xs={12} sm={12} md={4}>
                            <img
                              alt="..."
                              src={studio5}
                              className={navImageClasses}
                            />
                            <img
                              alt="..."
                              src={studio4}
                              className={navImageClasses}
                            />
                          </GridItem>
                        </GridContainer>
                      ),
                    },
                    {
                      tabButton: "Work",
                      tabIcon: Palette,
                      tabContent: (
                        <GridContainer justify="center">
                          <GridItem xs={12} sm={12} md={4}>
                            <img
                              alt="..."
                              src={work1}
                              className={navImageClasses}
                            />
                            <img
                              alt="..."
                              src={work2}
                              className={navImageClasses}
                            />
                            <img
                              alt="..."
                              src={work3}
                              className={navImageClasses}
                            />
                          </GridItem>
                          <GridItem xs={12} sm={12} md={4}>
                            <img
                              alt="..."
                              src={work4}
                              className={navImageClasses}
                            />
                            <img
                              alt="..."
                              src={work5}
                              className={navImageClasses}
                            />
                          </GridItem>
                        </GridContainer>
                      ),
                    },
                    {
                      tabButton: "Favorite",
                      tabIcon: Favorite,
                      tabContent: (
                        <GridContainer justify="center">
                          <GridItem xs={12} sm={12} md={4}>
                            <img
                              alt="..."
                              src={work4}
                              className={navImageClasses}
                            />
                            <img
                              alt="..."
                              src={studio3}
                              className={navImageClasses}
                            />
                          </GridItem>
                          <GridItem xs={12} sm={12} md={4}>
                            <img
                              alt="..."
                              src={work2}
                              className={navImageClasses}
                            />
                            <img
                              alt="..."
                              src={work1}
                              className={navImageClasses}
                            />
                            <img
                              alt="..."
                              src={studio1}
                              className={navImageClasses}
                            />
                          </GridItem>
                        </GridContainer>
                      ),
                    },
                  ]}
                />
              </GridItem>
            </GridContainer>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
}
