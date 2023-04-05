import React, { useContext } from "react";
import FormControl from "@mui/material/FormControl";
import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import InputAdornment from "@mui/material/InputAdornment";
import IconButton from "@mui/material/IconButton";
import InputLabel from "@mui/material/InputLabel";
import Visibility from "@mui/icons-material/Visibility";
import VisibilityOff from "@mui/icons-material/VisibilityOff";

import "../App.css";
import { UserContext } from "../contexts/UserContext";

const postLogin = async (username, password) => {
    return await fetch(`/api/user/login?username=${username}&password=${password}`, {
        method : "POST",
        headers : {
            "Content-Type" : "application/json",
            "Accept" : "*/*"
        }
    })
}

function Login() {
    const [values, setValues] = React.useState({
        amount: "",
        password: "",
        username: "",
        weight: "",
        weightRange: "",
        showPassword: false,
    });

    const {userId, setUserId} = useContext(UserContext);

    const handleLogin = async () => {
        try {
            const data = await postLogin(values.username, values.password);
            //console.log(data)
            console.log(data.headers.get("authorization"))

        } catch (e) {
            console.log(e);
        }
    }

    const handleChange = (prop) => (event) => {
        setValues({...values, [prop]: event.target.value});
    };

    const handleClickShowPassword = () => {
        setValues({
            ...values,
            showPassword: !values.showPassword,
        });
    };

    const handleMouseDownPassword = (event) => {
        event.preventDefault();
    };

    return (
        <div className="className=sm:p-8 px-4 py-8 w-full bg-[#FFFBE9] min-h-[calc(100vh-73px)]">
            <Box
                sx={{
                    margin: "4rem",
                    display: "flex",
                    gap: "1rem",
                    flexWrap: "wrap",
                    width: "35rem",
                }}
                component="form"
                // onSubmit={}
            >
                <FormControl fullWidth>
                    <TextField

                        name="email"
                        id="email"
                        label="Email"
                        variant="outlined"
                        onChange={handleChange("username")}
                        sx={{
                            "& .MuiOutlinedInput-root.Mui-focused": {
                                "& > fieldset": {
                                    borderColor: "#262018"
                                }
                            },
                            input: {color: "#262018"}
                        }}
                        InputLabelProps={{
                            style: {color: "#262018"},
                        }}
                    />
                </FormControl>
                <FormControl
                    fullWidth
                    sx={{input: {color: "#262018"}}}
                    variant="outlined"
                >
                    <InputLabel
                        sx={{
                            color: "whitesmoke",
                            "&.Mui-focused": {
                                color: "whitesmoke",
                            },
                        }}
                        htmlFor="outlined-adornment-password"
                    >
                    </InputLabel>
                    <TextField
                        sx={{
                            "& .MuiOutlinedInput-root.Mui-focused": {
                                "& > fieldset": {
                                    borderColor: "#262018"
                                }
                            },
                            input: {color: "#262018"}
                        }}
                        InputLabelProps={{
                            style: {color: "#262018"},

                        }}
                        id="outlined-adornment-password"
                        type={values.showPassword ? "text" : "password"}
                        value={values.password}
                        onChange={handleChange("password")}
                        endAdornment={
                            <InputAdornment position="end">
                                <IconButton
                                    sx={{color: "whitesmoke"}}
                                    aria-label="toggle password visibility"
                                    onClick={handleClickShowPassword}
                                    onMouseDown={handleMouseDownPassword}
                                    edge="end"
                                >
                                    {values.showPassword ? (
                                        <VisibilityOff/>
                                    ) : (
                                        <Visibility/>
                                    )}
                                </IconButton>
                            </InputAdornment>
                        }
                        label="Password"
                    />
                </FormControl>
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
                    onClick={() => handleLogin()}

                >
                    Log in
                </Button>
            </Box>
        </div>
    );
}

export default Login;
