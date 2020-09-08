import React from "react";
import Header from "../../components/Header/Header.js";
import HeaderLinks from "../../components/Header/HeaderLinks.js";

const NavBar = (props) => {
  const { ...rest } = props;
  return (
    <Header
      color="dark"
      brand="Material Kit React"
      rightLinks={<HeaderLinks />}
      fixed
      changeColorOnScroll={{
        height: 200,
        color: "white",
      }}
      {...rest}
    />
  );
};

export default NavBar;
