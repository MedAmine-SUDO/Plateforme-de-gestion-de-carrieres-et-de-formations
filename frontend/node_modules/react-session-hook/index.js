"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const react_1 = require("react");
const context_1 = require("./context");
var cookies_1 = require("./storage/cookies");
exports.cookies = cookies_1.default;
var context_2 = require("./context");
exports.UseSessionProvider = context_2.UseSessionProvider;
exports.default = () => {
    return react_1.useContext(context_1.UseSessionContext);
};
//# sourceMappingURL=index.js.map