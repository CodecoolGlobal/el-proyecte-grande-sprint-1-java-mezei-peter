import {Link} from "react-router-dom";
import Box from "@mui/material/Box";
import useUserIdCookie from "../hooks/cookies.js";
import Button from "@mui/material/Button";
import React from "react";

const MyProfileButton = () => {
    return (
        <>
            <Link
                style={{
                    textDecoration: "none"
                }}
                className="button ml-10 flex text-gray-900"
                to="/my-profile"
            >
                <Box>  { !useUserIdCookie(localStorage) ? null :
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
                        My Profile
                    </Button>
                } </Box>
            </Link>
        </>
    )
}

export default MyProfileButton;
