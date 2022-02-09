let domain = "";

async function logout(){
    await fetch(`${domain}/api/logout`, {
        method: "DELETE"
    })

    window.location.href = "../";
}
