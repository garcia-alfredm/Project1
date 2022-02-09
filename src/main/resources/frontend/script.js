//let domain = "http://54.172.148.216:7000";
//let domain = ""

window.addEventListener("load", async () => {
    let response = await fetch(`${domain}/api/check-session`);
    let result = await response.json();

    if(result.successful){
        window.location.href = `./${result.data.role.toLowerCase()}-dashboard`;
    }

    let marker = document.createElement("h3");
    let body = document.getElementsByTagName("body");
    body.appendChild(marker);
})

async function login(e){
    e.preventDefault();

    let usernameInputElem = document.getElementById("username-input");
    let passwordInputElem = document.getElementById("password-input");

    let response = await fetch(`${domain}/api/login`, {
        method: "POST",
        body: JSON.stringify({
            username: usernameInputElem.value,
            password: passwordInputElem.value
        })
    })

    let result = await response.json();

    if(result.successful){
        window.location.href = `./${result.data.role.toLowerCase()}-dashboard`;
    }else{
        location.reload();
    }
}