import React from "react";
import { useDispatch, useSelector } from "react-redux";
import _ from "lodash";
import { GetUserList } from "../actions/UserActions";
import { Link } from "react-router-dom";
const UserList = () => {
  const dispatch = useDispatch();
  const userList = useSelector((state) => state.UserList);
  React.useEffect(() => {
    FetchData();
  }, []);
  const FetchData = () => {
    dispatch(GetUserList());
  };
  const ShowData = () => {
    if (!_.isEmpty(userList.data)) {
      return userList.data.map((item) => {
        return <div key={item.id}>{item.email}</div>;
      });
    }
    if (userList.loading) {
      return <p>Loading...</p>;
    }
    if (userList.errorMsg !== "") {
      return <p>{userList.errorMs}</p>;
    }
    return " unable to get data ";
  };
  return <div>{ShowData()}</div>;
};

export default UserList;
