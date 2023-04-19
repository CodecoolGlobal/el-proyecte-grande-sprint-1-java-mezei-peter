import React, {useState, useEffect} from 'react';
import {Link} from "react-router-dom";

const SearchBar = () => {
    const [searchTerm, setSearchTerm] = useState('');
    const [searchResult, setSearchResult] = useState([]);

    useEffect(() => {
        const fetchSearchResult = async () => {
            try {
                const response = await fetch(`/api/user/search?username=${searchTerm}`, {
                    method: 'GET',
                    headers: {
                        Authorization: `Bearer ${window.localStorage.getItem('token')}`,
                    },
                });

                const data = await response.json();
                console.log(data)
                setSearchResult(data);
            } catch (error) {
                console.error(error);
            }
        };

        if (searchTerm) {
            const timeoutId = setTimeout(fetchSearchResult, 500);
            return () => clearTimeout(timeoutId);
        }

        setSearchResult([]);
    }, [searchTerm]);

    const handleInputChange = (event) => {
        const {value} = event.target;
        setSearchTerm(value);
    };

    return (
        <form>
            <input type="text" value={searchTerm} onChange={handleInputChange}/>
            {Array.isArray(searchResult) && searchResult.map((result) => (
                <Link
                    to="/my-profile"
                    key={result.userId}
                >
                    <div>{result.username}</div>
                </Link>
            ))}
        </form>
    );
};

export default SearchBar;