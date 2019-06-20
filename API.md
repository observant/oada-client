# API Endpoints

The OpenLink API is a REST API using the JSON data format.

## Bookmarks - `/api/bookmarks`
Responds to HTTP GET request and returns list of bookmarks for Observant OADA API.

Response example
```javascript
[
    {
        "name": "me",
        "url": "https://hostname/api/users/me"
    },
    {
        "name": "portfolios",
        "url": "https://hostname/api/bookmarks/portfolios"
    },
    {
        "name": "sensors",
        "url": "https://hostname/api/bookmarks/sensors"
    }
]
```
| Name | Example | Description | Nullable
| ---- | ------- | ----------- | --------
| `name` | `portfolios` | Name of the bookmark | no
| `url` | `https://hostname/api/bookmarks/portfolios` | Full URL of the bookmark | no

## User details - `/api/users/me`

Responds to HTTP GET request and returns details of the user account in Observant OADA server.

Response example
```javascript
{
    "username": "user.name",
    "scope": [
        "sensor-data"
    ],
    "resourceIds": [
        "oada/api"
    ],
    "clientId": "TestFarms",
    "grantType": "authorization_code"
}
```
| Name | Example | Description | Nullable
| ---- | ------- | ----------- | --------
| `username` | `user.name` | Username in Observant system | no
| `scope` | `sensor-data` | List of scopes User has granted access to for Client application | no
| `resourceIds` | `oada/api` | List of resources the Client has access to in Observant system | no
| `clinetId` | `TestFarms` | Client application ID in Observant system | no
| `grantType` | `authorization_code` | Grant type in use | no

## Portfolios - `/api/bookmarks/portfolios`

Responds to HTTP GET request and returns a list of the available Portfolios.

Response example
```javascript
[
    {
        "id": "portfolio-1",
        "name": "Portfoloio 1"
    },
    {
        "id": "portfolio-2",
        "name": "Portfoloio 2"
    },
]
```

| Name | Example | Description | Nullable
| ---- | ------- | ----------- | --------
| `id` | `portfolio-1` | ID of the Portfolio | no
| `name`| `Portfolio 1` | Name of the Protfolio | no

## Sensors - `/api/bookmarks/sensors`

Responds to HTTP GET request. Returns list of available sensors.

This request accepts optional parameter `portfolioId`. 
If provided returned list is filtered using given Portfolio ID.
Example request URL with optional portfolio ID `/api/bookmarks/sensors?portfolioId=portfolio-1`. 
List of valid portfolio ID-s can be obtained from Portfolios endpoint.

Response example
```javascript
[ 
  { 
    "id" : "sensor1", 
    "portfolio" : "portfolio-1",
    "name" : "Sensor 1-A"
  },
  {
    "id" : "sensor2",
    "portfolio" : "portfolio-1",
    "name" : "Sensor 1-B"
  },
  {
    "id" : "sensor3",
    "portfolio" : "portfolio-2",
    "name" : "Sensor 2-A"
  }
]
```

| Name | Example | Description | Nullable
| ---- | ------- | ----------- | --------
| `id` | `sensor1` | ID of the Sensor | no
| `portfolio` | `portfolio-1`| ID of the Protfolio | no
| `name` | `Sensor-1A` | Name of the Sensor | no

## Sensor Metadata - `/api/resources/{portfolioId}/{sensorId}/meta`

Responds to HTTP GET request. Returns meta data for given sensor.

Response example
```javascript
{ 
  "id" : "sensor1", 
  "portfolio" : "portfolio-1",
  "serialCode": "C3-2161",
  "name" : "Sensor 1-A", 
  "location" : { "lat" : -37.797059, "lon" : 144.977967, "elv" : 3.9 },
  "type" : "Soil Moisture",
  "stateOfCharge": 95.5,
  "rssi": -67,
  "health": {
    "status": "ERROR",
    "type": "DevicePortUndercurrent",
    "message": "Communication failure. Device is not drawing power as expected. Check cabling",
    "timestamp": "2018-04-09T02:55:05.465Z"
  },
  "readings" : [
      {
          "id" : "reading1",
          "name" : "Moisture",
          "type" : "double",
          "unit" : "%",
          "format" : "0.0",
          "observing": "soil_moisture",
          "label": "Soli Moisture",
          "secondaryLabel": "10 cm"
      },
      {
          "id" : "reading2",
          "name" : "Temperature",
          "type" : "double",
          "unit" : "°C",
          "format" : "#0.00",
          "observing": "soil_temperature"
          "label": "Soli Temperature",
          "secondaryLabel": "20 cm"
      },
  ]
}
```

