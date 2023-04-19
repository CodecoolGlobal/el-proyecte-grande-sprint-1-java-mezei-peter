import React, { useState, useEffect } from "react";

const BitResponse = ({response, loading, hideComments}) => {

    if(loading) {
        return <div>Loading</div>
    }

    return ( 
        <>
                <div>
                    {response.bitResponseContent}
                </div>        
        </>
     );
}
 
export default BitResponse;

/*<div>
{response.posterUserName}
</div>
<div>
{response.posterUserName}
</div>
<div>
{response.posterUserId}
</div>

<div>
{response.isEdited}
</div>*/