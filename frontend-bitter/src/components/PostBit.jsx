import Button from "@mui/material/Button";
import {useState} from "react";
import {useUserIdCookie} from "../hooks/cookies.js";

function PostBit({fetchBitFeed}) {
    const CHARACTER_LIMIT = 255;
    const [currentLength, setCurrentLength] = useState(0);
    const [newBit, setNewBit] = useState("");

    const postBit = async content => {
        const requestBody = {userId: useUserIdCookie(window.localStorage), bitContent: content};
        const response = await fetch("/api/bit/add", {
            method: "POST",
            headers: {
                'Content-type': 'application/json',
                'Authorization': `Bearer ${window.localStorage.getItem("token")}`,
            },
            body: JSON.stringify(requestBody)
        });
        const data = await response.text();
        console.log(data);
    };

    const handlePostBit = async () => {
        const bitContent = newBit.toString();
        setNewBit("");
        setCurrentLength(0);
        await postBit(bitContent);
        await fetchBitFeed();
    };

    const handleBitTextChange = event => {
        const text = event.target.value;
        const length = text.length;
        if (length > CHARACTER_LIMIT) {
            return;
        }
        setCurrentLength(text.length);
        setNewBit(text);
    }

    return (
        <div className="bg-[#FFFBE9] w-full pt-6">
            <div className="md:w-4/5 m-auto flex flex-col justify-between p-10">
                <textarea className="resize-none h-32 p-2 bg-white border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-[#222328] focus:border-[#222328]"
                          placeholder="What's on your mind?"
                          value={newBit}
                          onChange={event => handleBitTextChange(event)}
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
                <div className="text-end">{currentLength}/{CHARACTER_LIMIT}</div>
            </div>
        </div>
    );
}

export default PostBit;