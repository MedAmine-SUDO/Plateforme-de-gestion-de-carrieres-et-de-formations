"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const universal_cookie_1 = __importDefault(require("universal-cookie"));
const defaultOptions = {
    storageAliases: {
        accessToken: "accessToken",
        idToken: "idToken",
        refreshToken: "refreshToken",
        token: "token"
    }
};
const cookieStorage = (options = {}) => {
    const { req, storageAliases } = Object.assign({}, defaultOptions, options);
    const cookies = new universal_cookie_1.default(req ? req.headers.cookie : undefined);
    return {
        get: () => {
            const allCookies = cookies.getAll();
            return {
                accessToken: allCookies[storageAliases.accessToken],
                idToken: allCookies[storageAliases.idToken],
                refreshToken: allCookies[storageAliases.refreshToken],
                token: allCookies[storageAliases.token]
            };
        },
        remove: () => {
            cookies.remove(storageAliases.accessToken);
            cookies.remove(storageAliases.idToken);
            cookies.remove(storageAliases.refreshToken);
            cookies.remove(storageAliases.token);
        },
        set: ({ accessToken, idToken, refreshToken, token }, expires) => {
            if (accessToken) {
                cookies.set(storageAliases.accessToken, accessToken, { expires });
            }
            if (idToken) {
                cookies.set(storageAliases.idToken, idToken, { expires });
            }
            if (refreshToken) {
                cookies.set(storageAliases.refreshToken, refreshToken, { expires });
            }
            if (token) {
                cookies.set(storageAliases.token, token, { expires });
            }
        }
    };
};
cookieStorage.get = (options = {}) => {
    return cookieStorage(options).get();
};
cookieStorage.set = (tokens, expires) => {
    return cookieStorage().set(tokens, expires);
};
cookieStorage.remove = () => {
    return cookieStorage().remove();
};
exports.default = cookieStorage;
//# sourceMappingURL=cookies.js.map