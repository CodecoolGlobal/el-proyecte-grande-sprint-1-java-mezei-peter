import React, {useState, useEffect, useContext} from "react";
import Button from "@mui/material/Button";
import {ButtonGroup} from "@mui/material";
import Box from "@mui/material/Box";
import Modal from "../Modal/Modal.jsx";
import useFetch from "../../hooks/UseFetch.js";
import {GlobalContext} from "../../contexts/GlobalContext.jsx";
import {useParams} from "react-router-dom";
import FollowUser from "../FollowUser.jsx";

function UserProfile(props) {
    const [followersIsOpen, setFollowersIsOpen] = useState(false);
    const [followedIsOpen, setFollowedIsOpen] = useState(false);
    const { userId } = useParams();
    const fetch = useFetch(`/api/user/${userId}`);
    const user = fetch.data;

    //setInterval(() => {
    //    console.log(fetch);
    //}, 2000);

    if(userId === "error" || fetch.error || !user) return (<div className="className=sm:p-8 px-4 py-8 w-full bg-[#FFFBE9] min-h-[calc(100vh-73px)]">Something went wrong!</div>);

    return ( fetch.loading ? <div className="className=sm:p-8 px-4 py-8 w-full bg-[#FFFBE9] min-h-[calc(100vh-73px)]">Loading...</div> :
      <div className="className=sm:p-8 px-4 py-8 w-full bg-[#FFFBE9] min-h-[calc(100vh-73px)]">
          <div id="follower-data">
              <ButtonGroup variant="contained" aria-label="outlined primary button group">
                  <Button onClick={() => {setFollowersIsOpen(true);}}>Followers</Button>
                  <Button onClick={() => setFollowedIsOpen(true)}>Followed</Button>
              </ButtonGroup>
          </div>
          <div>
              <h1>User data</h1>
              <h2>{`Username: ${user.username}`}</h2>
              <br/>
              <h2>{`${user.userBio}`}</h2>
              <h2>{`Member since: ${user.dateOfRegistration}`}</h2>
          </div>
          <Modal id="followersModal" open={followersIsOpen} onClose={() => setFollowersIsOpen(false)}></Modal>
          <Modal id="followedModal" open={followedIsOpen} onClose={() => setFollowedIsOpen(false)}></Modal>
      </div>
    );
}

export default UserProfile;