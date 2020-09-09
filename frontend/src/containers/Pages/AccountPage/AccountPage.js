import React, { useState } from "react";
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
import CustomInput from "../../../components/CustomInput/CustomInput.js";
import Button from "../../../components/CustomButtons/Button.js";
import DateTimePicker from "react-datetime-picker";

const useStyles = makeStyles(styles);
function AccountPage() {
  const classes = useStyles();
  const [birthDate, setBirthDate] = useState(new Date("2000-01-01"));
  const onChange = (date) => {
    console.log(birthDate);
    setBirthDate(date);
  };

  return (
    <div>
      <div className={classNames(classes.main, classes.mainRaised)}>
        <div>
          <div className={classes.container}>
            <GridContainer justify="center">
              <GridItem xs={12} sm={12}>
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
                          <form>
                            <h1>Profile Settings</h1>
                            <strong>First Name :</strong>
                            <CustomInput
                              id="firstName"
                              name="firstName"
                              inputProps={{
                                placeholder: "First Name",
                              }}
                              formControlProps={{
                                fullWidth: true,
                              }}
                            />
                            <strong>Last Name :</strong>
                            <CustomInput
                              id="lastName"
                              name="lastName"
                              inputProps={{
                                placeholder: "Last Name",
                              }}
                              formControlProps={{
                                fullWidth: true,
                              }}
                            />
                            <br />
                            <br />
                            <strong>Date of Birth :</strong>
                            <br />
                            <br />
                            <FormControl fullWidth>
                              <DateTimePicker
                                format="yyyy-MM-dd"
                                onChange={onChange}
                                value={birthDate}
                              />
                            </FormControl>
                            <br />
                            <br />
                            <strong>Phone Number :</strong>
                            <CustomInput
                              id="telephone"
                              name="telephone"
                              inputProps={{
                                placeholder: "Phone Number",
                              }}
                              formControlProps={{
                                fullWidth: true,
                              }}
                            />
                            <strong>Address :</strong>
                            <CustomInput
                              id="address"
                              name="address"
                              inputProps={{
                                placeholder: "Address",
                              }}
                              formControlProps={{
                                fullWidth: true,
                              }}
                            />
                            <strong>Post Code :</strong>
                            <CustomInput
                              id="postCode"
                              name="postCode"
                              inputProps={{
                                placeholder: "Post Code",
                              }}
                              formControlProps={{
                                fullWidth: true,
                              }}
                            />
                            <strong>City :</strong>
                            <CustomInput
                              id="city"
                              name="city"
                              inputProps={{
                                placeholder: "City",
                              }}
                              formControlProps={{
                                fullWidth: true,
                              }}
                            />

                            <strong>Country :</strong>
                            <CustomInput
                              id="country"
                              name="country"
                              inputProps={{
                                placeholder: "Country",
                              }}
                              formControlProps={{
                                fullWidth: true,
                              }}
                            />
                            <br />
                            <br />
                            <Button color="rose" round>
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
                          <form>
                            <h1>Account Settings</h1>
                            <strong>Username :</strong>
                            <CustomInput
                              id="username"
                              name="username"
                              inputProps={{
                                placeholder: "Username",
                              }}
                              formControlProps={{
                                fullWidth: true,
                              }}
                            />
                            <strong>Email :</strong>
                            <CustomInput
                              id="email"
                              name="email"
                              inputProps={{
                                placeholder: "Email",
                              }}
                              formControlProps={{
                                fullWidth: true,
                              }}
                            />
                            <strong>Password :</strong>
                            <CustomInput
                              id="password"
                              name="password"
                              inputProps={{
                                placeholder: "Password",
                              }}
                              formControlProps={{
                                fullWidth: true,
                              }}
                            />

                            <br />
                            <br />
                            <Button color="rose" round>
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
  );
}

export default AccountPage;
