import { ClassNames } from "@emotion/react";
import React, { useState, useEffect } from "react";

const BitResponse = ({ response, loading, hideComments }) => {
  if (loading) {
    return <div>Loading</div>;
  }

  return (
    <>
      <div className="border-2 border-red-500 flex">
        <div>
          <div>{response.posterUserName}</div>
          <div>{response.dateOfPosting}</div>
        </div>
        <div>{response.bitResponseContent}</div>
      </div>
    </>
  );
};

export default BitResponse;
