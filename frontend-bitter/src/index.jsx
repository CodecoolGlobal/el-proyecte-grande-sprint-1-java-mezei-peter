import React from "react";
import ReactDOM from "react-dom/client";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import "./index.css";
import Layout from "./components/Layout";
import ErrorPage from "./components/ErrorPage";
import Login from "./components/Login";
import BitFeed from "./components/BitFeed.jsx";
import { UserContextProvider } from "./contexts/UserContext";

const router = createBrowserRouter([
    {
        path: "/",
        element: <Layout />,
        errorElement: <ErrorPage />,
        children: [
            {
                path: "/",
                element: <UserContextProvider><BitFeed /></UserContextProvider>,
            },
            {
                path: "/login",
                element: <UserContextProvider><Login /></UserContextProvider>,
            },

        ],
    },
]);

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
    <React.StrictMode>
         <UserContextProvider><RouterProvider router={router} /></UserContextProvider>
    </React.StrictMode>
);