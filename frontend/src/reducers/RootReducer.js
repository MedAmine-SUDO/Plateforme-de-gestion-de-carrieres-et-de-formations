import { combineReducers } from "redux";
import auth from "./AuthReducer"
import profile from "./ProfileReducer"
import formation from "./FormationReducer"
import ressource from "./RessourceReducer"
const RootReducer = combineReducers({
  auth: auth,
  profile: profile,
  formation:formation,
  ressource:ressource
});

export default RootReducer;
