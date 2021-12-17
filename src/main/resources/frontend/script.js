window.addEventListener("load", async () => {
    let response = await fetch("http://localhost:7000/api/check-session");
    let result = await response.json();

    if(result.successful){
        window.location.href = `./${result.data.role.toLowerCase()}-dashboard`;
    }
})

async function login(e){
    e.preventDefault();

    let usernameInputElem = document.getElementById("username-input");
    let passwordInputElem = document.getElementById("password-input");

    let response = await fetch("http://localhost:7000/api/login", {
        method: "POST",
        body: JSON.stringify({
            username: usernameInputElem.value,
            password: passwordInputElem.value
        })
    })

    let result = await response.json();

    if(result.successful){
        window.location.href = `./${result.data.role.toLowerCase()}-dashboard`;
    }
}