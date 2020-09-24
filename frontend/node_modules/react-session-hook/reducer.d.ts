import { DispatchAction, UseSession } from "./interfaces";
export declare const reducer: <TProfile>(state: UseSession<TProfile>, action: DispatchAction) => UseSession<TProfile>;
export declare const getState: <TProfile>(state: UseSession<TProfile>) => UseSession<TProfile>;
export declare const getProfile: <TProfile>({ accessToken, idToken, jwt, profile, profileFn, token }: any) => TProfile | undefined;
export declare const getExpiration: ({ expiration, profile }: any) => any;
export declare const getIsAuthenticated: ({ expiration, profile }: any) => boolean;
//# sourceMappingURL=reducer.d.ts.map