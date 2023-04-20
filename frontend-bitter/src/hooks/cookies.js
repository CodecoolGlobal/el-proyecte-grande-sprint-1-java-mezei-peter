export function useUserIdCookie(localStorage) {
    return localStorage.getItem("userId");
}

export function useJwtToken(localStorage) {
    return localStorage.getItem("token");
}