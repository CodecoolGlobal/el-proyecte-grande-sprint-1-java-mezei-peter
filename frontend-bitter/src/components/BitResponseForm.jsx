import { Button } from "@mui/material";
import { useState } from "react";

const BitResponseForm = ({handleSubmit}) => {

    const [content, setContent] = useState("")

    return ( <>
        <textarea onChange={(event) => {setContent(event.target.value)}} value={content}>

        </textarea>
        <Button onClick={() => {handleSubmit(content); setContent("")}}>Post Response</Button>
    </> );
}
 
export default BitResponseForm;