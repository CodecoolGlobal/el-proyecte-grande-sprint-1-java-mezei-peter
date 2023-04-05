import React from 'react';
import { Navigate, useRoutes } from 'react-router-dom';

const RequireAuth = (Component) => {
    const isAuthenticated = false; // replace with your actual authentication logic

    const routes = useRoutes([
        {
            path: '*',
            element: isAuthenticated ? <Component /> : <Navigate to="/login" />,
        },
    ]);

    return routes;
};

export default RequireAuth;