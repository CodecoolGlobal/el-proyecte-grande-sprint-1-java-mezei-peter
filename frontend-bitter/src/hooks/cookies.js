function useUserIdCookie(localStorage) {
    return localStorage.getItem("userId");
}

export default useUserIdCookie;