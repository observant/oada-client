# OAuth2

## Authentication Process  
The user authentication process is triggered when a third-party application tries to access the OpenLink API without a valid user authentication token. When this occurs, rather than returning the requested resource, the client is redirected to an Observant hosted login page. This page submits the user's Observant authentication credentials (username and password) directly to the Observant OAuth authentication server. Upon successful authentication the user is redirect back to the client application's return url.

### ￼Client Stage  
In order for the client to make a successful request to the OpenLink API it must include a state code. This is achieved by the following two stages: 
  1. Client Registration  
The Client must be registered manually with Observant. Currently, the only way to register a new client application with Observant is to send an email requesting the Client authentication credentials.
  2. Client Authentication  
Once a client is registered manually, the programatic authentication is possible and the client can acquire a state code. This intermediary step takes place when a request is sent from the client to the resource provider.

#### Initial Client Request  
Request Parameter | Description | Value
------------------|-------------|--------
￼￼￼￼client_id|Contains the unique client identifier obtained from Observant|${client_id}
redirect_url | Contains the url used to redirect back to the client application (this callback url should be registered with Observant and must use the HTTPS protocol) | ${client_url}
response_type | Contains the response type code | code

### ￼User Stage  
A user will have authentication credentials that are registered with Observant. These authentication credentials can be reset though Observant Global.

### Authentication Server  
#### Service location  
                  | ￼￼Domain      |￼Path
------------------|-------------|--------
￼Authorisation Exchange|￼￼￼￼${oauth-domain}|￼/uaa/oauth/authorize
￼Token Exchange|￼￼${oauth-domain}|  ￼/uaa/oauth/token
￼￼
#### Authorisation Exchange  
The first time a given user's browser is sent to this URL, they see a consent page. If they grant access, then the response includes an authorisation code which may be redeemed for an access token and a refresh token.
An example of an authorization code exchange is shown below:
```
POST /oauth2/v3/token HTTP/1.1
Host: ${oauth-domain}
Content-Type: application/x-www-form-urlencoded
code=4/P7q7W91a-oMsCeLvIaQm6bTrgtp7&
client_id=${client-id}
client_secret=${client_secret}&
redirect_uri=${redirect-url}
grant_type=authorization_code
```
If this is the first time the application has exchanged an authorization code for a user, then the response includes an access token and a refresh token, as shown below:
```
{
  "access_token":"1/fFAGRNJru1FTz70BzhT3Zg",
  "expires_in":3920,
  "token_type":"Bearer",
  "refresh_token":"1/xEoDL4iW3cxlI7yDbSRFYNG01kVKM2C-259HOF2aQbI"
}
```
##### *Important*  
When the client application receives a refresh token, it is important that it stores that refresh token for future use. If the application loses the refresh token, it will have to re-prompt the user for consent before obtaining another refresh token.  
##### ￼Example interaction sequence 
The table below represents the interactions between a browser and the servers required to complete a login to a client application and access a users data through a resource provider and authentication server. In the example the ${client-domain} string replaces the domain of the client and the ${oath-domain} string replaces the domain of the Observant OAuth authentication server.  

