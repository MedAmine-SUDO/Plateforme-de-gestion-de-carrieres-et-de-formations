import React, { useState, useEffect,useRef } from "react";
import { useSelector, useDispatch } from "react-redux";
import {
  profileGetData,
  profileUpdateData,
  profilePostData
} from "../../../actions/ProfileActions";
import classNames from "classnames";
import { makeStyles } from "@material-ui/core/styles";
import styles from "../../../assets/jss/material-kit-react/views/accountPage.js";
import GridContainer from "../../../components/Grid/GridContainer.js";
import GridItem from "../../../components/Grid/GridItem.js";
import Footer from "../../../components/Footer/Footer.js";
import NavPills from "../../../components/NavPills/NavPills.js";
import Dashboard from "@material-ui/icons/Dashboard";
import Schedule from "@material-ui/icons/Schedule";
import FormControl from "@material-ui/core/FormControl";
import Button from "../../../components/CustomButtons/Button.js";
import DateTimePicker from "react-datetime-picker";
import { CountryDropdown, RegionDropdown } from "react-country-region-selector";
import Input from "@material-ui/core/Input";
import CircularProgress from "@material-ui/core/CircularProgress";

const useStyles = makeStyles(styles);
function AccountPage() {
  const authDetail = useSelector((state) => state.auth);
  const profileData = useSelector((state) => state.profile);
  const dispatch = useDispatch();
  const classes = useStyles();

  const [id, setId] = useState("");
    const [userID, setUserID] = useState("");

  const [firstName, setfirstName] = useState("");
  const [lastName, setlastName] = useState("");
  const [birthDate, setBirthDate] = useState(new Date("2000-01-01"));
  const [telephone, settelephone] = useState("");
  const [address, setaddress] = useState("");
  const [postCode, setpostCode] = useState("");
  const [country, setcountry] = useState("");
  const [region, setregion] = useState("");

  const [username, setusername] = useState("");
  const [email, setemail] = useState("");
  const [password, setpassword] = useState("");
  const isFirstRun = useRef(true);
  useEffect(() => {
    if (authDetail.user) {
      setUserID(authDetail.user.id)
      setusername(authDetail.user.username);
      setemail(authDetail.user.email);
      if (isFirstRun.current) {
        isFirstRun.current = false;
        return;
      }
      dispatch(profileGetData(userID));
    }
  }, [authDetail,userID, dispatch]);
  useEffect(() => {

    if (profileData.data) {
      setId(profileData.data.id)
      setfirstName(profileData.data.firstName);
      setlastName(profileData.data.lastName);
      setBirthDate(new Date(profileData.data.birthDate));
      settelephone(profileData.data.telephone);
      setaddress(profileData.data.address);
      setpostCode(profileData.data.postCode);
      setcountry(profileData.data.country);
      setregion(profileData.data.region);
    }
  }, [profileData.data]);
  const handleChange = (e, name) => {
    const user = {};
    if (name === "country" || name === "region" || name === "birthDate")
      user[name] = e;
    else user[name] = e.target.value;
    // validations
    switch (name) {
      case "firstName":
        setfirstName(user.firstName);
        break;
      case "lastName":
        setlastName(user.lastName);
        break;
      case "birthDate":
        setBirthDate(new Date(user.birthDate));
        break;
      case "telephone":
        settelephone(user.telephone);
        break;
      case "address":
        setaddress(user.address);
        break;
      case "postCode":
        setpostCode(user.postCode);
        break;
      case "country":
        setcountry(user.country);
        break;
      case "region":
        setregion(user.region);
        break;
      case "username":
        setusername(user.username);
        break;
      case "email":
        setemail(user.email);
        break;
      case "password":
        setpassword(user.password);
        break;
      default:
        break;
    }
  };
  const handleSave = async (e) => {
    e.preventDefault();
    if (
      firstName &&
      lastName &&
      birthDate &&
      telephone &&
      address &&
      postCode &&
      country &&
      region
    )
      {
        if(profileData.data)
        dispatch(
          profileUpdateData({
            id,
            firstName,
            lastName,
            birthDate,
            telephone,
            address,
            postCode,
            country,
            region,
          })
        );
        else
        {
          dispatch(
          profilePostData({userID,
            firstName,
            lastName,
            birthDate,
            telephone,
            address,
            postCode,
            country,
            region,
          })
        );}
      }
  };

  return (
    <div>
      <div className={classNames(classes.main, classes.mainRaised)}>
        <div>
          <div className={classes.container}>
            <GridContainer justify="center">
              <GridItem xs={12} sm={12}>
                {profileData.loading ? (
                  <CircularProgress />
                ) : (
                  <NavPills
                    color="rose"
                    horizontal={{
                      tabsGrid: { xs: 12, sm: 4, md: 4 },
                      contentGrid: { xs: 12, sm: 8, md: 8 },
                    }}
                    tabs={[
                      {
                        tabButton: "Profile Settings",
                        tabIcon: Dashboard,
                        tabContent: (
                          <>
                            <form onSubmit={handleSave}>
                              <h1>Profile Settings</h1>
                              <h3>First Name :</h3>
                              <Input
                                id="firstName"
                                name="firstName"
                                inputProps={{
                                  placeholder: "First Name",
                                }}
                                value={firstName}
                                fullWidth
                                onChange={(e) => handleChange(e, "firstName")}
                              />

                              <h3>Last Name :</h3>
                              <Input
                                id="lastName"
                                name="lastName"
                                inputProps={{
                                  placeholder: "Last Name",
                                }}
                                value={lastName}
                                fullWidth
                                onChange={(e) => handleChange(e, "lastName")}
                              />

                              <h3>Date of Birth :</h3>

                              <FormControl fullWidth>
                                <DateTimePicker
                                  format="yyyy-MM-dd"
                                  onChange={(e) => handleChange(e, "birthDate")}
                                  value={birthDate}
                                />
                              </FormControl>

                              <h3>Phone Number :</h3>
                              <Input
                                id="telephone"
                                name="telephone"
                                inputProps={{
                                  placeholder: "Phone Number",
                                }}
                                value={telephone}
                                fullWidth
                                onChange={(e) => handleChange(e, "telephone")}
                              />

                              <h3>Address :</h3>
                              <Input
                                id="address"
                                name="address"
                                inputProps={{
                                  placeholder: "Address",
                                }}
                                value={address}
                                fullWidth
                                onChange={(e) => handleChange(e, "address")}
                              />

                              <h3>Post Code :</h3>
                              <Input
                                id="postCode"
                                name="postCode"
                                inputProps={{
                                  placeholder: "Post Code",
                                }}
                                value={postCode}
                                fullWidth
                                onChange={(e) => handleChange(e, "postCode")}
                              />

                              <h3>Country :</h3>
                              <CountryDropdown
                                id="country"
                                name="country"
                                value={country}
                                onChange={(e) => handleChange(e, "country")}
                              />

                              <h3>Region :</h3>
                              <RegionDropdown
                                id="region"
                                name="region"
                                country={country}
                                value={region}
                                onChange={(e) => handleChange(e, "region")}
                              />
                              <br />
                              <br />
                              <Button color="rose" type="submit" round>
                                Save
                              </Button>
                              <Button round>Cancel</Button>
                              <br />
                              <br />
                            </form>
                          </>
                        ),
                      },
                      {
                        tabButton: "Account Settings",
                        tabIcon: Schedule,
                        tabContent: (
                          <>
                            <form onSubmit={handleSave}>
                              <h1>Account Settings</h1>
                              <h3>Username :</h3>
                              <Input
                                id="username"
                                name="username"
                                inputProps={{
                                  placeholder: "Username",
                                }}
                                fullWidth
                                value={username}
                                onChange={(e) => handleChange(e, "username")}
                              />

                              <h3>Email :</h3>
                              <Input
                                type="email"
                                id="email"
                                name="email"
                                inputProps={{
                                  placeholder: "Email",
                                }}
                                value={email}
                                fullWidth
                                onChange={(e) => handleChange(e, "email")}
                              />

                              <h3>Password :</h3>
                              <Input
                                type="password"
                                id="password"
                                name="password"
                                inputProps={{
                                  placeholder: "Password",
                                }}
                                value={password}
                                fullWidth
                                onChange={(e) => handleChange(e, "password")}
                              />
                              <br />
                              <br />
                              <Button color="rose" type="submit" round>
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
                )}
              </GridItem>
            </GridContainer>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default AccountPage;