| Name | Example | Description | Nullable
| ---- | ------- | ----------- | --------
| `id` | `sensor1` | ID of the Sensor | no
| `portfolio` | `portfolio-1` | ID of the Portfolio | no
| `serialCode` | `C3-2161` | Serial Code of the field station that the sensor is connected to | no
| `name`| `Sensor 1-A` | Name of the sensor | no
| `location` | | Geolocation of the Sensor | no
| `location.lon` | `144.977967` | Longitude | no
| `location.lat` | `-37.797059` | Latitude | no
| `location.elv` | `3.9` | Elevation | no
| `type` | `Soil Moisture` | Type of the Sensor. List of supported sensor types is available [here](https://github.com/observant/oada-client#supported-soil-monitoring-sensors) | no
| `stateOfCharge` | `95.5` | The field station state of charge in % | yes
| `rssi` | `-67` | The field station signal strength in dBm | yes
| `health.status` | `ERROR` | Health of sensor - OK, WARNING, ERROR or INACTIVE | no
| `health.type` | `DevicePortUndercurrent` | Sensor health issue type | no
| `health.message` | `Communication failure. Device is not drawing power as expected. Check cabling` | Sensor health user-friendly warning | no
| `health.timestamp` | `2018-04-09T02:55:05.465Z` | Start time for the sensor health issue | no
| `readings` | | Readings of the Sensor | no
| `readings.id` | `reading1` | ID of Sensor Reading  | no
| `readings.name` | `Moisture` | Name of Sensor Reading | no
| `readings.type`| 'double'| Data type of the Sensor Reading | no
| `readings.unit`| '%'| Unit of the Sensor Reading | no
| `readings.format`| '#0.00'| Format of the Sensor Reading | no
| `readings.observing` | `soil_moisture` | Type of observation. List of observable phenomena is available [here](https://github.com/observant/oada-client#observable-phenomena) | yes (null when Sensors are not part of a Sensor group)
| `readings.label`| `Soil Moisture` | Primary label of the reading | no
| `readings.secondaryLabel`| `10 cm` | Secondary label of the reading | no

## Sensor Data - `/api/resources/{portfolioId}/{sensorId}/data`

Responds to HTTP GET request. Returns data for given sensor.

This request accepts optional 'from' and 'until' parameters. When provided returns the data for the given time window (inclusive range, i.e includes data points exactly at 'from' or 'until'), otherwise returns sensor data for the last week. Data granularity is deternmined based on the duration of the time window, i.e 5 minutely for the last 3 days, 15 minutely for the last week, hourly for the last month, etc... up to 12 hourly for the last 12 months of data requested.

Response example
```javascript
{
    "id": "sensor1",
    "portfolio": "portfolio-1",
    "readings": [
        {
            "id": "reading1",
            "entries": [
                {
                    "timestamp": "2015-04-22T14:00:00Z",
                    "value": 49.7545
                },
                {
                    "timestamp": "2015-04-22T15:00:00Z",
                    "value": 50.1909
                }
            ]
        },
        {
            "id": "reading2",
            "entries": [
                {
                    "timestamp": "2015-04-22T14:00:00Z",
                    "value": 0
                },
                {
                    "timestamp": "2015-04-22T15:00:00Z",
                    "value": 1
                }
            ]
        }
    ]
}
```

| Name | Example | Description | Nullable
| ---- | ------- | ----------- | --------
| `id` | `sensor1` | ID of the Sensor | no
| `portfolio` | `portfolio-1` | ID of the Portfolio | no
| `readings` | | Readings of the Sensor | no
| `readings.id` | `reading1` | ID of Sensor Reading  | no
| `readings.entries` |  | Entries of the Sensor Reading | no
| `readings.entries.timestamp`| '2015-04-22T14:00:00Z'| UTC Timestamp of the Sensor Reading Entry | no
| `readings.entries.value`| '49.7545'| Numerical value of the Sensor Reading Entry | yes (null when reading entry value does not exist yet)
| `readings.entries.errorCode`| '603'| 3 digit error code | yes (null when no error)
| `readings.entries.errorReason`| 'No named Sensor found for this field'| The cause of the error | yes (null when no error)


###Wind-Direction enumeration values
```javascript
{
    "enum": 0,
    "value": "N"
},
{
    "enum": 1,
    "value": "NNE"
},
{
    "enum": 2,
    "value": "NE"
},
{
    "enum": 3,
    "value": "ENE"
},
{
    "enum": 4,
    "value": "E"
},
{
    "enum": 5,
    "value": "ESE"
},
{
    "enum": 6,
    "value": "SE"
},
{
    "enum": 7,
    "value": "SSE"
},
{
    "enum": 8,
    "value": "S"
},
{
    "enum": 9,
    "value": "SSW"
},
{
    "enum": 10,
    "value": "SW"
},
{
    "enum": 11,
    "value": "WSW"
},
{
    "enum": 12,
    "value": "W"
},
{
    "enum": 13,
    "value": "WNW"
},
{
    "enum": 14,
    "value": "NW"
},
{
    "enum": 15,
    "value": "NNW"
}
```