￼￼Verb|￼Path|￼￼￼Status|￼￼￼Response
----|----|------|---------
￼￼Get|￼${client-domain}/testfarms/|￼￼￼￼200|￼￼￼￼index.html
￼￼Get|￼${client-domain}/testfarms/login.html|￼￼￼￼200|￼￼￼￼login.html
￼Post|￼￼${client-domain}/testfarms/login.do|￼￼￼￼302|￼￼￼Redirect.html
￼Get|￼${client-domain}/testfarms/|￼￼200|￼index.html
￼￼￼Get|${client-domain}/testfarms/oada/|￼302|￼Redirect to authorisation server
￼￼Get|￼${oauth-domain}/uaa/oauth/authorize?client_id=${client_id}&redirect_uri=${client-domain}/oada/&response_type=code&state=${state_code}|￼￼￼￼302|￼￼￼￼Redirect to login page
￼￼Get|￼${oauth-domain}/uaa/login|￼￼￼￼200|￼￼￼￼login.html
￼Post|￼￼${oauth-domain}/uaa/login|￼￼￼￼302|￼￼￼Redirect
￼￼￼Get|${oauth-domain}/uaa/oauth/authorize?client_id=${client_id}&redirect_uri=${client-domain}/oada/&response_type=code&state=${state_code }|￼302|￼authorisation html
￼￼Post|￼${oauth-domain}/uaa/oauth/authorize|￼￼￼￼302|￼￼￼￼Redirect
￼Get|￼${client-domain}/testfarms/oada/?code=${auth_code}&state=${state_code}|￼￼￼302|￼￼￼Redirect￼
 | ￼￼￼￼￼￼￼￼￼Token exchange (between client and authentication server)|   
￼￼￼￼￼Get|${client-domain}/testfarms/oada/index.html|200|index.html  
####Token Exchange
A further interaction takes place between the client server and the authentication server in order to obtain the access token with which the subsequent requests will be made. These subsequent request can be made from the client server without direct initialisation from the user browser as long at the client server has the access token, or the refresh token with which to obtain new access tokens.  
##### Obtaining Tokens  
An access token can be obtained through the "token" endpoint using the value as shown in the table below:  

Field | Description
------|-------------
redirect_uri | One of the redirect URIs registered with Observant
code | The authorisation code returned from the initial request
client_secret | The client secret obtained from Observant
client_id | The client Id obtained from Observant  
￼The "expires_in" value in the response from the "token" endpoint (/uaa/oauth/token) provides the time remaining until the token expires, in seconds. This value can be used to monitor when a token "refresh" is required. An access token is usually created with a short "time-to-live", the exact value of this will be confirmed before the service goes to production but it is expect to be 24 hours.  
##### Access Token Refresh  
A token refresh can be achieved by using the "refresh_token" value returned from the token endpoint to request another access token through the same endpoint.  

Field | Description
------|-------------
refresh_token | The refresh token returned form the authorisation code exchange
client_id | The client Id obtain from Observant
client_secret | The client secret obtained from Observant
grant_type | As defined in the OAuth 2.0 specification, this field must contain the value of the "refresh_token"  
As long as the access granted to the application has not been revoked, the response includes a new access token. An example of a request is shown below:
```
{
  "access_token":"1/fFBGRNJru1FQd44AzqT3Zg",
  "expires_in":3920,
  "token_type":"Bearer",
}
```
##### Token Invalidation  
The refresh token will be created with an indefinite expiry duration. However, if the token becomes invalidated either through the the removal of authorisation via Observant Global or if a user account is removed then the service will announce that the refresh token has been set to expire after a grace period (of potentially two week). This should allow time for the authorisation to be re-applied or a user account to be re-established. The exact mechanism for this still needs to be worked out and once understood will be communicated accordingly.

#### ￼Simulating the exchange using CURL commands  
The user authentication through the client can be simulated as follows. This process shows how the authorisation code is exchanged for an access token and a refresh token.
```
${client_id}:${client_secret}@${oauth-domain}/uaa/oauth/token -d grant_type=authorization_code -d
client_id=service -d redirect_uri=${client-domain}/oada/ -d code=${auth_code}
```
The response from this request is something like the following:
```
{"access_token":"63d83905-2c82-4ced-8716-e5b7d9788c62","token_type":"bearer","refresh_token":"fd92ee77-5566
-450c-8800-b5e1b484287e","expires_in":43199,"scope":"read use"}
```
The access token can the be used as the bearer value to make request to the authentication server
```
curl -v -H "Authorization: Bearer 63d83905-2c82-4ced-8716-e5b7d9788c62" ${oauth-domain}/uaa/user
```
