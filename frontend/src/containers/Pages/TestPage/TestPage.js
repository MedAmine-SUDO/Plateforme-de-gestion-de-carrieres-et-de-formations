import React from "react";
import classNames from "classnames";
import { makeStyles } from "@material-ui/core/styles";
import styles from "../../../assets/jss/material-kit-react/views/testPage.js";
import GridContainer from "../../../components/Grid/GridContainer.js";
import Footer from "../../../components/Footer/Footer.js";
import NavPills from "../../../components/NavPills/NavPills.js";
import QuestionTable from "./Sections/QuestionTable.js";

const useStyles = makeStyles(styles);
function TestPage() {
  const classes = useStyles();
  
  return (
    <div>
      <div className={classNames(classes.main, classes.mainRaised)}>
        <div>
          <div className={classes.container}>
            <GridContainer justify="left">
            {/* <GridItem xs={12} sm={12} md={6}>
                <div className={classes.profile}>
                  <div className={classes.name}>
                    <h2>write HERE</h2>
                  </div>
                </div>
              </GridItem> */}
              <NavPills
                color="primary"
                
                tabs={[{tabButton:"Questions", tabContent:(
                  <>
                  <QuestionTable />
                  </>
                )
              },{tabButton:"Tests"  }]}
              />
              
            </GridContainer>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default TestPage;
