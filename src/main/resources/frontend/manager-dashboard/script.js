    /* 
    <div class="reimbursement-request" id="43">
        <div class="user-details">
            <span class="first-name">First Name</span>
            <span class="last-name">Last Name</span>
            <span class="username">username</span>
            <span class="email">email</span>
        </div>
        <div class="reimburse-details">
            <span class="description">Description</span>
            <span class="amount">Amount</span>
            <span class="submitted">Submitted</span>
            <span class="resolved">Resolved</span>
            <span class="status">Status</span>
            <span class="type">Type</span>
        </div>
        <div class="resolver-details">
            <span class="resolver-first-name">Resolver First Name</span>
            <span class="resolver-last-name">Resolver Last Name</span>
            <span class="resolver-email">Resolver Email</span>
        </div>
        <div class="manager-actions">
            <!-- button uses reimbursement id to target -->
            <button class="approve" id="approve-btn-43">Approve</button>
            <button class="deny" id="deny-btn-43">Deny</button>
        </div>
    </div> 
    */

let managerId;
//current domain
//let domain = "";
//let domain = "http://54.172.148.216:7000";

window.onload = async () => {
    let response = await fetch(`${domain}/api/check-session`);
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

    let userInfo = document.getElementById("manager-info");
    userInfo.appendChild(usernameElem);
    userInfo.appendChild(roleElem);

    //get userId
    managerId = await result.data.userId;

    //populate reimbursement requests
    populateReimbursements();
}

async function populateReimbursements(){
    /* default fetch method GET */
    let response = await fetch(`${domain}/reimbursements`);

    /* returns array of responses */
    let result = await response.json();

    /* clear container of all requests */
    let displayReimbursementsContainer = document.getElementById("display-reimbursements");
    displayReimbursementsContainer.innerHTML = "";

    /* loop thru each request, creating formateed dom elements */
    result.forEach(reimb => {
        /* create reimbursement request container */
        let requestElem = document.createElement("div");
        requestElem.className = "reimbursement-request border border-primary";
        /* assign reimbursement request id */
        requestElem.id = reimb.id;

        /* inner html has containers displaying formatted data */
        requestElem.innerHTML = `
            <div class="user-details">
                <span class="fullname-name">${reimb.authorFirstName} ${reimb.authorLastName}</span>
                <br>
                <span class="username">Employee Username: ${reimb.authorUserName}</span>
                <span class="email">Employee Email: ${reimb.authorEmail}</span>
            </div>
            <div class="reimburse-details">
                <span class="description">Description: ${reimb.description}</span>
                <span class="amount">Amount Requested: ${reimb.amount}</span>
                <span class="type">Type:${reimb.type}</span>
                <span class="submitted"> Date Submittted: ${reimb.submitted}</span>
                ${reimb.resolved == null ? '' : `<span class="resolved">Date Resolved: ${reimb.resolved}</span>`}
                <span class="status">Status: ${reimb.status}</span>
            </div>
            ${reimb.status == 'APPROVED' || reimb.status == 'DENIED' ? '' : `
                <div class="manager-actions">
                    <button class="btn btn-primary approve" id="approve-btn-${reimb.id}" onclick="approveRequest(event)">Approve</button>
                    <button class="btn btn-primary deny" id="deny-btn-${reimb.id}" onclick="denyRequest(event)">Deny</button>
                </div>
            `}
        `;

        displayReimbursementsContainer.appendChild(requestElem);
    });
}

async function approveRequest(e){
    e.preventDefault();

    /* target class contains approval or denials */
    let id = e.target.id.slice("approve-btn-".length, e.target.id.length);
    console.log(id);

    /* send request to update reimbursement */
    await fetch(`${domain}/reimbursements/${id}`, {
        method: "PATCH",
        body: JSON.stringify({
            resolverId: managerId,
            statusId: 2
        })
    })
    populateReimbursements();
}

async function denyRequest(e){
    e.preventDefault();

    let id = e.target.id.slice("deny-btn-".length, e.target.id.length);
    console.log(id);

    await fetch(`${domain}/reimbursements/${id}`, {
        method: "PATCH",
        body: JSON.stringify({
            resolverId: managerId,
            statusId: 3
        })
    })
    populateReimbursements();
}

function filterReimbursements(e, filterStatus){
    e.preventDefault;

    //populateReimbursements();

    /* array of elements with class status */
    let filter = Array.from(document.getElementsByClassName("status"));
    console.log(filter);

    /* for each element of class status */
    filter.forEach(elem => {
        /*  */
        if(elem.innerText != "Status: " + filterStatus){
            elem.parentNode.parentNode.replaceChildren("");
        }
    })
}