import { Button } from "@mui/material";
import { useState } from "react";

const BitResponseForm = ({ handleSubmit }) => {
  const [content, setContent] = useState("");

  return (
    <>
      <div className="bg-[#FFFBE9] w-2/5 pt-6">
        <div className="w-4/5 m-auto flex flex-col justify-between p-10">
          <textarea
            onChange={(event) => {
              setContent(event.target.value);
            }}
            value={content}
          ></textarea>
          <Button
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
