import { useState } from "react";
import FormControl from "@mui/material/FormControl";
import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import InputAdornment from "@mui/material/InputAdornment";
import IconButton from "@mui/material/IconButton";
import InputLabel from "@mui/material/InputLabel";
import Visibility from "@mui/icons-material/Visibility";
import VisibilityOff from "@mui/icons-material/VisibilityOff";

const postRegistration = async(registrationData) => {
    return await (await fetch('/api/user/registration', {
        method: "POST",
        headers : {
            "Content-Type" : "application/json"
        },
        body: JSON.stringify(registrationData)
    })).json()
}

const Login = () => {

    const  [disabled, setDisabled] = useState(true);

    const handleChange = (prop) => (event) => {
        setValues({...values, [prop]: event.target.value});
        setDisabled(!Object.values(values).every(item => item !== ""));
    };

    const [values, setValues] = useState({
        userEmail: "",
        password: "",
        username: "",
    });

    const handleRegistration = async () => {
        try {
            const response = await postRegistration(values);

            console.log(response);
            
            window.location.pathname = '/';
        } catch(e) {
            console.error(e);
        }
    }


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
                        onChange={handleChange("userEmail")}
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
                <FormControl fullWidth>
                    <TextField

                        name="username"
                        id="username"
                        label="username"
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
                        type="password"
                        value={values.password}
                        onChange={handleChange("password")}
                        endadornment={
                            <InputAdornment position="end">
                                <IconButton
                                    sx={{color: "whitesmoke"}}
                                    aria-label="toggle password visibility"
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
                    onClick={() => handleRegistration()}
                    disabled= {disabled}

                >
                    Register
                </Button>
            </Box>
        </div>
    
    ) ;
}
 
export default Login;