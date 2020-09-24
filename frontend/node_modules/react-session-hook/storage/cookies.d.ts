import { Tokens } from "../interfaces";
interface CookieStorageOptions {
    req?: {
        headers: {
            cookie?: string;
        };
    };
    storageAliases: {
        accessToken: string;
        idToken: string;
        refreshToken: string;
        token: string;
    };
}
declare const cookieStorage: {
    (options?: Partial<CookieStorageOptions>): {
        get: () => Tokens;
        remove: () => void;
        set: ({ accessToken, idToken, refreshToken, token }: Tokens, expires?: Date | undefined) => void;
    };
    get(options?: Partial<CookieStorageOptions>): Tokens;
    set(tokens: Tokens, expires?: Date | undefined): void;
    remove(): void;
};
export default cookieStorage;
//# sourceMappingURL=cookies.d.ts.map