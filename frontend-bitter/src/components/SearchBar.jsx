import React, {useState, useEffect} from 'react';

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
            <input placeholder="Search for a user" className="bg-white border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-[#222328] focus:border-[#222328] outline-none block w-full p-3" type="text" value={searchTerm} onChange={handleInputChange}/>
            {Array.isArray(searchResult) && searchResult.map((result) => (
                <div key={result.userId}>{result.username}</div>
            ))}
        </form>
    );
};

export default SearchBar;