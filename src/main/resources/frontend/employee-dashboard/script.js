    {/*
        <div class="request-container border border-primary">
            <div class="display-amount">Amount</div>
            <div class="display-desc">Description</div>
            <div class="display-submitted-date">Submitted Date</div>
            <div class="display-resolved-date">Resolved Date</div>
            <div class="display-resolver">Resolver</div>
            <div class="status">Status</div>
            <div class="type">Type</div>
        </div>
    */}

let userId;
//current domain
let domain = "";

window.onload = async () => {
    let response = await fetch("http://localhost:7000/api/check-session");
    let result = await response.json();

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

    /* Display logged in users name */
    let userInfo = document.getElementById("user-info");
    userInfo.appendChild(usernameElem);
    userInfo.appendChild(roleElem);

    //get userId
    userId = await result.data.userId;

    //populate reimbursement requests
    populateRequests();
}

async function populateRequests(){
    //fetch users requests, default method GET
    let response = await fetch(`${domain}/reimbursements/${userId}`);
    
    //returns array
    let result = await response.json();

    //clear container
    let displayRequestsContainer = document.getElementById("display-requests-container");
    displayRequestsContainer.innerHTML = "";

    //loop thur each request, create dom elements
    result.forEach(reimb => {
        //create request-container elem
        let requestElem = document.createElement("div");
        requestElem.className = "request-container border border-primary";

        requestElem.innerHTML = `
        <div class="display-amount">${reimb.amount}</div>
            <div class="display-desc">${reimb.description}</div>
            <div class="display-submitted-date">${reimb.submitted} Date</div>
            <div class="display-resolved-date">${reimb.resolved} Date</div>
            <div class="display-resolver">${reimb.resolver}</div>
            <div class="status">${reimb.status}</div>
            <div class="type">${reimb.typeId}</div>
        </div>
        `;

        //append to container
        displayRequestsContainer.appendChild(requestElem);

    });
}

async function createReimb(e){
    e.preventDefault();

    //get values from forms input
    let amountInputElem = document.getElementById("amount-input");
    let amount_ = Number(amountInputElem.value);
    let descInputElem = document.getElementById("desc-input");
    let desc = descInputElem.value;
    let typeInputElem = document.getElementById("type-input");
    let type_ = Number(typeInputElem.value);

    await fetch(`${domain}/reimbursements`, {
        method: "POST",
        body: JSON.stringify({
            amount: amount_,
            //submitted: 
            //description
            description: desc,
            //author
            author: userId,
            //status
            statusId: 1,
            //type
            typeId: type_
        })
    })
    amountInputElem = "";
    descInputElem = ""; 
    typeInputElem = "";

    populateRequests()
}