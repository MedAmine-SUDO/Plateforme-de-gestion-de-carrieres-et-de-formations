"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const jwt_decode_1 = __importDefault(require("jwt-decode"));
exports.reducer = (state, action) => {
    if (action.type === "setSession") {
        state.storage.set(action.value);
        return exports.getState(Object.assign({}, state, action.value));
    }
    if (action.type === "removeSession") {
        window.localStorage.setItem("logout", Date.now().toString());
        state.storage.remove();
        return exports.getState(Object.assign({}, state, { expiration: null, isAuthenticated: false, profile: undefined, accessToken: undefined, idToken: undefined, refreshToken: undefined, token: undefined }));
    }
    if (action.type === "setErrorMessage") {
        return exports.getState(Object.assign({}, state, { errorMessage: action.value }));
    }
    throw new Error();
};
exports.getState = (state) => {
    const profile = exports.getProfile(Object.assign({}, state));
    const expiration = exports.getExpiration(Object.assign({}, state, { profile }));
    const isAuthenticated = exports.getIsAuthenticated(Object.assign({}, state, { expiration, profile }));
    return Object.assign({}, state, { expiration,
        isAuthenticated,
        profile });
};
exports.getProfile = ({ accessToken, idToken, jwt, profile, profileFn, token }) => {
    if (profileFn) {
        if (accessToken && idToken) {
            profile = profileFn(idToken);
        }
        else if (token) {
            profile = profileFn(token);
        }
    }
    else if (jwt) {
        if (accessToken && idToken) {
            profile = jwt_decode_1.default(idToken);
        }
        else if (token) {
            profile = jwt_decode_1.default(token);
        }
    }
    return profile;
};
exports.getExpiration = ({ expiration, profile }) => {
    if (expiration || expiration === null) {
        return expiration;
    }
    if (profile && profile.exp) {
        return new Date(profile.exp * 1000);
    }
    return new Date(Date.now() + 10 * 60 * 60 * 1000); // 10 hours
};
exports.getIsAuthenticated = ({ expiration, profile }) => {
    if (expiration === null && profile) {
        return true;
    }
    if (expiration && profile) {
        return Date.now() < expiration.valueOf();
    }
    return false;
};
//# sourceMappingURL=reducer.js.map