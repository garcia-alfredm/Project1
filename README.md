# Project1
## Project Description
This is an employee reimbursement system. It is a full stack development, allowing the client side to
send requests to persist changes to the database.

## Technologies Used
* HTML/CSS with Javascript
* Java with JDBC
* Postgres SQL

## Features
* Users can log in to their account, use session checks to prevent subdomain navigation
* Employees can create reimbursement requests
* Managers can filter reimbursement requests by request status

## Getting Started
To download repository:

```git clone https://github.com/garcia-alfredm/Project1.git```

To set up enviornment variables:
* update .bashrc file to include aws credentials
```
export AWS_RDS_ENDPOINT='[location of aws ec2 domain]'
export RDS_USERNAME='[ec2 username]'
export RDS_PASSWORD='[ec2 password]'
```

* Run javalin server using IntelliJ
* use web browser to navigate to client side
```http://localhost:7000```

## Usage
Employees and Financial Managers will be able to login and access their account dashboard.

Once logged in, an employee will be able to create a reimbursement request and view the status of current
and past requests. 

Managers will be able to view employee requests, approve or deny them, and filter requests
by status


## Contributors
Alfred Garcia

## License
This project uses the license: 
