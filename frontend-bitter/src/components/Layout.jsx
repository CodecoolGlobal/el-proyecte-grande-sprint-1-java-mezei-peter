import React from "react";
import logo from "../logo.png";
import {Outlet, Link} from "react-router-dom";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";

function Layout() {
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

                <Link
                    style={{textDecoration: "none"}}
                    className="button ml-auto flex text-gray-900"
                    to="/login"
                ><Box>
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
                    </Button>
                </Box>
                </Link>


            </header>
            <Outlet/>
        </>
    );
}

export default Layout;
