import { useEffect, useState } from "react"
import { useJwtToken } from "./cookies.js";
import axios from "axios"


function useFetch(url, method, dataToSend){
    const determineMethod = (url, method, dataToSend, configToSend) => {
        if(!method || method === "get") return axios.get(url, configToSend);
        else if (method === "delete") return axios.delete(url, configToSend);
        else if (method === "put") return axios.put(url, dataToSend, configToSend);
        else if (method === "patch") return axios.patch(url, dataToSend, configToSend);
        else if (method === "post") return axios.post(url, dataToSend, configToSend);
        else console.log("invalid request method");
    }

    const getConfig = () => {
        headers: {
            Authorization: `Bearer ${useJwtToken(localStorage)}`
        }
    };

    const [data,setData] = useState(null)
    const [error,setError] = useState(null)
    const [loading,setLoading] = useState(false)

    useEffect(() => {
        (
            async function () {
                try {
                    setLoading(true);
                    const response = await determineMethod(url, method, dataToSend, getConfig());
                    setData(response.data);
                } catch (err) {
                    setError(err)
                } finally {
                    setLoading(false)
                }
            }
        )()
    }, [url]);

    return { data, error, loading }
}

export default useFetch;