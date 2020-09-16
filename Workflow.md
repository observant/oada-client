# General workflow

This document describes general workflow between User, Client Application and Observant server.

## Requirements
1. Client Application has registered in Observant system.
2. Client Application is properly configured to use Observant system.
3. Client identity is established in Client Application. Eg. user is logged in and has active session.

## Step by step description

![alt text][01]

### Step 1
1. Client Application displays link or button 'Connect with Observant' or similar to User.
2. User clicks on link and HTTP request is sent to Client Application.

### Step 2
Client Application sends HTTP redirect response containing Observant authorization URL and required parameters `client_id`, `redirect_uri`, `response_type` and optional `state`.
For example `https://global.test-jainlogic.com/uaa/oauth/authorize?client_id=myService&redirect_uri=http%3A%2F%2Flocalhost%3A1888%myservice%2Foada%2F&response_type=code&state=ABC123`.

### Step 3
Browsers sends HTTP GET request to Observant server using URL from previous step.

### Step 4
1. Observant server recognises request and redirects browser to login form using HTTP 302 response and URL.
For example `https://global.test-jainlogic.com/uaa/authenticate`.
2. Browser sends GET request to specified URL.
3. Observant server responds with login page.
![alt text][02]

### Step 5
Browser submits login form using HTTP POST request after user clicks 'SIGN IN' button.

### Step 6
1. Observant server recognises User and sends HTTP 302 response to redirect User to the authorization page.
For example `https://global.test-jainlogic.com/uaa/oauth/authorize?client_id=myService&redirect_uri=http%3A%2F%2Flocalhost%3A1188%2Fmyservice%2Foada%2F&response_type=code&state=ABC123`.
2. Browser sends GET request with specified URL to Observant server.
3. Observant server responds with authorization page.
![alt text][03]

### Step 7
Browser submits authorization form using HTTP POST request after user clicks 'ALLOW' button.

### Step 8
Observant server recognises request and responds with HTTP 302 and URL `http://localhost:1888/myservice/oada/?code=XYZ789`.

### Step 9
Browser sends HTTP GET request to specified URL in Client Application.

### Step 10
Client Application recognises request and sends HTTP POST request to Observant server to exchange code for tokens including Client Application credentials and required parameters `grant_type`, `client_id`, `redirect_uri` & `code`.
For example `https://global.test-jainlogic.com/uaa/oauth/token`.

### Step 11
Observant server recognises request and responds with tokens.
For example

```javascript
{
  "access_token":"abcdef",
  "token_type":"bearer",
  "refresh_token":"0123456789",
  "expires_in":86400,
  "scope":"sensor-data"
}
```

### Step 12
1. Client application receives response.
2. Extracts and stores tokens for later use.
3. Sends suitable response for still pending Client request from step 9.

### Step 13
Client Application makes HTTP request to Observant server on behalf of the Client using stored `access_token`. This request needs no Client involvement and can be issued using timer, cron job or any other suitable mechanism. Client Application can repeat this step until it has valid `access_token`.

### Step 14
1. Observant server recognises `access_token` and sends response with requested data.
2. Client Application handles response data as needed. Eg. stores for later use.

### Step 15
Browser sends request to Client App to access data collected from Observant server.

### Step 16
Client Application responds with stored data collected from Observant server.

[01]: https://github.com/ObservantPtyLtd/oada-client/blob/master/images/workflow.png "Workflow"
[02]: https://github.com/ObservantPtyLtd/oada-client/blob/master/images/02.png "OAuth2 login"
[03]: https://github.com/ObservantPtyLtd/oada-client/blob/master/images/03.png "OAuth2 access grants"
