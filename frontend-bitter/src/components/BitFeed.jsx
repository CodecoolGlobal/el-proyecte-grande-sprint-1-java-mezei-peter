import {useState, useEffect} from "react";
import BitCard from "./BitCard.jsx";

function BitFeed() {
    const [feedBits, setFeedBits] = useState(null);
    const [loading, setLoading] = useState(true);
    useEffect(() => {
        fetch("/bit/feed/abc")
            .then(response => response.json())
            .then(data => {
                setFeedBits(data)
                setLoading(false)
            });


    }, []);

    if (loading) {
        return <div>LOADING</div>
    }
    return (
        <>
            <div className="BitFeed">
                {feedBits.map(bit => <BitCard bit={bit} key={bit.bitId}/>)}

            </div>
        </>
    );
}

export default BitFeed;
