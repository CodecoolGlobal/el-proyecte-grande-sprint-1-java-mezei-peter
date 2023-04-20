import { useEffect, useState } from "react"
import axios from "axios"


function useFetch(url, method, data){
    const determineMethod = (url, method, data) => {
        if(!method || method === "get") return axios.get(url);
        else if (method === "delete") return axios.delete(url);
        else if (method === "put") return axios.put(url, data);
        else if (method === "patch") return axios.put(url, data);
    }

    const [data,setData] = useState(null)
    const [error,setError] = useState(null)
    const [loading,setLoading] = useState(false)

    useEffect(() => {
        (
            async function () {
                try {
                    setLoading(true);
                    const response = await determineMethod(url, method, data);
                    setData(response.data);
                } catch (err) {
                    setError(err)
                } finally {
                    setLoading(false)
                }
            }
        )()
    }, [url])

    return { data, error, loading }
}

export default useFetch;