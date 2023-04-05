import React, { createContext, useState } from 'react';

export const UserContext = createContext();

export const UserContextProvider = ({children}) => {
    const [userId, setUserId] = useState({'userId': ""});

    return (
        <UserContext.Provider value={{userId, setUserId}}>
            {children}
        </ UserContext.Provider>
    );
}
