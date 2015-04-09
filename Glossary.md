# Glossary

## OADA
The [Open Agriculture Data Alliance](http://openag.io/), OADA, is an open project designed to bring interoperability, security, and privacy to agricultural data. OADA provides [documentation](https://github.com/OADA/oada-docs) on Github, including functional requirements, a [REST API](https://github.com/OADA/oada-docs/tree/master/rest-specs) definition and [example applications](https://github.com/oada).

## OAuth 2.0  
OpenLink API is conformant to the OADA requirement to authenticate users using the [OAuth 2.0](http://oauth.net/) open authorisation standard. This standardised mechanism provides client applications a 'secure delegated access' to server resources on behalf of a resource owner. It specifies a process for resource owners to authorise third-party access to their server resources without sharing their credentials.

### Resource Provider
The resource provider in this case is Observant's OpenLink API.

### Client  
A client is a third party application that consumes data through the OpenLink API.

### User  
A user is an Observant customer who decides to share their data with a third-party application.

### Authentication Server  
The server issuing access tokens to a client application after successfully authenticating the user and obtaining their authorisation.

### Scopes  
Scopes can be used to limit the amount of data that is made available by the resource provider. OpenLink API version 0.5 will only have one scope for the user to select from - "soil moisture". This is subject to change as more data becomes accessible through the API.
