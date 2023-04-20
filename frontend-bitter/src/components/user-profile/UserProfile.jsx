import React, {useState, useEffect, useContext} from "react";
import Button from "@mui/material/Button";
import {ButtonGroup} from "@mui/material";
import Box from "@mui/material/Box";
import Modal from "../Modal/Modal.jsx";
import useFetch from "../../hooks/UseFetch.js";
import {GlobalContext} from "../../contexts/GlobalContext.jsx";
import {useParams} from "react-router-dom";

function UserProfile(props) {
    const [followersIsOpen, setFollowersIsOpen] = useState(false);
    const [followedIsOpen, setFollowedIsOpen] = useState(false);
    const { userId } = useParams();
    const fetch = useFetch(`/api/user/${userId}`);
    const user = fetch.data;

    if(userId === "error" || fetch.error) return (<h1>Something went wrong!</h1>);

    return ( fetch.loading ? <h1>Loading...</h1> :
      <div className="className=sm:p-8 px-4 py-8 w-full bg-[#FFFBE9] min-h-[calc(100vh-73px)]">
          <div id="follower-data">
              <ButtonGroup variant="contained" aria-label="outlined primary button group">
                  <Button onClick={() => {setFollowersIsOpen(true);}}>Followers</Button>
                  <Button onClick={() => setFollowedIsOpen(true)}>Followed</Button>
              </ButtonGroup>
          </div>
          <Modal id="followersModal" open={followersIsOpen} onClose={() => setFollowersIsOpen(false)}></Modal>
          <Modal id="followedModal" open={followedIsOpen} onClose={() => setFollowedIsOpen(false)}></Modal>
      </div>
    );
}

export default UserProfile;