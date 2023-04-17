import React, { createContext, useState } from 'react';

export const UserContext = createContext(null);

export const UserContextProvider = ({children}) => {
    const [userId, setUserId] = useState("");

    return (
        <UserContext.Provider value={{userId, setUserId}}>
            {children}
        </ UserContext.Provider>
    );
}
