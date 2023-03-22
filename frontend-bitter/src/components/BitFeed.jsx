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
        return <div className="App">LOADING</div>
    }
    return (
        <>
            <div className="BitFeed App">
                {feedBits.map(bit => <BitCard bit={bit} key={bit.bitId}/>)}

            </div>
        </>
    );
}

export default BitFeed;
