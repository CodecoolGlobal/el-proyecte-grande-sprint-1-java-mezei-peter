import { Button } from "@mui/material";
import { useState } from "react";

const BitResponseForm = ({ handleSubmit }) => {
  const [content, setContent] = useState("");

  return (
    <>
        <div className="bg-white w-full py-2">
            <div className="max-w-4xl mx-auto flex flex-col justify-between px-8">
    <textarea
        className="resize-none border-2 border-gray-300 p-2 rounded-lg"
        onChange={(event) => {
            setContent(event.target.value);
        }}
        value={content}
        placeholder="Write your response..."
    ></textarea>
                <Button
                    className="mt-4 bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
                    onClick={() => {
                        handleSubmit(content);
                        setContent("");
                    }}
                >
                    Post Response
                </Button>
            </div>
        </div>


    </>
  );
};

export default BitResponseForm;
