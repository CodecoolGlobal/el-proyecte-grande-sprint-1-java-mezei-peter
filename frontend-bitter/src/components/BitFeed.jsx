import {useState, useEffect} from "react";
import BitCard from "./BitCard.jsx";

function BitFeed() {
    const [feedBits, setFeedBits] = useState(null);
    const [loading, setLoading] = useState(true);
    useEffect(() => {
        fetch("/api/bit/feed/abc")
            .then(response => response.json())
            .then(data => {
                console.log(data)
                setFeedBits(data)
                setLoading(false)
            });


    }, []);

    if (loading) {
        return <div className="className=sm:p-8 px-4 py-8 w-full bg-[#FFFBE9] min-h-[calc(100vh-73px)]">LOADING</div>
    }
    return (
        <>
            <div className="className=sm:p-8 px-4 py-8 w-full bg-[#FFFBE9] min-h-[calc(100vh-73px)]">
                {feedBits.map(bit => <BitCard bit={bit} key={bit.bitId}/>)}

            </div>
        </>
    );
}

export default BitFeed;
