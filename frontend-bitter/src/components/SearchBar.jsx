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
        <form className={"flex flex-col h-16"} onSubmit={ev => ev.preventDefault()}>
            <input placeholder="Search for a user"
                   className="bg-white border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-[#222328]
                   focus:border-[#222328] outline-none block w-full p-3"
                   type="text" value={searchTerm} onChange={handleInputChange}/>
            <div className={"overflow-y-visible z-10 bg-white border-x-2"}>
                {Array.isArray(searchResult) && searchResult.map((result, index) => {
                        const isEven = index % 2 == 0;
                        const backgroundColor = isEven ? "bg-[#f9f9f9]" : "bg-white";
                        return (
                            <Link
                                to="/my-profile"
                                key={result.userId}
                            >
                                <div className={`border-b-2 ${backgroundColor} hover:bg-[#FFFBE9]`}>{result.username}</div>
                            </Link>
                        );
                    }
                )}
            </div>
        </form>
    );
};

export default SearchBar;