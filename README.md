# Observant's OpenLink API 

## Overview  
Observant's OpenLink API allows an Observant user to share their data with third-party applications through an interface that strives to be [Open Agriculture Data Alliance](http://openag.io/) compliant. The data exposed through the API is represented in accordance to the OADA documentation and access to the API is restricted through the use of an [OAuth 2.0](http://oauth.net/) authentication and authorisation mechanism. Once a user is authenticated they can provide authorisation to the third-party application to make use of their data. The data that a third-party application receives will be dependant on the data that the user chose to share through the Observant platform.

## Version 1.1

Release date: 14th August 2017

Observant OpenLink API version 1.1 provides access to data collected by sensors monitoring the following phenomenas: soil, weather, mirco-climate, water level, water flow and pressure. It is an opt in feature which must be explicitly activated by the User in Observant's management application on a per portfolio or per sensor basis. Once data sharing is enabled by the User, registered third party clients can request access to sensor readings like soil moisture, temperature and electrical conductivity. The API returns historical data for the requested time window, defaulting to the last week of data. Data granularity is deternmined based on the duration of the time window, i.e 5 minutely for the last 3 days, 15 minutely for the last week, hourly for the last month, etc... up to 12 hourly for the last 12 months. 

### Supported Soil Monitoring Sensors
* Aquacheck
* Decagon GS3
* EnviroPro
* Sentek EnviroScan
* Stevens Hydra Probe II

### Supported Weather Monitoring Sensors
* Davis Rain Gauge
* Davis Weather Station

### Supported Micro Climate Monitoring Sensors
* Decagon VP4
* Elekronik EE071

### Supported Water Level Sensors
* Senix ToughSonic

### Supported Flow Meters
* Seametrics AG2000
* Siemens MAG8000

### Supported Pressure Sensors
* Schneider OsiSense 

### Observable Phenomena
#### Soil Monitoring Sensors
* Soil Moisture
* Soil Temperature
* Soil Electro-conductivity
* Soil Salinity

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

#### Micro Climate Monitoring Sensors
* Temperature (Air)
* Dew Point
* Humidity
* Vapor Pressure
* Atmospheric Pressure

#### Water Level Sensors
* Percent Full
* Level From Bottom

#### Flow Meters
* Flow Rate
* Total Flow

#### Pressure Sensors
* Water Pressure

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
