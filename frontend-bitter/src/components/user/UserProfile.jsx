import React, { useState, useEffect } from "react";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";
import Modal from "../Modal/Modal.jsx";

function UserProfile(props) {
    const [isOpen, setIsOpen] = useState(false);

    return (
      <div className="className=sm:p-8 px-4 py-8 w-full bg-[#FFFBE9] min-h-[calc(100vh-73px)]">
          <h1>user profile here</h1>
          <Button onClick={() => setIsOpen(true)}>open modal</Button>
          <Modal open={isOpen} onClose={() => setIsOpen(false)}>

          </Modal>
      </div>
    );
}

export default UserProfile;