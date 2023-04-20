import React, {useContext, useEffect} from "react";
import logo from "../logo.png";
import {Outlet, Link, useNavigate} from "react-router-dom";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";
import SearchBar from "./SearchBar.jsx";

import {useUserIdCookie} from '../hooks/cookies.js';
import {GlobalContext} from "../contexts/GlobalContext.jsx";

function Layout() {

    const logout = () => {
        window.localStorage.removeItem("token");
        window.localStorage.removeItem("userId");
        window.location.reload();
    }

    const globalContext = useContext(GlobalContext);
    const loggedInUserId = useUserIdCookie(localStorage);

    return (
        <>
            <header
                className="w-full flex justify-between items-center h-20 sm:px-8 px-4 py-4 border-b border-b-[#e6ebf4] bg-[#FFFBE9]">

                <Link
                    style={{textDecoration: "none"}}
                    className="flex text-gray-900"
                    to="/"
                >
                    <img src={logo} className="w-20 object-contain" alt="logo"/>
                    <h1 className="hidden md:block mt-5 font-extrabold text-[#222328] text-[32px] pr-5">
                        Bitter
                    </h1>
                </Link>
                <div className="flex text-gray-900 pt-5">
                    <SearchBar />
                </div>


                <Box>  { !useUserIdCookie(localStorage) ? <Link
                    style={{textDecoration: "none"}}
                    className="button ml-auto flex text-gray-900"
                    to="/login"
                >
                    <Button
                        sx={{
                            backgroundColor: "#FFFBE9",
                            color: "black",
                            border: "2px solid #262018",
                            ":hover": {
                                border: "2px solid #262018",
                                bgcolor: "whitesmoke",
                                color: "black",
                            },
                        }}

                        variant="outlined"
                    >
                        Log in
                    </Button></Link> : <Link
                        style={{textDecoration: "none"}}
                        className="button ml-auto flex text-gray-900"
                        to="/login"
                    ><Button
                        sx={{
                            backgroundColor: "#FFFBE9",
                            color: "black",
                            border: "2px solid #262018",
                            ":hover": {
                                border: "2px solid #262018",
                                bgcolor: "whitesmoke",
                                color: "black",
                            },
                        }}

                        variant="outlined"
                        onClick={() => {
                            logout();

                        }}
                    >
                        Logout
                    </Button></Link>}
                </Box>


            </header>
            <Outlet />
        </>
    );
}

export default Layout;
