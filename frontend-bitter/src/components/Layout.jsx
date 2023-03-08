import React from "react";
import logo from "../logo.png";
import { Outlet, Link } from "react-router-dom";

function Layout() {
    return (
        <>
            <nav className="nav">
                <img src={logo} className="App-logo" alt="logo" />
                <ul className="nav-items">
                    <li>
                        <Link
                            style={{ textDecoration: "none" }}
                            className="link"
                            to="/"
                        >
                            Bitter
                        </Link>
                    </li>
                    <li>
                        <Link
                            style={{ textDecoration: "none" }}
                            className="link"
                            to="/about"
                        >
                            About
                        </Link>
                    </li>
                    <li>
                        <Link
                            style={{ textDecoration: "none" }}
                            className="link"
                            to="/contact"
                        >
                            Contact
                        </Link>
                    </li>
                    <div className="login-div">
                        <Link
                            style={{ textDecoration: "none" }}
                            className="login"
                            to="/login"
                        >
                            Log in
                        </Link>
                    </div>
                </ul>
            </nav>
            <Outlet />
        </>
    );
}

export default Layout;
