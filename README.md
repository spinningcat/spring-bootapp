# Wallet Application

## Modules

### Register

### Login

### Logout

### Wallet

### Transaction

## Description

<span style="font-size: 18px;">This app is designed for carrying
out basic wallet operations such as creating a wallet and retrieving
wallet information. Future enhancements may include transaction
features like withdrawals, deposits,, and approval - deny
mechanism.</span>

<span style="font-size: 18px; text-decoration: underline; color:red"> 
Project contains some parts to maintain more flexible and sustainable working
environment. It is also solid principles. You can find ib places like repository,
services, controller. Every part has its own job like seperating layers and that communicates
with each other. In the belo, you will find short explanation about folder structure.
</span>

<span style="font-size: 18px;">The project steams in 9090 port.
So possible to reach api end points with http://localhost:9090.

## Folder Structure and Short Explanation

- controllers

**Purpose: Handles incoming HTTP requests and sends responses.**
- Services

**Purpose: Contains the business logic of the application.**

- Repositories

**Purpose: Manages data access and interaction with the database.**

- Models

**Purpose: Represents the data structure of the application.**

- Dtos

**Purpose: Data Transfer Objects (DTOs) are used to transfer data between layers.**

- Enums

**Purpose: Defines constants used across the application.**

- Utils

**Purpose: Contains utility classes and helper methods.**

- Exceptions

**Purpose: Handles custom exceptions.**

## Some features in the project
- There is a simple but effective login system. And end points are protected by session token. 
- When user logout session will end and token will be passive. Thanks to that this is not possible 
to send request. That will greatly protect system against intruder. That will maintain authentication.
- System has role based authorization. It is possible to find some implementations. That customer can do 
operation while employee cannot.
- If request done without token system immediately reject it.
- The design adheres solid principle.
- It is also convenient to unit of work pattern. Each folder can be considered as seperate layer.
And each laye has its own nature.

## Generic API route

**@RequestMapping("/api/wallets")**

## Testing with Curl

<span style="border: 3px solid red; padding:2%;">
Curl test is a thing. So it is represented below;
</span>

### Wallet
<table>
  <thead>
    <tr>
      <th>Method</th>
      <th>Route</th>
      <th>Explanation</th>
      <th>Result</th>
      <th>Status</th>
    </tr>
  </thead>
  <tbody>
    <!-- Fetch all wallets -->
    <tr>
      <td>GET</td>
      <td><code>curl -X GET http://localhost:9090/api/wallets -H 'Authorization: Bearer d5b2f505-0110-4605-8455-9d792bcc82e7'
</code></td>
      <td>Fetch all wallets</td>
      <td><pre>[{"id":1,"ownerName":"John Doe","currency":"USD","balance":100.0}]</pre></td>
      <td>200 OK</td>
    </tr>
    <!-- Create a new wallet -->
    <tr>
      <td>POST</td>
      <td><code>curl -X POST \                                                                                              
  http://localhost:9090/api/wallets \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer d5b2f505-0110-4605-8455-9d792bcc82e7' \
  -d '{                    
        "walletName": "My Wallet",
        "ownerName": "John Doe", 
        "currency": "TRY",
        "balance": 1000.0,
        "usableBalance": 800.0,
        "activeForShopping": true,
        "activeForWithdrawal": true
      }'
