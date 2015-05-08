# Observant's OpenLink API 

## Overview  
Observant's OpenLink API allows an Observant user to share their data with third-party applications through an interface that strives to be [Open Agriculture Data Alliance](http://openag.io/) compliant. The data exposed through the API is represented in accordance to the OADA documentation and the access to the API is restricted through the use of an [OAuth 2.0](http://oauth.net/) authentication and authorisation mechanism. Once a user is authenticated they can provide authorisation to the third-party application to make use of their data. The data that a third-party application receives will be dependant on the data that the user chose to share through the Observant platform.

## Version 1.0

Release date: TBD

Observant OpenLink API version 1.0 will provide access to data collected by soil moisture sensors. It is opt in feature which has explicitly activated by the User for each installed sensor from Observant's Crop Manager application. Once data sharing is enabled by User, registered third party clients can request access to sensor readings like soil moisture, soil temperature and soil electrical conductivity. API returns data for last seven days with one hour interval. This release will support readings from sensors:
* Aquacheck
* EnviroPro
* Stevens Hydra Probe II
* Decagon GS3
* Watermark

## Documentation

* [API](API.md)
* [Getting started: Observant demo client](GettingStarted.md)
* [General workflow](Workflow.md)
* [OAuth2: step by step](OAuth2-step-by-step.md)
* [OAuth2: longer description](OAuth2-details.md)
* [FAQ](FAQ.md)
* [Glossary](Glossary.md)

## Contact

OpenLing API related questions: openlink@observant.net.
