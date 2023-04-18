import Button from "@mui/material/Button";
import {useState} from "react";
import useUserIdCookie from "../hooks/cookies.js";

function PostBit() {
    const [newBit, setNewBit] = useState("");

    const postBit = async content => {
        const requestBody = {userId: useUserIdCookie(window.localStorage), bitContent: content};
        const response = await fetch("/api/bit/add", {
            method: "POST",
            headers: {'Content-type': "application/json"},
            body: JSON.stringify(requestBody)
        });
        const data = await response.text();
        console.log(data);
    };

    const handlePostBit = () => {
        const bitContent = newBit.toString();
        setNewBit("");
        postBit(bitContent);
    };

    return (
        <div className="bg-[#FFFBE9] w-full pt-6">
            <div className="w-4/5 m-auto flex flex-col justify-between p-10 border-x-4 border-black">
                <textarea className="resize-none h-32 p-2"
                          placeholder="What's on your mind?"
                          value={newBit}
                          onChange={event => setNewBit(event.target.value)}
                ></textarea>
                <Button
                    sx={{
                        backgroundColor: "#FFFBE9",
                        color: "black",
                        border: "2px solid #262018",
                        margin: "auto",
                        width: "60%",
                        ":hover": {
                            border: "2px solid #262018",
                            bgcolor: "whitesmoke",
                            color: "black",
                        },
                    }}
                    onClick={handlePostBit}
                >Post
                </Button>
            </div>
        </div>
    );
}

export default PostBit;