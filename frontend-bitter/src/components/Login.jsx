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

import { GlobalContext } from "../contexts/GlobalContext.jsx";
import {Link} from "react-router-dom";
import {useUserIdCookie} from "../hooks/cookies.js";

const postLogin = async (username, password) => {
    const res =  await fetch(`/api/user/login?username=${username}&password=${password}`, {
        method : "POST",
        headers : {
            "Content-Type" : "application/json",
            "Accept" : "*/*"
        }
    })

    const id = await res.text();

    console.log(await id);

    return {"authorization" : res.headers.get("authorization"), "userId" : id};
}

function Login() {
    const globalContext = useContext(GlobalContext);

    const [values, setValues] = React.useState({
        amount: "",
        password: "",
        username: "",
        weight: "",
        weightRange: "",
        showPassword: false,
    });

    const handleLogin = async () => {
        try {
            const data = await postLogin(values.username, values.password);
            
            const token = data.authorization
            window.localStorage.setItem("token", token);
            window.localStorage.setItem("userId", data.userId);

            globalContext.user.setUserId(useUserIdCookie(localStorage));

            window.location.pathname = '/';
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
        <div className="sm:p-8 px-4 py-8 w-full bg-[#FFFBE9] min-h-[calc(100vh-73px)]">
            <Box
                sx={{
                    display: 'flex',
                    justifyContent: 'space-between',
                    alignItems: 'center',
                    height: "50vh",
                    marginBottom: '1rem',
                    '& > *': {
                        flex: '1 1 auto'
                    }
                }}
            >
                <Box
                    sx={{
                        margin: '4rem 1rem 4rem 4rem',
                        display: 'flex',
                        flexDirection: 'column',
                        gap: '1rem',
                        mx: {xs: "auto", md: 0},
                        width: {xs: "100%", md: "80%"},

                    }}
                    component="form"
                >
                <FormControl fullWidth>
                    <TextField

                        name="email"
                        id="email"
                        label="Username"
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
                        endadornment={
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
                <Box
                    sx={{
                        display: 'flex',
                        flexDirection: 'column',
                        justifyContent: 'space-between',
                        '& > *': {
                            flex: '1 1 auto'
                        }
                    }}
                >
                    <Button
                        sx={{
                            backgroundColor: "#FFFBE9",
                            color: "black",
                            border: "2px solid #262018",
                            width: "100%",
                            marginBottom: "3%",
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
                    <Link
                        style={{
                            textDecoration: "none",
                            width: "100%"
                        }}
                        className="flex text-gray-900"
                        to="/register"
                    >
                        <Button
                            sx={{
                                backgroundColor: "#FFFBE9",
                                color: "black",
                                border: "2px solid #262018",
                                width: "100%",
                                ":hover": {
                                    border: "2px solid #262018",
                                    bgcolor: "whitesmoke",
                                    color: "black",
                                },
                            }}
                            variant="outlined"
                        >
                            Register
                        </Button>
                    </Link>
                </Box>
                </Box>
                <Box sx={{ margin: '4rem', flex: '0 1 auto' }} className="hidden md:block">
                    <h1 className="text-[#222328] font-bold text-[28px] mb-4">Welcome to Bitter!</h1>
                    <p className="text-[#222328] text-[18px] mb-4">
                        Bitter is a social media platform that allows you to share your thoughts, connect with friends and colleagues, and
                        stay up-to-date with the latest trends and news. With a simple and intuitive interface, Bitter makes it easy for
                        you to express yourself, engage with others, and join the conversation.
                    </p>
                    <p className="text-[#222328] text-[18px] mb-4">
                        Whether you're here to share your opinions, promote your business, or just have some fun, Bitter is the perfect
                        platform for you. So log in now and start exploring all that Bitter has to offer!
                    </p>
                </Box>
            </Box>
        </div>
    );
}

export default Login;
