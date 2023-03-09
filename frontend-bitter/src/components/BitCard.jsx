import React from "react";

function BitCard({bit}) {
    return (
        <>
            <div className="BitCard">
                <div className="PictureContainer">
                    <img src={} alt={"profilePicture"}/>
                </div>
                <div className="ContentContainer">
                    <div className="CardPostData">
                        <span className="CardPostUserName">{bit.generalUserDTO.userName}</span>
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
