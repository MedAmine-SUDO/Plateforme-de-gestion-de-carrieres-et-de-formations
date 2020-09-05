"use strict";
var __rest = (this && this.__rest) || function (s, e) {
    var t = {};
    for (var p in s) if (Object.prototype.hasOwnProperty.call(s, p) && e.indexOf(p) < 0)
        t[p] = s[p];
    if (s != null && typeof Object.getOwnPropertySymbols === "function")
        for (var i = 0, p = Object.getOwnPropertySymbols(s); i < p.length; i++) if (e.indexOf(p[i]) < 0)
            t[p[i]] = s[p[i]];
    return t;
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const react_1 = __importDefault(require("react"));
const cookies_1 = __importDefault(require("./storage/cookies"));
const reducer_1 = require("./reducer");
const useGlobalEvents_1 = __importDefault(require("./utils/useGlobalEvents"));
const useSessionTimers_1 = __importDefault(require("./utils/useSessionTimers"));
const defaults = {
    globalLogin: true,
    globalLogout: true,
    jwt: true,
    refreshInterval: 15 * 60 * 1000,
    storage: cookies_1.default,
    isAuthenticated: false,
    removeSession: () => undefined,
    setSession(value) {
        this.dispatch({ type: "setSession", value });
    },
    clearErrorMessage: () => undefined,
    setErrorMessage: () => undefined,
    dispatch: () => undefined,
    isAuthenticatedGuard: () => false
};
exports.UseSessionContext = react_1.default.createContext(defaults);
exports.UseSessionProvider = (props) => {
    const _a = Object.assign({}, defaults, props), { children, initialAccessToken, initialIdToken, initialProfile, initialRefreshToken, initialToken } = _a, options = __rest(_a, ["children", "initialAccessToken", "initialIdToken", "initialProfile", "initialRefreshToken", "initialToken"]);
    const { accessToken, idToken, refreshToken, token } = options.storage.get();
    const initialState = Object.assign({ accessToken: initialAccessToken || accessToken, idToken: initialIdToken || idToken, refreshToken: initialRefreshToken || refreshToken, token: initialToken || token, profile: initialProfile, isAuthenticated: false }, options);
    const [state, dispatch] = react_1.default.useReducer(reducer_1.reducer, initialState, reducer_1.getState);
    const session = Object.assign({}, state, { dispatch, setSession: (value) => dispatch({ type: "setSession", value }), removeSession: () => dispatch({ type: "removeSession" }), clearErrorMessage: () => dispatch({ type: "setErrorMessage" }), setErrorMessage: (value) => {
            dispatch({ type: "setErrorMessage", value });
        }, isAuthenticatedGuard() {
            return state.isAuthenticated;
        } });
    useGlobalEvents_1.default(session);
    useSessionTimers_1.default(session);
    return (react_1.default.createElement(exports.UseSessionContext.Provider, { value: session }, children));
};
//# sourceMappingURL=context.js.map