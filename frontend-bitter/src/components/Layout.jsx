import React, { useEffect } from "react";
import logo from "../logo.png";
import {Outlet, Link} from "react-router-dom";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";
import SearchBar from "./SearchBar.jsx";

function Layout() {

    const logout = () => {
        window.localStorage.removeItem("token");
        window.localStorage.removeItem("userId");
        window.location.reload();
    }

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
                    <h1 className="mt-5 font-extrabold text-[#222328] text-[32px]">
                        Bitter
                    </h1>
                </Link>
                <SearchBar/>

               <Link
                    style={{textDecoration: "none"}}
                    className="button ml-auto flex text-gray-900"
                    to="/login"
                ><Box>  { !window.localStorage.getItem("userId") ?  
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
                    </Button> : <Button
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
                        onClick={() => logout()}
                    >
                        Logout
                    </Button>}
                </Box>
                </Link> 


            </header>
            <Outlet />
        </>
    );
}

export default Layout;
