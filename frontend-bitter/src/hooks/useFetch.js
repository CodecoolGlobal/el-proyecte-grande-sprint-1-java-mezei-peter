import { useEffect } from "react";

const useFetch = async (url) => {
  const [loading, setLoading] = useState(true);
  const [data, setData] = useState(null);
  const [searchText, setSearchText] = useState("");
  const [searchedResults, setSearchedResults] = useState(null);
  const [error, setError] = useState(null);

  const controller = new AbortController();

  useEffect(() => {
    const getPosts = async (signal) => {
      setLoading(true);

      try {
        const response = await (await fetch(url)).json();

        setData(response);
      } catch (e) {
        se;
      } finally {
        setLoading(false);
      }
    };

    getPosts(controller.signal);

    return () => signal.abort();
  }, [url]);

  return { data, loading, error };
};
