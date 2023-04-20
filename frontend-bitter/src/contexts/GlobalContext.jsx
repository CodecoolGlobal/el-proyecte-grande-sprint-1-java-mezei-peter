import React, { createContext, useState } from 'react';
import useUserIdCookie from "../hooks/cookies.js";

export const GlobalContext = createContext(null);

export const GlobalContextProvider = ({children}) => {
    const [userId, setUserId] = useState(useUserIdCookie());

    return (
        <GlobalContext.Provider
            value={{
                user: {
                    userId,
                    setUserId
                },
                data: (thing) => {
                    console.log(thing);
                }
            }}
        >
            {children}
        </ GlobalContext.Provider>
    );
}
