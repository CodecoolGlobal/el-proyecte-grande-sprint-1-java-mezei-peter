import React, { useState, useEffect } from "react";

const BitResponse = ({responses}) => {
    return ( 
        <>
            {responses.map(response => <div key={response.bitResponseId}>
                <div>
                    {response.posterUserName}
                </div>
                <div>
                    {response.posterUserName}
                </div>
                <div>
                    {response.posterUserId}
                </div>
                <div>
                    {response.bitResponseContent}
                </div>
                <div>
                    {response.isEdited}
                </div>
            </div>)}
            <button>More</button>
        </>
     );
}
 
export default BitResponse;