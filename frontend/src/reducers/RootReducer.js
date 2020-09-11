import { combineReducers } from "redux";
import auth from "./AuthReducer"
import profile from "./ProfileReducer"
const RootReducer = combineReducers({
  auth: auth,
  profile: profile
});

export default RootReducer;
