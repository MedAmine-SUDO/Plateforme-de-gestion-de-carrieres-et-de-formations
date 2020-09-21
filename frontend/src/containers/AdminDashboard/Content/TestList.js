import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import { stylesContent } from "../styles/Styles"
import NavPills from '../../../components/NavPills/NavPills';
import { AssignmentInd, Toc } from '@material-ui/icons';
import TestPage from '../../Pages/TestPage/TestPage';

function TestList(props) {

  return (

    
        <NavPills style={{display:""}} color="adminDashboard" tabs={[{
          tabButton: "Test de niveau",
          tabIcon: Toc, 
          tabContent:<>
            <TestPage />

          </>
        }, {
          tabButton: "Test de profile d'apprentissage",
          tabIcon: AssignmentInd
        }]} />




  );
}

TestList.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(stylesContent)(TestList);
