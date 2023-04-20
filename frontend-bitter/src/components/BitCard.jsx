import React, { useState, useEffect } from "react";
import BitResponse from "./BitResponses";
import BitResponseForm from "./BitResponseForm";
import { json } from "react-router-dom";
import { Button } from "@mui/material";

const fetchResponses = async (id, token) => {
  return await (
    await fetch(`/api/response/${id}`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
  ).json();
};

const postResponse = async (response, token) => {
  return await (
    await fetch(`/api/response`, {
      method: "POST",
      headers: {
        Authorization: `Bearer ${token}`,
        "Content-Type": "application/json",
      },
      body: JSON.stringify(response),
    })
  ).json();
};

function BitCard({ bit, isAdmin, handleDelete, index }) {
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
      bitId: bit.bitId,
      userId: userId,
      bitResponseContent: content,
    };

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
          <div className="bg-white rounded-lg overflow-hidden max-w-3xl my-4 mx-auto border border-gray-300">
              <div className="flex p-4">
                  <div className="mr-4">
                      <div className="w-16 h-16 rounded-full bg-green-700 flex justify-center items-center text-white text-2xl font-bold">
                          {bit.userDTO.userName[0]}
                      </div>
                  </div>
                  <div>
                      <div className="flex items-center mb-2">
                          <p className="text-gray-700 font-bold text-xm mr-2">{bit.userDTO.userName}</p>
                          <span className="text-gray-500 text-xs">{bit.dateOfCreation}</span>
                      </div>
                      <div className="text-gray-800 font-normal text-xl break-all mb-4">{bit.content}</div>
                      <div className="inline-block justify-between">
                          <button className="text-gray-500 font-medium text-xs border border-gray-300 rounded-lg px-4 py-2 hover:bg-gray-500 hover:text-white transition-all duration-300">
                              LIKE
                          </button>
                          {isAdmin ? <button className="text-gray-500 font-medium text-xs border border-gray-300 rounded-lg px-4 py-2 hover:bg-gray-500 hover:text-white transition-all duration-300" onClick={() => handleDelete(bit.bitId, index)}>Delete</button> : null}
                          {visiable ? (
                              <div>
                                  {data.map((response) => (
                                      <div key={response.bitResponseId}>
                                          <BitResponse response={response} loading={loading} hideComments={() => hideComments()} />
                                      </div>
                                    
                                  ))}
                                  <button onClick={() => setVisiable(false)} className="border-gray-300 text-gray-500 font-medium text-xs border rounded-lg px-4 py-2 hover:bg-gray-500 hover:text-white transition-all duration-300">
                                  Hide COMMENTS
                              </button>
                              </div>
                          ) : (
                              <button onClick={() => setVisiable(true)} className="border-gray-300 text-gray-500 font-medium text-xs border rounded-lg px-4 py-2 hover:bg-gray-500 hover:text-white transition-all duration-300">
                                  Show COMMENTS
                              </button>
                          )}
                          <div>
                              <BitResponseForm handleSubmit={submitResponse} />
                          </div>
                      </div>
                  </div>
              </div>
          </div>
      </>
  );
}

export default BitCard;
