import {useState, useEffect, useContext} from "react";
import BitCard from "./BitCard.jsx";
import { GlobalContext } from "../contexts/GlobalContext.jsx";
import PostBit from "./PostBit.jsx";

function BitFeed() {
    const [feedBits, setFeedBits] = useState(null);
    const [loading, setLoading] = useState(true);

//    const {userId, setUserID} = useContext(GlobalContext);
    const userId = window.localStorage.getItem("userId")
    const token = window.localStorage.getItem("token");

    const fetchBitFeed = async () => {
        fetch(`/api/bit/feed/${userId}`, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        })
            .then(response => response.json())
            .then(data => {
                setFeedBits(data)
                setLoading(false)
            });
    }

    useEffect(() => {
        console.log(userId);
        console.log(token);
        fetchBitFeed()
    }, []);

    if (loading) {
        return <div className="className=sm:p-8 px-4 py-8 w-full bg-[#FFFBE9] min-h-[calc(100vh-73px)]">LOADING</div>
    }
    return (
        <>
            <PostBit fetchBitFeed={fetchBitFeed} />
            <div className="className=sm:p-8 px-4 py-8 w-full bg-[#FFFBE9] min-h-[calc(100vh-73px)]">
                {feedBits.map(bit => <BitCard bit={bit} key={bit.bitId}/>)}
            </div>
        </>
    );
}

export default BitFeed;
