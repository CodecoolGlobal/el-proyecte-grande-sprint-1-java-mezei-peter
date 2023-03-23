import React from "react";

function BitCard({bit}) {
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
                    </div>
                </div>
            </div>
        </>
    );
}

export default BitCard;
