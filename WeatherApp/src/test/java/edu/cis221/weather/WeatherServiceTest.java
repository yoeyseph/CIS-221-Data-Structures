package edu.cis221.weather;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import edu.cis221.weather.WeatherService.CityNotFoundException;
import edu.cis221.weather.WeatherService.WeatherSnapshot;

class WeatherServiceTest {
    private final WeatherService service = new WeatherService();

    @Test
    void requiredNumberReadsNumericFields() throws Exception {
        JsonObject response = JsonParser.parseString("{\"temperature\":12.5}").getAsJsonObject();

        double result = (double) invokePrivate(service, "requiredNumber",
                new Class<?>[]{JsonObject.class, String.class}, response, "temperature");

        assertEquals(12.5, result, 0.0001);
    }

    @Test
    void requiredNumberRejectsMissingNullNonnumericAndArrayFields() {
        JsonObject missing = new JsonObject();
        JsonObject nullValue = new JsonObject();
        nullValue.add("temperature", null);
        JsonObject text = new JsonObject();
        text.addProperty("temperature", "warm");
        JsonObject array = new JsonObject();
        array.add("temperature", new JsonArray());

        for (JsonObject response : new JsonObject[]{missing, nullValue, text, array}) {
            IOException exception = assertThrows(IOException.class, () -> invokePrivate(service,
                    "requiredNumber", new Class<?>[]{JsonObject.class, String.class},
                    response, "temperature"));
            assertTrue(exception.getMessage().contains("temperature"));
        }
    }

    @Test
    void optionalStringReturnsEmptyForMissingAndNullValues() throws Exception {
        JsonObject response = new JsonObject();
        response.add("empty", null);

        assertEquals("", invokePrivate(service, "optionalString",
                new Class<?>[]{JsonObject.class, String.class}, response, "missing"));
        assertEquals("", invokePrivate(service, "optionalString",
                new Class<?>[]{JsonObject.class, String.class}, response, "empty"));
        response.addProperty("name", "Oslo");
        assertEquals("Oslo", invokePrivate(service, "optionalString",
                new Class<?>[]{JsonObject.class, String.class}, response, "name"));
    }

    @Test
    void formatLocationIncludesRegionAndCountry() throws Exception {
        JsonObject location = JsonParser.parseString(
                "{\"name\":\"Springfield\",\"admin1\":\"Illinois\",\"country\":\"United States\"}")
                .getAsJsonObject();

        assertEquals("Springfield, Illinois, United States", invokePrivate(service,
                "formatLocation", new Class<?>[]{JsonObject.class}, location));
    }

    @Test
    void formatLocationDoesNotRepeatNameAsRegion() throws Exception {
        JsonObject location = JsonParser.parseString(
                "{\"name\":\"Oslo\",\"admin1\":\"oslo\",\"country\":\"Norway\"}")
                .getAsJsonObject();

        assertEquals("Oslo, Norway", invokePrivate(service,
                "formatLocation", new Class<?>[]{JsonObject.class}, location));
    }

    @Test
    void apiErrorMessageUsesReasonOrHttpStatus() throws Exception {
        assertEquals("Weather service error: Unknown city",
                invokePrivate(service, "apiErrorMessage", new Class<?>[]{String.class, int.class},
                        "{\"reason\":\"Unknown city\"}", 404));
        assertEquals("Weather service returned HTTP 503.",
                invokePrivate(service, "apiErrorMessage", new Class<?>[]{String.class, int.class},
                        "not-json", 503));
        assertEquals("Weather service returned HTTP 502.",
                invokePrivate(service, "apiErrorMessage", new Class<?>[]{String.class, int.class},
                        "{\"reason\":\"\"}", 502));
    }

    @Test
    void readResponseHandlesNullAndUtf8Input() throws Exception {
        assertEquals("", invokePrivate(service, "readResponse",
                new Class<?>[]{java.io.InputStream.class}, new Object[]{null}));

        ByteArrayInputStream stream = new ByteArrayInputStream(
                "weather data".getBytes(StandardCharsets.UTF_8));
        assertEquals("weather data", invokePrivate(service, "readResponse",
                new Class<?>[]{java.io.InputStream.class}, stream));
    }

    @Test
    void weatherSnapshotExposesValuesAndCondition() throws Exception {
        WeatherSnapshot snapshot = createSnapshot("Oslo, Norway", 12.5, 10.0, 60.0,
                8.0, 61, "2026-11-03T12:00");

        assertEquals("Oslo, Norway", snapshot.getLocation());
        assertEquals(12.5, snapshot.getTemperature(), 0.0001);
        assertEquals(10.0, snapshot.getApparentTemperature(), 0.0001);
        assertEquals(60.0, snapshot.getHumidity(), 0.0001);
        assertEquals(8.0, snapshot.getWindSpeed(), 0.0001);
        assertEquals("Light rain", snapshot.getCondition());
        assertEquals("2026-11-03T12:00", snapshot.getTime());
    }

