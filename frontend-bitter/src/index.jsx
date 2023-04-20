import React from "react";
import ReactDOM from "react-dom/client";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import "./index.css";
import Layout from "./components/Layout";
import ErrorPage from "./components/ErrorPage";
import Login from "./components/Login";
import BitFeed from "./components/BitFeed.jsx";
import Register from "./components/Register.jsx";
import { GlobalContextProvider } from "./contexts/GlobalContext.jsx";

import RequireAuth from './components/RequireAuth';
import UserProfile from "./components/user-profile/UserProfile.jsx";

const router = createBrowserRouter([
    {
        path: '/',
        element: <Layout/>,
        errorElement: <ErrorPage />,
        children: [
            {
                path: '/',
                element: <RequireAuth Component={BitFeed} />,
            },
            {
                path: '/login',
                element: <Login />,
            },
            {
                path: "/register",
                element: <Register />
            },
            {
                path: "/user/{userId}",
                element: <UserProfile />
            }
        ],
    },
]);

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
    <React.StrictMode>
         <GlobalContextProvider><RouterProvider router={router} /></GlobalContextProvider>
    </React.StrictMode>
);