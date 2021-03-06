# FAQ

## As a Client can I skip the manual Observant authentication?
Observant is using OAuth2 protocol to authenticate Users and authorise Client (third party) access to User's data. Client system can't read User data unless access is explicitly granted. Before granting any access Observant has to authenticate the User.

## As a Client do I need to complete the User authentication for every API request?
After successful authentication and authorisation Observant provides 'access token' which can be used for subsequent API request. Client side application is responsible for storing 'access token' for later use if needed. For example Client application can issue API calls from background to Observant API using stored 'access token' without any interaction with the User.

## As a Client do I need to do two logins?
Actually no logins are required from the Client. Client has to direct User to Observant server when User is willing to share data with Client. User is directed back to Client application after successful login and access granting in the Observant server. Client application has to use it's Observant credentials to exchange temporary code to actual 'access token' in Observant server. Credentials are sent as HTTP Basic Authentication header and this process is not requiring any manual interaction.

## As an User do I need to do two logins?
As an User you have to login to Data Provider system to grant access for the Client. This is one time operation supposing Client application handles provided 'access token' properly.

Most likely you have to login to the Client application time to time. This is matter between Client application and end user. It has nothing to do with Data Provider or OAuth2 protocol.

## What is difference between User and Client in OAuth2 context?
* User refers to actual enduser who has accounts in Data Provider system. In current case Observant is the Data Provider.
* Client refers to third party application which can access User data from Data Provider system in case access is explicitly granted.
* Enduser account(s) in Client system is matter between Client and enduser. Any knowledge about such arrangement is not required by the Data Provider.

## What are the benefits of OAuth2 protocol?
* There is no need to disclose User credentials to Client application.
* Client application has no need to handle User account changes like password renewals etc.
* User can explicitly grant and revoke access to data shared by Data Provider.

## Can the path component of the url change if the domain is kept the same?
The redirect url registered with Observant must contain the scheme, domain and path components. If the url registered with Observant is https://a.domain.name/path then any subpath will also be accepted, e.g. https://a.domain.name/path/subpath.

## Can query parameters be sent in the redirect url?
The OAuth protocol allows for the transmission of additional state (otherwise achieved using query parameters, e.g. ?param=value) using a single 'state' query parameter. In order to pass an 'id' parameter as part of the redirect url, when hitting the 'oauth/authorize' endpoint, the id and it's value would need to be passed as the value of the state parameter. For example, see the following HTTP GET request and the resulting redirect url:

#### Request
https://global.test-jainlogic.com/uaa/oauth/authorize?response_type=code&client_id=TestFarms&redirect_uri=http%3A%2F%2Flocalhost%3A9977%2Ftestfarms%2Foada&state=id%3D123

#### Resulting redirect Url
http://localhost:9977/testfarms/oada?code=tWjSsu&state=id%3D123



