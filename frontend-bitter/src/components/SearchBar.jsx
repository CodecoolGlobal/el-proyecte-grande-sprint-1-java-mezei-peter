import React, {useState, useEffect, useRef} from 'react';
import {Link} from "react-router-dom";

const SearchBar = () => {
    const [searchTerm, setSearchTerm] = useState('');
    const [searchResult, setSearchResult] = useState([]);
    const [hiddenResults, setHiddenResults] = useState(true);
    const isMouseEntered = useRef(false);

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

    const handleExitedClick = () => {
        setHiddenResults(true);
    }

    const determineInvisibleDivClass = () => {
        const base = "z-10 absolute top-0 left-0 w-screen h-screen";
        if (hiddenResults) {
            return base + " hidden";
        }
        return base;
    }

    return (
        <>
            <div className={determineInvisibleDivClass()}
                 onClick={() => handleExitedClick()}></div>
            <form className={"flex flex-col h-16 z-20"} onSubmit={ev => ev.preventDefault()}
                  onMouseLeave={() => isMouseEntered.current = false}
                  onMouseEnter={() => isMouseEntered.current = true}
                  onClick={() => setHiddenResults(false)}>
                <input placeholder="Search for a user"
                       className="bg-white border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-[#222328]
                   focus:border-[#222328] outline-none block w-full p-3"
                       type="text" value={searchTerm} onChange={handleInputChange}/>
                <div className={"overflow-y-visible bg-white border-x-2"}>
                    {Array.isArray(searchResult) && searchResult.map((user, index) => {
                            const isEven = index % 2 === 0;
                            const backgroundColor = isEven ? "bg-[#f9f9f9]" : "bg-white";
                            const isHidden = hiddenResults ? "hidden" : "";
                            return (
                                <Link
                                    to={`/user/${user?.userId ?? "error"}`}
                                    key={user.userId}
                                >
                                    <div
                                        className={`border-b-2 ${backgroundColor} hover:bg-[#FFFBE9] ${isHidden}`}>{user.username}</div>
                                </Link>
                            );
                        }
                    )}
                </div>
            </form>
        </>

    );
};

export default SearchBar;