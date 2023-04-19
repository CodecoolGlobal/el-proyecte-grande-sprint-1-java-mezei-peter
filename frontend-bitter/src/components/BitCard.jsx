import React, { useState, useEffect } from "react";
import BitResponse from "./BitResponses";

const fetchResponses = async (id, token) => {
    return await (await fetch(`/api/response/${id}`, {headers: {
        Authorization: `Bearer ${token}`
    }})).json();
  };

function BitCard({bit}) {

    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(true);

    const userId = window.localStorage.getItem("userId")
    const token = window.localStorage.getItem("token");

    useEffect(() => {
        const controller = new AbortController()
        const getResponses = async() => {
            try {
              const responses = await fetchResponses(bit.bitId, token)

              console.log(responses)

              setData(responses)
              setLoading(false)

            } catch (err) {
                if(err.name !== "AbortError") {
                    throw err
                }
            }
        }
    
        getResponses();
    
      }, []);

    if (loading) {
        return <div>loading</div>
    }

    return (
        <>
            <div className="BitCard">
                <div className="PictureContainer">
                    <img src={""} alt="profilePicture"/>
                </div>
                <div className="ContentContainer">
                    <div className="CardPostData">
                        <div className="w-7 h-7 rounded-full object-cover bg-green-700 flex justify-center items-center text-white text-xs font-bold">{bit.userDTO.userName[0]}</div>
                        <p className="text-white text-sm">{bit.userDTO.userName}</p>
                        <span className="CardPostUserName">{bit.userDTO.userName}</span>
                        <span className="CardPostTime">{bit.dateOfCreation}</span>
                    </div>
                    <div className="CardContent">{bit.content}</div>
                    <div className="CardStats">
                        <button className="CardLikes">LIKE</button>
                        <button className="CardComments">COMMENTS</button>
                        <div>{data.map(response => <div key={response.bitResponseId}><BitResponse responses={data} /></div>)}</div>
                    </div>
                </div>
            </div>
        </>
    );
}

export default BitCard;
