export default function useUserIdCookie(localStorage) {
    return localStorage.getItem("userId");
}