        @Test
        void parseHourlyForecastMapsAllRequestedFields() throws Exception {
        JsonObject response = JsonParser.parseString(
            "{\"hourly\":{\"time\":[\"2026-11-03T13:00\",\"2026-11-03T14:00\"],"
                + "\"temperature_2m\":[10,11],\"precipitation\":[0.2,1.5],"
                + "\"precipitation_probability\":[20,80],\"cloud_cover\":[35,90],"
                + "\"wind_speed_10m\":[8,12]}}")
            .getAsJsonObject();

        @SuppressWarnings("unchecked")
        List<WeatherService.HourlyForecast> result = (List<WeatherService.HourlyForecast>)
            invokePrivate(service, "parseHourlyForecast", new Class<?>[]{JsonObject.class}, response);

        assertEquals(2, result.size());
        WeatherService.HourlyForecast first = result.get(0);
        assertEquals("2026-11-03T13:00", first.getTime());
        assertEquals(10.0, first.getTemperature(), 0.0001);
        assertEquals(0.2, first.getPrecipitation(), 0.0001);
        assertEquals(20.0, first.getPrecipitationProbability(), 0.0001);
        assertEquals(35.0, first.getCloudCover(), 0.0001);
        assertEquals(8.0, first.getWindSpeed(), 0.0001);
        }

        @Test
        void parseHourlyForecastReturnsEmptyWhenHourlyDataIsUnavailable() throws Exception {
        @SuppressWarnings("unchecked")
        List<WeatherService.HourlyForecast> result = (List<WeatherService.HourlyForecast>)
            invokePrivate(service, "parseHourlyForecast", new Class<?>[]{JsonObject.class},
                new JsonObject());

        assertTrue(result.isEmpty());
        }

        @Test
        void parseHourlyForecastRejectsMismatchedArrays() {
        JsonObject response = JsonParser.parseString(
            "{\"hourly\":{\"time\":[\"2026-11-03T13:00\"],"
                + "\"temperature_2m\":[],\"precipitation\":[0],"
                + "\"precipitation_probability\":[20],\"cloud_cover\":[35],"
                + "\"wind_speed_10m\":[8]}}")
            .getAsJsonObject();

        IOException exception = assertThrows(IOException.class, () -> invokePrivate(service,
            "parseHourlyForecast", new Class<?>[]{JsonObject.class}, response));

        assertTrue(exception.getMessage().contains("inconsistent"));
        }

    @Test
    void weatherSnapshotMapsRepresentativeAndUnknownWeatherCodes() throws Exception {
        assertEquals("Clear sky", createSnapshot(0).getCondition());
        assertEquals("Fog", createSnapshot(48).getCondition());
        assertEquals("Freezing drizzle", createSnapshot(57).getCondition());
        assertEquals("Heavy rain", createSnapshot(65).getCondition());
        assertEquals("Heavy snow showers", createSnapshot(86).getCondition());
        assertEquals("Thunderstorm with hail", createSnapshot(99).getCondition());
        assertEquals("Heavy thunderstorm", createSnapshot(97).getCondition());
        assertEquals("Conditions unavailable", createSnapshot(100).getCondition());
    }

    @Test
    void convertsCelsiusToFahrenheit() {
        assertEquals(32.0, WeatherApp.toFahrenheit(0.0), 0.0001);
        assertEquals(-40.0, WeatherApp.toFahrenheit(-40.0), 0.0001);
        assertEquals(212.0, WeatherApp.toFahrenheit(100.0), 0.0001);
    }

    @Test
    void cityNotFoundExceptionIncludesTheRequestedCity() {
        CityNotFoundException exception = new CityNotFoundException("Atlantis");

        assertEquals("No matching city was found for \"Atlantis\".", exception.getMessage());
    }

    private static WeatherSnapshot createSnapshot(int weatherCode) throws Exception {
        return createSnapshot("Test", 1.0, 1.0, 1.0, 1.0, weatherCode, "");
    }

    private static WeatherSnapshot createSnapshot(String location, double temperature,
                                                   double apparentTemperature, double humidity,
                                                   double windSpeed, int weatherCode, String time)
            throws Exception {
        Constructor<WeatherSnapshot> constructor = WeatherSnapshot.class.getDeclaredConstructor(
                String.class, double.class, double.class, double.class, double.class,
                int.class, String.class, List.class);
        constructor.setAccessible(true);
        return constructor.newInstance(location, temperature, apparentTemperature, humidity,
                windSpeed, weatherCode, time, Collections.emptyList());
    }

    private static Object invokePrivate(Object target, String methodName,
                                        Class<?>[] parameterTypes, Object... arguments)
            throws Exception {
        Method method = WeatherService.class.getDeclaredMethod(methodName, parameterTypes);
        method.setAccessible(true);
        try {
            return method.invoke(target, arguments);
        } catch (InvocationTargetException exception) {
            Throwable cause = exception.getCause();
            if (Exception.class.isInstance(cause)) {
                throw Exception.class.cast(cause);
            }
            if (Error.class.isInstance(cause)) {
                throw Error.class.cast(cause);
            }
            throw exception;
        }
    }
}