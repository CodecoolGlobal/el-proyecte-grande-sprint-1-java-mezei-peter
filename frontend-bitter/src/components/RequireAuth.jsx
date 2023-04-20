import React, {useContext} from 'react';
import { Navigate, useRoutes } from 'react-router-dom';
import {useUserIdCookie} from '../hooks/cookies.js';

const RequireAuth = ({Component}) => {
    const isAuthenticated = () => useUserIdCookie(window.localStorage) ?? false;

    const routes = useRoutes([
        {
            path: '*',
            element: isAuthenticated() ? <Component /> : <Navigate to="/login" />,
        },
    ]);

    return routes;
};

export default RequireAuth;