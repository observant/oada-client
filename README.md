## Observant API 
The API is a REST API using the JSON data format. 

### Endpoints
**/api/user**  
returns the current user
```
{
    "username": "username",
    "scope": [
        "soil-moisture"
    ],
    "resourceIds": [
        "/api/bookmarks"
    ],
    "clientId": "clientId",
    "grantType": "authorization_code"
}
```

**/api/bookmarks**  
returns all available bookmarks
```
[ 
  { "name" : "portfolios", "url" : "/api/bookmarks/portfolios" }, 
  { "name" : "sensors", "url" : "/api/bookmarks/sensors" }
]
```

**/api/bookmarks/portfolios**  
returns a list of the available portfolios
```
[ 
  { "id" : "portfolioId1", "name" : "Portfolio One" }, 
  { "id" : "portfolioId2", "name" : "Portfolio Two" }, 
  .
  .
  .
]
```

**/api/bookmarks/sensors** or **/api/bookmarks/sensors?portfolio={portfolioId}**  
returns a list of all available sensors or available sensors for the specified portfolio
```
[ 
  { 
	"id" : "sensor1", 
    "portfolio" : "portfolioId1", 
    "name" : "Soil Probe Almonds One"
  },
  { 
	"id" : "sensor2", 
    "portfolio" : "portfolioId1", 
    "name" : "Soil Probe Almonds Two"
  }
  .
  .
  .
]
```

**/resources/sensors/{sensorId}/meta**  
returns the metadata for a specific sensor
```
{ 
	"id" : "sensor1", 
    "portfolio" : "portfolioId1", 
    "name" : "Soil Probe Almonds One", 
    "location" : { "lat" : "-37.86", "lon" : "145.08", "elevation" : "0.0" }, 
	"type" : "soil-probe",
	"readings" : [
		{
			"id" : "reading1",
			"name" : "Reading One",
			"type" : "double",
			"unit" : "%",
			"format" : "0.0"
			.
			.
			.
		},
		{
			"id" : "reading2",
			"name" : "Reading Two",
			"type" : "date",
			"format" : "ISO8601"
			.
			.
			.
		},
		{
			"id" : "reading3",
			"name" : "Reading Three",
			"type" : "double",
			"unit" : "°C"
			"format" : "#0.00"
			.
			.
			.
		},
		.
		.
		.
	]
}
```

**/resources/sensors/{sensorId}/data**  
returns the data for a specific sensor
```
{ 
	"id" : "sensor1", 
    "readings" : [
		{
			"id" : "reading1",
			[
		        {"date": "...", "value":"..."},
        		{"date": "...", "value":"..."},
        		{"date": "...", "value":"..."},
    			.
				.
				.
			]
		},
		{
			"id" : "reading2",
			[
		        {"date": "...", "value":"..."},
        		{"date": "...", "value":"..."},
        		{"date": "...", "value":"..."},
    			.
				.
				.
			]
		},
		{
			"id" : "reading3",
			[
		        {"date": "...", "value":"..."},
        		{"date": "...", "value":"..."},
        		{"date": "...", "value":"..."},
    			.
				.
				.
			]
		},
		.
		.
		.
	]
}
```