'</code></td>
      <td>Create a new wallet</td>
      <td><pre>{"id":1,"ownerName":"John Doe","currency":"USD","balance":100.0}</pre></td>
      <td>201 Created</td>
    </tr>
    <!-- Register a new user -->
    <tr>
      <td>POST</td>
      <td><code>curl -X POST http://localhost:9090/api/register -H "Content-Type: application/json" -d '{"username": "testuser6", "password": "testpassword", "role": "CUSTOMER", "name": "Test", "surname": "User", "tckn": "12345678901"}'</code></td>
      <td>Register a new user</td>
      <td><pre>{"id":4,"username":"testuser6","password":"$2a$10$FzVcnHsuAcrbWmrNFR4NVOpCN3AHAzXWeU2KZI2sdS.XWeDU4yKBu","role":"CUSTOMER","name":"Test","surname":"User","tckn":"12345678901","wallets":null}</pre></td>
      <td>201 Created</td>
    </tr>
    <!-- Login -->
    <tr>
      <td>POST</td>
      <td><code>curl -X POST http://localhost:9090/api/login \                                                      
        -H "Content-Type: application/json" \
        -d '{
        "username": "testuser6",
        "password": "testpassword"
        }'
        </code></td>
      <td>Login and receive a session token</td>
      <td><pre>2efa8103-0442-47ae-82ef-6717ec397936</pre></td>
      <td>200 OK</td>
    </tr>
    <!-- Fetch wallet by ID -->
    <tr>
      <td>GET</td>
      <td><code>curl -X GET http://localhost:9090/api/wallets/6</code></td>
      <td>Fetch wallet by ID</td>
      <td><pre>{"id":6,"walletName":"My Wallet","ownerName":"John Doe","balance":1000.0,"usableBalance":800.0,"activeForShopping":true,"activeForWithdrawal":true,"userId":2}</pre></td>
      <td>200 OK</td>
    </tr>
    <!-- Fetch all wallets (Authorized) -->
    <tr>
      <td>GET</td>
      <td><code>curl -X GET http://localhost:9090/api/wallets -H 'Authorization: Bearer d5b2f505-0110-4605-8455-9d792bcc82e7'</code></td>
      <td>Fetch all wallets (Authorized)</td>
      <td><pre>[{"id":6,"walletName":"My Wallet","ownerName":"John Doe","balance":1000.0,"usableBalance":800.0,"activeForShopping":true,"activeForWithdrawal":true,"userId":2},{"id":7,"walletName":"My Wallet","ownerName":"John Doe","balance":1000.0,"usableBalance":800.0,"activeForShopping":true,"activeForWithdrawal":true,"userId":1},{"id":8,"walletName":"My Wallet","ownerName":"John Doe","balance":1000.0,"usableBalance":800.0,"activeForShopping":true,"activeForWithdrawal":true,"userId":1},{"id":9,"walletName":"My Wallet","ownerName":"John Doe","balance":1000.0,"usableBalance":800.0,"activeForShopping":true,"activeForWithdrawal":true,"userId":1},{"id":10,"walletName":"My Wallet","ownerName":"John Doe","balance":1000.0,"usableBalance":800.0,"activeForShopping":true,"activeForWithdrawal":true,"userId":1}]</pre></td>
      <td>200 OK</td>
    </tr>
    <!-- Create a new wallet (Authorized) -->
    <tr>
      <td>POST</td>
      <td><code>curl -X POST http://localhost:9090/api/wallets -H 'Content-Type: application/json' -H 'Authorization: Bearer d5b2f505-0110-4605-8455-9d792bcc82e7' -d '{"walletName": "My Wallet", "ownerName": "John Doe", "currency": "TRY", "balance": 1000.0, "usableBalance": 800.0, "activeForShopping": true, "activeForWithdrawal": true}'</code></td>
      <td>Create a new wallet (Authorized)</td>
      <td><pre>{"id":11,"walletName":"My Wallet","ownerName":"John Doe","balance":1000.0,"usableBalance":800.0,"activeForShopping":true,"activeForWithdrawal":true,"userId":1}</pre></td>
      <td>201 Created</td>
    </tr>
    <!-- Logout -->
    <tr>
      <td>POST</td>
      <td><code>curl -X POST http://localhost:9090/api/logout -H 'Authorization: Bearer ed2393f1-0316-4816-9564-24dbcc7a175e'</code></td>
      <td>Logout and invalidate session token</td>
      <td><pre>No content</pre></td>
      <td>200 OK</td>
    </tr>
    <!-- Deposit -->
    <tr>
      <td>POST</td>
      <td><code>curl -X POST http://localhost:9090/api/transactions/deposit -H "Content-Type: application/json" -H "Authorization: Bearer 2a30ade0-b07d-47f6-905b-f3038e3d4723" -d '{"walletId": 6, "amount": 200.0, "source": "Bank Transfer"}'</code></td>
      <td>Deposit funds into a wallet</td>
      <td><pre>Transaction details</pre></td>
      <td>201 Created</td>
    </tr>
    <!-- Withdraw -->
    <tr>
      <td>POST</td>
      <td><code>curl -X POST http://localhost:9090/api/transactions/withdraw -H "Content-Type: application/json" -H "Authorization: Bearer 2a30ade0-b07d-47f6-905b-f3038e3d4723" -d '{"walletId": 6, "amount": 50.0, "destination": "Bank Account"}'</code></td>
      <td>Withdraw funds from a wallet</td>
      <td><pre>Transaction details</pre></td>
      <td>201 Created</td>
    </tr>
    <!-- Approve Transaction -->
    <tr>
      <td>POST</td>
      <td><code>curl -X POST http://localhost:9090/api/transactions/approve -H "Content-Type: application/json" -H "Authorization: Bearer 2a30ade0-b07d-47f6-905b-f3038e3d4723" -d '{"transactionId": 123, "status": "APPROVED"}'</code></td>
      <td>Approve a transaction</td>
      <td><pre>Transaction details</pre></td>
      <td>200 OK</td>
    </tr>
    <!-- Deny Transaction -->
    <tr>
      <td>POST</td>
      <td><code>curl -X POST http://localhost:9090/api/transactions/deny -H "Content-Type: application/json" -H "Authorization: Bearer 2a30ade0-b07d-47f6-905b-f3038e3d4723" -d '{"transactionId": 133, "status": "DENIED"}'</code></td>
      <td>Deny a transaction</td>
      <td><pre>Transaction details</pre></td>
      <td>200 OK</td>
    </tr>
  </tbody>
</table>

## Commands for Executing programs
- gradle build
- gradle bootRun


