import React from "react";
import { UseSession, UseSessionProviderProps } from "./interfaces";
export declare const UseSessionContext: React.Context<UseSession<any>>;
export declare const UseSessionProvider: <TProfile extends Record<string, any> = Record<string, any>>(props: Partial<UseSessionProviderProps<TProfile>> & {
    children?: React.ReactNode;
}) => JSX.Element;
//# sourceMappingURL=context.d.ts.map