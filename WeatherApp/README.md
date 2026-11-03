# Weather Desk

A small Java Swing app for searching a city and viewing current weather conditions.
It uses the Open-Meteo geocoding and forecast APIs, with no API key required for
non-commercial use.

## Requirements

- Java 25 or newer
- Maven 3.6 or newer
- Internet access

## Run

From this directory, run:

```powershell
mvn clean compile exec:java
```

Search by city name. If a city name is ambiguous, add a state or country to narrow
the result. Temperatures are shown in Celsius and wind speed in km/h.

Weather data is provided by [Open-Meteo](https://open-meteo.com/). Its public API
is intended for non-commercial use and is subject to its usage limits and terms.