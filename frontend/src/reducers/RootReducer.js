import { combineReducers } from "redux";
import auth from "./AuthReducer"
import profile from "./ProfileReducer"
import formation from "./FormationReducer"
import ressource from "./RessourceReducer"
import competence from "./CompetenceReducer";
import notification from "./NotificationReducer";
const RootReducer = combineReducers({
  auth: auth,
  profile: profile,
  formation:formation,
  ressource:ressource,
  competence:competence,
  notification:notification
});

export default RootReducer;
