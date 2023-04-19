import React, { useState, useEffect } from "react";

const BitResponse = ({responses, loading, hideComments}) => {

    if(loading) {
        return <div>Loading</div>
    }

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
            <button onClick={hideComments}>Hide COMMENTS</button>
        </>
     );
}
 
export default BitResponse;