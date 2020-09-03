import { createStore } from "redux";
import { composeWithDevTools } from "redux-devtools-extension";
import { applyMiddleware} from "redux";
import thunk from "redux-thunk";
import RootReducer from "./reducers/RootReducer";
import { persistStore, persistReducer } from 'redux-persist';
import storage from 'redux-persist/lib/storage';

const persistConfig = {
    key: 'auth',
    storage: storage,
    whitelist: ['auth'] // which reducer want to store
  };
  const pReducer = persistReducer(persistConfig, RootReducer);

const Store = createStore(pReducer, composeWithDevTools(applyMiddleware(thunk)));
const persistor = persistStore(Store);

export  { persistor, Store };