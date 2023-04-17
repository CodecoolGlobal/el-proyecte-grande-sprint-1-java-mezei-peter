import React from "react";
import ReactDOM from "react-dom/client";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import "./index.css";
import Layout from "./components/Layout";
import ErrorPage from "./components/ErrorPage";
import Login from "./components/Login";
import BitFeed from "./components/BitFeed.jsx";
import Register from "./components/Register.jsx";
import { UserContextProvider } from "./contexts/UserContext";

import RequireAuth from './components/RequireAuth';

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
            }
        ],
    },
]);

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
    <React.StrictMode>
         <UserContextProvider><RouterProvider router={router} /></UserContextProvider>
    </React.StrictMode>
);