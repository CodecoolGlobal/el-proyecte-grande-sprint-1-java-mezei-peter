import React, { useState, useEffect } from "react";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";
import Modal from "../Modal.jsx";

function UserProfile(props) {
    const [isOpen, setIsOpen] = useState(true);

    return (
      <div className="className=sm:p-8 px-4 py-8 w-full bg-[#FFFBE9] min-h-[calc(100vh-73px)]">
          <h1>user profile here</h1>
          <Modal content={"content"} isOpen={true} setIsOpen={setIsOpen} />
      </div>
    );
}

export default UserProfile;