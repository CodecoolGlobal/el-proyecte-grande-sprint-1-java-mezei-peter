import Button from "@mui/material/Button";

function PostBit() {
    return (
        <div className="bg-[#FFFBE9] w-full pt-6">
            <div className="w-4/5 m-auto flex flex-col justify-between p-10 border-x-4 border-black">
                <textarea className="resize-none h-32 p-2" placeholder="What's on your mind?"></textarea>
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
                    }}>
                    Post</Button>
            </div>
        </div>
    );
}

export default PostBit;