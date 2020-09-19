import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import { stylesContent } from "../styles/Styles"
import TestPage from '../../Pages/TestPage/TestPage';

function TestList(props) {
  return (
    <TestPage />
  );
}

TestList.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(stylesContent)(TestList);
