window.onload = async () => {
    let response = await fetch("http://localhost:7000/api/check-session");
    let result = await response.json();

    //go to login if no session is found
    if(!result.successful){
        window.location.href = "../";
    }

    //got to manager dashboard if role is manager
    if(result.data.role === "EMPLOYEE"){
        window.location.href = "../employee-dashboard";
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