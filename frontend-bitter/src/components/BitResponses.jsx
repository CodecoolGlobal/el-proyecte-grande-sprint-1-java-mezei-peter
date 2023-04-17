const BitResponse = ({responses, fetchNext}) => {
    return ( 
        <div>
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
            <button onClick={() => fetchNext()}>More</button>
        </div>
     );
}
 
export default BitResponse;{respons
    es}