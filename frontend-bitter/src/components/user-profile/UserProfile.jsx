import React, { useState, useEffect } from "react";
import Button from "@mui/material/Button";
import Box from "@mui/material/Box";
import Modal from "../Modal/Modal.jsx";
import useFetch from "../../hooks/UseFetch.js";
import FollowUser from "../FollowUser.jsx";

function UserProfile(props) {
    const [followersIsOpen, setFollowersIsOpen] = useState(false);
    const [followedIsOpen, setFollowedIsOpen] = useState(false);
    const fetched = useFetch("https://swapi.dev/api/people/1/");

    return (
      <div className="className=sm:p-8 px-4 py-8 w-full bg-[#FFFBE9] min-h-[calc(100vh-73px)]">
          <Button onClick={() => setFollowersIsOpen(true)}>Followers</Button>
          <Modal id="followersModal" open={followersIsOpen} onClose={() => setFollowersIsOpen(false)}>
          </Modal>
          <Button onClick={() => setFollowedIsOpen(true)}>Followed</Button>
          <Modal id="followedModal" open={followedIsOpen} onClose={() => setFollowedIsOpen(false)}>
          </Modal>
      </div>
    );
}

export default UserProfile;