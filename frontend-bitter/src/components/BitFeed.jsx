import {useState, useEffect} from "react";
import BitCard from "./BitCard.jsx";

function BitFeed() {
    const [user, setuser] = useState('abc');
    const [feedBits, setFeedBits] = useState(null);
    const [loading, setLoading] = useState(true);
    useEffect(() => {
        const abortController = new AbortController();
        const abortFetch = () => {
            // TODO: we might get stuck in the "loading" state here
            // Maybe a "something went wrong" state?
            abortController.abort()
        };
        setLoading(true);
        fetch(`/bit/feed/${user}`, { signal: abortController.signal })
            .then(response => {
                if (response.status === 200) {
                    return response.json()
                } else {
                    // now what?
                }
            })
            .then(data => {
                console.log(data)
                setFeedBits(data)
                setLoading(false)
            });

        return abortFetch;
    }, [user]);

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
