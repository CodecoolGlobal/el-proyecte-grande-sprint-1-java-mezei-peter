import {Link} from "react-router-dom";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import React from "react";

const MyProfileButton = () => {
    return (
        <>
            <Link
                style={{
                    textDecoration: "none",
                    width: "max-content"
                }}
                className="button ml-10 flex text-gray-900"
                to={`/user/${window.localStorage.getItem("userId")}`}
            >
                <Box>  { !window.localStorage.getItem("userId") ? null :
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
                        to={`/user/${window.localStorage.getItem("userId")}`}
                    >
                        My Profile
                    </Button>
                } </Box>
            </Link>
        </>
    )
}

export default MyProfileButton;
