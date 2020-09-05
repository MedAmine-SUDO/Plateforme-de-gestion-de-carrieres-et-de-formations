import { combineReducers } from "redux";
import UserListReducer from "./UserListReducer";
import auth from "./AuthReducer"
const RootReducer = combineReducers({
  UserList: UserListReducer,
  auth: auth
});

export default RootReducer;
