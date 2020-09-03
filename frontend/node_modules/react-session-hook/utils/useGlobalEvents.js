"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const react_1 = require("react");
exports.default = ({ globalLogin, globalLogout, setSession, removeSession, storage }) => {
    /**
     * Global logout/login
     */
    react_1.useEffect(() => {
        const logoutEvent = (event) => {
            if (globalLogout && event.key === "logout") {
                removeSession();
            }
            if (globalLogin && event.key === "login") {
                setSession(storage.get());
            }
        };
        window.addEventListener("storage", logoutEvent);
        return () => {
            window.localStorage.removeItem("logout");
            window.localStorage.removeItem("login");
            window.removeEventListener("storage", logoutEvent);
        };
    }, [globalLogout, globalLogin]);
};
//# sourceMappingURL=useGlobalEvents.js.map