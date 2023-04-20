import {useState, useEffect, useContext} from "react";
import BitCard from "./BitCard.jsx";
import { UserContext } from "../contexts/UserContext";

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
        }
    })).json();
}

function BitFeed() {
    const [feedBits, setFeedBits] = useState(null);
    const [loading, setLoading] = useState(true);
    const [user, setUser] = useState(null);

    const userId = window.localStorage.getItem("userId")
    const token = window.localStorage.getItem("token");

    const handleDelete = async (bitId) => {
        const res = deleteBit(bitId, userId, token);

        console.log(res)
    }

    useEffect(() => {
        console.log(userId);
        console.log(token);
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


    }, []);

    useEffect(() => {
        const getUser = async (userId, token) => {
            try {
                setLoading(true)
                const user = await fetchCurrentUser(userId, token);
                
                console.log(user);

                setUser(user);
            } catch(e) {
                console.log(e);
            } finally {
                setLoading(false);
            }
        }

        getUser();

    }, []);

    if (loading) {
        return <div className="className=sm:p-8 px-4 py-8 w-full bg-[#FFFBE9] min-h-[calc(100vh-73px)]">LOADING</div>
    }
    return (
        <>
            <div className="className=sm:p-8 px-4 py-8 w-full bg-[#FFFBE9] min-h-[calc(100vh-73px)]">
                {feedBits.map(bit => <BitCard bit={bit} key={bit.bitId} isAdmin={user.isAdmin} handleDelete={handleDelete}/>)}

            </div>
        </>
    );
}

export default BitFeed;
