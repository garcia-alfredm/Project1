window.onload = async () => {
    let response = await fetch("http://localhost:7000/api/check-session");
    console.log(response);
    let result = await response.json();
    console.log(result);

    //go to login if no session is found
    if(!result.successful){
        window.location.href = "../";
    }

    //got to manager dashboard if role is manager
    if(result.data.role === "MANAGER"){
        window.location.href = "../manager-dashboard";
    }

    let usernameElem = document.createElement("h2");
    // if i change json, can get user name, last name etc
    usernameElem.innerText = result.data.username;

    let roleElem = document.createElement("h3");
    roleElem.innerText = result.data.role;

    let userInfo = document.getElementById("user-info");
    userInfo.appendChild(usernameElem);
    userInfo.appendChild(roleElem);
}