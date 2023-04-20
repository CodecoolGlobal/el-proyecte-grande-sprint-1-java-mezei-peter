import Button from "@mui/material/Button";

function FollowUser({userId}) {
    const follow = async () => {
        const followerId = window.localStorage.getItem("userId");
        const response = await fetch(`/api/user/${followerId}/follow/${userId}`, {
            method: "PUT",
            headers: {
                'Authorization': 'Bearer ' + window.localStorage.getItem("token")
            }
        });
        console.log("Follow user status: " + response.status);
    }

    return (
        <Button
            sx={{
                boxShadow: "4px 4px black",
                color: "black"
            }}
            onClick={follow}
        >FOLLOW
        </Button>
    );
}

export default FollowUser;