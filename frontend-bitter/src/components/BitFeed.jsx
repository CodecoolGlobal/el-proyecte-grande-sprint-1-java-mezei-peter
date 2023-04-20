import {useState, useEffect, useContext} from "react";
import BitCard from "./BitCard.jsx";
import { GlobalContext } from "../contexts/GlobalContext.jsx";
import PostBit from "./PostBit.jsx";
import MyProfileButton from "./MyProfileButton.jsx";

const fetchCurrentUser = async (userId, token) => {
    return await (await fetch(`/api/user/${userId}`, {
        headers: {
            Authorization: `Bearer ${token}`
        },
    })).json()
}

const deleteBit = async (bitId, token, userId) => {
    return await (await fetch(`/api/bit/delete/${userId}/${bitId}`, {
        headers: {
            Authorization: `Bearer ${token}`
        },
        method: "DELETE"
    })).json();
}

function BitFeed() {
    const [feedBits, setFeedBits] = useState(null);
    const [loading, setLoading] = useState(true);
    const [user, setUser] = useState(null);

//    const {userId, setUserID} = useContext(GlobalContext);
    const userId = window.localStorage.getItem("userId")
    const token = window.localStorage.getItem("token");

    const handleDelete = async (bitId, index) => {
        const res = deleteBit(bitId, token, userId);

        const newBits = [...feedBits]

        newBits.splice(index, 1);
        
        setFeedBits(newBits);
        console.log(res)
    }


    const fetchBitFeed = async () => {
        fetch(`/api/bit/feed/${userId}`, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        })
            .then(response => response.json())
            .then(data => {
                console.log(data)
                setFeedBits(data)
                setLoading(false)
            });
    }

    useEffect(() => {
        console.log(userId);
        console.log(token);
        fetchBitFeed()
    }, []);

    useEffect(() => {
        setLoading(true)
        const getUser = async () => {
            console.log(userId)
            try {
                const user = await fetchCurrentUser(userId, token);

                console.log(user);

                setUser(user);
                setLoading(false)
            } catch(e) {
                console.log(e);
            }
        }

        getUser();

    }, []);

    if (loading || user === null || feedBits === null) {
        return <div className="className=sm:p-8 px-4 py-8 w-full bg-[#FFFBE9] min-h-[calc(100vh-73px)]">LOADING</div>
    }
    return (
        <>
            <div className="className=sm:p-8 px-4 py-8 w-full bg-[#FFFBE9] min-h-[calc(100vh-73px)]">
            <MyProfileButton className=""/>
            <PostBit fetchBitFeed={fetchBitFeed} />


                {feedBits.map((bit, index) => <BitCard bit={bit} key={bit.bitId} isAdmin={user.isAdmin} handleDelete={handleDelete} index={index}/>)}
            </div>
        </>
    );
}

export default BitFeed;
