# Observant's OpenLink API 

## Overview  
Observant's OpenLink API allows an Observant user to share their data with third-party applications through an interface that strives to be [Open Agriculture Data Alliance](http://openag.io/) compliant. The data exposed through the API is represented in accordance to the OADA documentation and access to the API is restricted through the use of an [OAuth 2.0](http://oauth.net/) authentication and authorisation mechanism. Once a user is authenticated they can provide authorisation to the third-party application to make use of their data. The data that a third-party application receives will be dependant on the data that the user chose to share through the Observant platform.

## Version 1.0

Release date: TBD

Observant OpenLink API version 1.0 will provide access to data collected by soil moisture sensors. It is an opt in feature which must be explicitly activated by the User for each installed sensor from Observant's Crop Manager application. Once data sharing is enabled by the User, registered third party clients can request access to sensor readings like soil moisture, soil temperature and soil electrical conductivity. The API returns data for the last seven days at one hour interval by default. This release will support readings from both soil and weather monitoring sensors.

### Supported Soil Monitoring Sensors
* Aquacheck
* EnviroPro
* Stevens Hydra Probe II
* Decagon GS3
* Watermark

### Supported Weather Monitoring Sensors
* Davis Weather Station
* Davis Rain Bucket
* E+E RHT Sensor

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
