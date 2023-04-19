import React, { createContext, useState } from 'react';

export const GlobalContext = createContext(null);

export const GlobalContextProvider = ({children}) => {
    const [userId, setUserId] = useState("");

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
