import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import { stylesContent } from "../styles/Styles"
import NavPills from '../../../components/NavPills/NavPills';
import { AssignmentInd, Toc } from '@material-ui/icons';
import TestNiveauAdmin from '../../Pages/TestPage/TestNiveauAdmin';
import TestProfileAdmin from '../../Pages/TestPage/TestProfileAdmin';

function TestList(props) {

  return (

    
        <NavPills style={{display:""}} color="adminDashboard" tabs={[{
          tabButton: "Test de niveau",
          tabIcon: Toc, 
          tabContent:<>
            <TestNiveauAdmin />

          </>
        }, {
          tabButton: "Test de profile d'apprentissage",
          tabIcon: AssignmentInd,
          tabContent:<>
            <TestProfileAdmin />
          </>
        }]} />




  );
}

TestList.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(stylesContent)(TestList);
