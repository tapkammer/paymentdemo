# Overview
This is a demo project to create banking accounts and perform transactions on the accounts.

# Main Technology Stack
* Java
* Spring MVC

# Pre-Requisites
In order to run the application, it is necessary JDK 17 and Docker.

# Running this Application
Start docker-compose:
````
docker-compose up --build
````

Start the application:
````
.\gradlew clean build bootRun
````

# Endpoints
Create account:
````
POST /accounts
{
    "document_number": "12345678900"
}
````

Retrieve account:
````
GET /accounts/:accountId
````

Create transaction:
````
POST /transactions
{
    "account_id": 1,
    "operation_type_id": 4,
    "amount": 123.45
}
````
