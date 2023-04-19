import React, {useState} from 'react';

const SearchBar = () => {
    const [searchTerm, setSearchTerm] = useState('');
    const [searchResult, setSearchResult] = useState([]);

    const handleSearch = async (event) => {
        event.preventDefault();
        const response = await fetch(`/api/user/search/${searchTerm}`);
        const data = await response.json();
        const lowerSearchTerm = searchTerm.toLowerCase();
        const filteredResult = data.filter(
            (result) => result.username.toLowerCase().includes(lowerSearchTerm)
        );
        setSearchResult(filteredResult);
        console.log(searchResult);
    }

    const handleInputChange = (event) => {
        setSearchTerm(event.target.value);
    }

    return (
        <form onSubmit={handleSearch}>
            <input type="text" value={searchTerm} onChange={handleInputChange} />
            <button type="submit">Search</button>
            {searchResult.map((result) => (
                <div key={result.id}>{result.username}</div>
            ))}
        </form>
    );
};
export default SearchBar;

