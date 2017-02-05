# Observant's OpenLink API 

## Overview  
Observant's OpenLink API allows an Observant user to share their data with third-party applications through an interface that strives to be [Open Agriculture Data Alliance](http://openag.io/) compliant. The data exposed through the API is represented in accordance to the OADA documentation and access to the API is restricted through the use of an [OAuth 2.0](http://oauth.net/) authentication and authorisation mechanism. Once a user is authenticated they can provide authorisation to the third-party application to make use of their data. The data that a third-party application receives will be dependant on the data that the user chose to share through the Observant platform.

## Version 1.0

Release date: 25th June 2015

Observant OpenLink API version 1.0 provides access to data collected by Soil and Weather Monitoring sensors. It is an opt in feature which must be explicitly activated by the User in Observant's Crop Manager application for each installed sensor. Once data sharing is enabled by the User, registered third party clients can request access to sensor readings like soil moisture, temperature and electrical conductivity. The API returns data for the last seven days at up to 15 minute intervals by default. 

### Supported Soil Monitoring Sensors
* Aquacheck
* EnviroPro
* Stevens Hydra Probe II
* Decagon GS3

### Supported Weather Monitoring Sensors
* Davis Weather Station

### Observable Phenomena
#### Soil Monitoring Sensors
* Soil Moisture
* Soil Temperature
* Soil Electro-conductivity

#### Weather Monitoring Sensors
* Temperature (Air)
* Dew Point
* Humidity
* Solar Radiation
* UV index
* Rainfall Summary
* Wind Speed
* Wind Direction (5 Minutely Average)
* Gust Speed
* Gust Direction (10 Minutely Average)

## Documentation

* [API](API.md)
* [Getting started: Observant demo client](GettingStarted.md)
* [General workflow](Workflow.md)
* [OAuth2: step by step](OAuth2-step-by-step.md)
* [OAuth2: longer description](OAuth2-details.md)
* [FAQ](FAQ.md)
* [Glossary](Glossary.md)

## Contact

OpenLink API related questions: openlink@observant.net.
