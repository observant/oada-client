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
| Name | Example | Description |
| ---- | ------- | ----------- |
| `name` | `portfolios` | Name of the bookmark |
| `url` | `https://hostname/api/bookmarks/portfolios` | Full URL of the bookmark |

## User details - `/users/me`

Responds to HTTP GET request and returns details of the user account in Observant OADA server.

Response example
```javascript
{
    "username": "user.name",
    "scope": [
        "soil-moisture"
    ],
    "resourceIds": [
        "oada/api"
    ],
    "clientId": "TestFarms",
    "grantType": "authorization_code"
}
```
| Name | Example | Description |
| ---- | ------- | ----------- |
| `username` | `user.name` | Username in Observant system |
| `scope` | `soil-moisture` | List of scopes User has granted access to for Client application | 
| `resourceIds` | `oada/api` | List of resources the Client has access to in Observant system |
| `clinetId` | `TestFarms` | Client application ID in Observant system |
| `grantType` | `authorization_code` | Grant type in use |

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

| Name | Example | Description |
| ---- | ------- | ----------- |
| `id` | `portfolio-1` | ID of the Portfolio |
| `name`| `Portfolio 1` | Name of the Protfolio |

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

| Name | Example | Description |
| ---- | ------- | ----------- |
| `id` | `sensor1` | ID of the Sensor |
| `portfolio` | `portfolio-1`| ID of the Protfolio |
| `name` | `Sensor-1A` | Name of the Sensor |

## Sensor Metadata - `/resources/{portfolioId}/{sensorId}/meta`

Responds to HTTP GET request. Returns meta data for given sensor.

Response example
```javascript
{ 
  "id" : "sensor1", 
  "portfolio" : "portfolio-1",
  "name" : "Sensor 1-A", 
  "location" : { "lat" : -37.797059, "lon" : 144.977967, "elv" : 3.9 },
  "type" : "Soil Moisture",
  "readings" : [
      {
          "id" : "reading1",
          "name" : "Moisture",
          "type" : "double",
          "unit" : "%",
          "format" : "0.0"
      },
      {
          "id" : "reading2",
          "name" : "Temperature",
          "type" : "double",
          "unit" : "°C"
          "format" : "#0.00"
      },
  ]
}
```

| Name | Example | Description |
| ---- | ------- | ----------- |
| `id` | `sensor1` | ID of the Sensor |
| `portfolio` | `portfolio-1` | ID of the Portfolio |
| `name`| `Sensor 1-A` | Name of the sensor |
| `location` | | Geolocation of the Sensor |
| `location.lon` | `144.977967` | Longitude |
| `location.lat` | `-37.797059` | Latitude |
| `location.elv` | `3.9` | Elevation |
| `type` | `Soil Moisture` | Type of the Sensor |
| `readings` | | Readings of the Sensor |
| `readings.id` | `reading1` | ID of Sensor Reading  |
| `readings.name` | `Moisture` | Name of Sensor Reading |
| `readings.type`| 'double'| Data type of the Sensor Reading |
| `readings.unit`| '%'| Unit of the Sensor Reading |
| `readings.format`| '#0.00'| Format of the Sensor Reading |

## Sensor Data - `/resources/{portfolioId}/{sensorId}/data`

Responds to HTTP GET request. Returns data for given sensor.

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

| Name | Example | Description |
| ---- | ------- | ----------- |
| `id` | `sensor1` | ID of the Sensor |
| `portfolio` | `portfolio-1` | ID of the Portfolio |
| `readings` | | Readings of the Sensor |
| `readings.id` | `reading1` | ID of Sensor Reading  |
| `readings.entries` |  | Entries of the Sensor Reading |
| `readings.entries.timestamp`| '2015-04-22T14:00:00Z'| UTC Timestamp of the Sensor Reading Entry |
| `readings.entries.value`| '49.7545'| Numerical value of the Sensor Reading Entry |
