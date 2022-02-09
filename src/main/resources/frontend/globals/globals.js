let domain = "";

async function logout(){
    await fetch("http://localhost:7000/api/logout", {
        method: "DELETE"
    })

    window.location.href = "../";
}
