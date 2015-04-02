# OAuth2 step by step

This document describes how to interact with OAuth2 system when you are in the role of the Client. To keep this simple as possible basic tools - web browser and curl - are used. Observant OADA system is used as OAuth2 provider although basic interactions with any other similar systems should be the same.

## Requirements

To successfully interact with OAuth2 system Client side has to know few parameters.

| Parameter | Value | Description |
| --------- | ----- | ----------- |
| Authorisation URL | `https://test.obsrv.it/uaa/oauth/authorize` | URL of OAuth2 system to authorise users |
| Token URL | `https://test.obsrv.it/uaa/oauth/token` | URL of OAuth2 system to exchange temporary access code to access_token |
| Resource URL | `https://test.obsrv.it/api/` | Base URL for OAuth2 protected resources |
| Client ID | myService | Client ID provided by Observant. <sup>1</sup> |
| Client Secret | mySecret123 | Client secret provided by Observant. <sup>1</sup> |
| Client callback URI | `https://localhost:1888/myservice/oada/` | OAuth2 login callback URL specified by Client. This has to be registered with Client account in Observant system for successful OAuth2 logins. <sup>1</sup> |
| Username | bob | Username for existing user in Observant system. This user will be authenticated by OAuth2 server. <sup>1</sup> |
| Password | b0b0b | Password for existing user in Observant system. This password is never revealed to Client system in OAuth2 workflow. <sup>1</sup> |

<sup>1 - listed value is not functional in Observant system. Replace with one provided to you by Observant.</sup>

## OAuth2 workflows
### Login request
All OAuth2 workflows start with user authentication and issuing access grants. For this we need to construct login URL on Client side and direct user browser to this page.
`https://test.obsrv.it/uaa/oauth/authorize?response_type=code&client_id=myService&redirect_uri=https%3A%2F%2Flocalhost%3A1888%2Fmyservice%2Foada%2F`
Usually it's done in client server by providing links like 'Login with XYZ', 'Connect your account with ABC' etc.
To mimic this behaviour we can just copy-paste URL from above to web browser address bar.

![alt text][01]

After hitting enter Observant OAuth2 server will direct browser to the login page.

![alt text][02]

After successful login user is redirected to access grant page.

![alt text][03]

Once 'Allow' button is clicked browser is directed back to provided callback URL. You'll see the default browser error page at the moment since there is no actual service listening the request. Once your application is up an running you'll see the response from client server here.

![alt text][04]

Important is the code appended to the callback URL. We can copy the value `hgMa5a` and use it to exchange it to actual OAuth2 access token. Usually this happens between the servers and user has no knowledge about this step.

### Token exchange
We need to send HTTP POST request to Observant Oauth2 token URL using specific parameters to exchange temporary code to access token. This request has to contain client ID, client secret and exact callback URL used in previous request to authenticate client server. In current case we are going to do this manually using curl from command line.

```
$ curl -v https://myService:mySecret123@test.obsrv.it/uaa/oauth/token -d grant_type=authorization_code -d client_id=myService -d redirect_uri=https://localhost:1888/myservice/oada/ -d code=hgMa5a
{"access_token":"564810ff-7bd6-4ff7-a3bb-f76b0a0c28be","token_type":"bearer","refresh_token":"e630def6-2b33-40e0-af74-e51f52a3c11d","expires_in":86399,"scope":"soil-moisture"}
```

Response body is on the last line. Important part from the response is `"access_token":"564810ff-7bd6-4ff7-a3bb-f76b0a0c28be"`. Using this value we can now issue request to Observant OADA endpoints without any other authentication information. We can store token information for later use. For example we might want to access some resources using scheduled background tasks. Same token can be used as many times as needed until the expiration time.

### Accessing OAuth2 protected resources

To access protected resources we can send HTTP GET request with OAuth2 specific header. Once again we can use curl to issue the request
```
$ curl -v -H "Authorization: Bearer 564810ff-7bd6-4ff7-a3bb-f76b0a0c28be" https://test.obsrv.it/api/users/me
{"username":"bob","scope":["soil-moisture"],"resourceIds":["oada/api"],"clientId":"myService","grantType":"authorization_code"}
```
Response body contains JSON document with details about the client, user and grant information.
Subsequent similar requests can be sent to resource server with or without actual user interaction. For example we could issue request when user is visiting specific page in client server or from background task periodically updating data from Observent OADA Resource server.

### Handling token timeouts
Token timeouts are part of the OAuth2 protocol and can be handled standard way.

[TBD]


[01]: https://github.com/ObservantPtyLtd/oada-client/blob/master/images/01.png "OAuth2 authorization"
[02]: https://github.com/ObservantPtyLtd/oada-client/blob/master/images/02.png "OAuth2 login"
[03]: https://github.com/ObservantPtyLtd/oada-client/blob/master/images/03.png "OAuth2 access grants"
[04]: https://github.com/ObservantPtyLtd/oada-client/blob/master/images/04.png "OAuth2 callback"

