import React, { useState, useEffect } from "react";
import BitResponse from "./BitResponses";
import BitResponseForm from "./BitResponseForm";
import { json } from "react-router-dom";

const fetchResponses = async (id, token) => {
  return await (
    await fetch(`/api/response/${id}`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
  ).json();
};

const postResponse = async (response, token) =>  {
    return await (await fetch(`/api/response`, {
        "method" : "POST",
        headers : {
            Authorization: `Bearer ${token}`,
            "Content-Type" : "application/json"
        },
        "body" : JSON.stringify(response)
    })).json();
}

function BitCard({ bit, isAdmin, handleDelete }) {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [visiable, setVisiable] = useState(false);

  const userId = window.localStorage.getItem("userId");
  const token = window.localStorage.getItem("token");

  const hideComments = () => {
    setVisiable(false);
  };

  const submitResponse = async (content) => {
    const bitResponse = {
        "bitId" : bit.bitId,
        "userId" : userId,
        "bitResponseContent" : content
    }

    const res = await postResponse(bitResponse, token);

    console.log(res)

    setData([...data, res])
  } 

  useEffect(() => {
    console.log("useeffect ran")
    const controller = new AbortController();
    const getResponses = async () => {
      try {
        const responses = await fetchResponses(bit.bitId, token);

        console.log(responses);

        setData(responses);
        setLoading(false);
      } catch (err) {
        if (err.name !== "AbortError") {
          throw err;
        }
      }
    };

    getResponses();
  }, []);

  return (
    <>
      <div className="BitCard">
        <div className="PictureContainer">
          <img src={""} alt="profilePicture" />
        </div>
        <div className="ContentContainer">
          <div className="CardPostData">
            <div className="w-7 h-7 rounded-full object-cover bg-green-700 flex justify-center items-center text-white text-xs font-bold">
              {bit.userDTO.userName[0]}
            </div>
            <p className="text-white text-sm">{bit.userDTO.userName}</p>
            <span className="CardPostUserName">{bit.userDTO.userName}</span>
            <span className="CardPostTime">{bit.dateOfCreation}</span>
          </div>
          <div className="CardContent">{bit.content}</div>
          <div className="CardStats">
            <button className="CardLikes">LIKE</button>
            {isAdmin ? <button onClick={() => handleDelete(bit.bitId)}>Delete</button> : null}

            {visiable ? (
              <div>
                {data.map((response) => (
                    <div key={response.bitResponseId}>
                    <BitResponse
                      response={response}
                      loading={loading}
                      hideComments={() => hideComments()}
                    />
                    </div>
                ))}
              </div>
            ) : (
              <button
                onClick={() => setVisiable(true)}
                className="CardComments"
              >
                Show COMMENTS
              </button>
            )}
            <BitResponseForm handleSubmit={submitResponse}/>
          </div>
        </div>
      </div>
    </>
  );
}

export default BitCard;
