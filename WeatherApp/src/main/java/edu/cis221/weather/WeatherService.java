package edu.cis221.weather;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public final class WeatherService {
    private static final int TIMEOUT_MILLIS = 8000;

    public WeatherSnapshot fetchCurrentWeather(String city) throws IOException {
        String encodedCity = URLEncoder.encode(city.trim(), "UTF-8");
        JsonObject geocoding = getJson("https://geocoding-api.open-meteo.com/v1/search?name="
                + encodedCity + "&count=1&language=en&format=json");
        JsonArray results = geocoding.has("results") && geocoding.get("results").isJsonArray()
                ? geocoding.getAsJsonArray("results") : new JsonArray();
        if (results.size() == 0) {
            throw new CityNotFoundException(city);
        }

        JsonObject location = results.get(0).getAsJsonObject();
        double latitude = requiredNumber(location, "latitude");
        double longitude = requiredNumber(location, "longitude");
        String locationName = formatLocation(location);

        String forecastUrl = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude
                + "&longitude=" + longitude
                + "&current=temperature_2m,relative_humidity_2m,apparent_temperature,weather_code,wind_speed_10m"
                + "&temperature_unit=celsius&wind_speed_unit=kmh&timezone=auto";
        JsonObject forecast = getJson(forecastUrl);
        JsonObject current = forecast.has("current") && forecast.get("current").isJsonObject()
                ? forecast.getAsJsonObject("current") : null;
        if (current == null) {
            throw new IOException("The weather response did not include current conditions.");
        }

        return new WeatherSnapshot(
                locationName,
                requiredNumber(current, "temperature_2m"),
                requiredNumber(current, "apparent_temperature"),
                requiredNumber(current, "relative_humidity_2m"),
                requiredNumber(current, "wind_speed_10m"),
                (int) requiredNumber(current, "weather_code"),
                optionalString(current, "time"));
    }

    private JsonObject getJson(String address) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) URI.create(address).toURL().openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(TIMEOUT_MILLIS);
        connection.setReadTimeout(TIMEOUT_MILLIS);
        connection.setRequestProperty("Accept", "application/json");

        try {
            int responseCode = connection.getResponseCode();
            InputStream responseStream = responseCode >= 200 && responseCode < 300
                    ? connection.getInputStream() : connection.getErrorStream();
            String response = readResponse(responseStream);
            if (responseCode < 200 || responseCode >= 300) {
                throw new IOException(apiErrorMessage(response, responseCode));
            }
            try {
                JsonElement parsed = JsonParser.parseString(response);
                if (!parsed.isJsonObject()) {
                    throw new IOException("The weather service returned an unexpected response.");
                }
                JsonObject object = parsed.getAsJsonObject();
                if (object.has("error") && object.get("error").getAsBoolean()) {
                    throw new IOException(optionalString(object, "reason"));
                }
                return object;
            } catch (JsonParseException | IllegalStateException exception) {
                throw new IOException("The weather service returned invalid data.", exception);
            }
        } finally {
            connection.disconnect();
        }
    }

    private String readResponse(InputStream stream) throws IOException {
        if (stream == null) {
            return "";
        }
        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }
        return response.toString();
    }

    private String apiErrorMessage(String response, int responseCode) {
        try {
            JsonObject error = JsonParser.parseString(response).getAsJsonObject();
            String reason = optionalString(error, "reason");
            if (!reason.isEmpty()) {
                return "Weather service error: " + reason;
            }
        } catch (JsonParseException | IllegalStateException ignored) {
            // Use the HTTP status when the server does not return a JSON error.
        }
        return "Weather service returned HTTP " + responseCode + ".";
    }

    private double requiredNumber(JsonObject object, String field) throws IOException {
        JsonElement value = object.get(field);
        if (value == null || value.isJsonNull() || !value.isJsonPrimitive()
                || !value.getAsJsonPrimitive().isNumber()) {
            throw new IOException("The weather response is missing " + field + ".");
        }
        return value.getAsDouble();
    }

    private String optionalString(JsonObject object, String field) {
        JsonElement value = object.get(field);
        return value == null || value.isJsonNull() ? "" : value.getAsString();
    }

    private String formatLocation(JsonObject location) {
        StringBuilder formatted = new StringBuilder(optionalString(location, "name"));
        String region = optionalString(location, "admin1");
        String country = optionalString(location, "country");
        if (!region.isEmpty() && !region.equalsIgnoreCase(formatted.toString())) {
            formatted.append(", ").append(region);
        }
        if (!country.isEmpty()) {
            formatted.append(", ").append(country);
        }
        return formatted.toString();
    }

    private static String conditionForCode(int code) {
        switch (code) {
            case 0:
                return "Clear sky";
            case 1:
                return "Mainly clear";
            case 2:
                return "Partly cloudy";
            case 3:
                return "Overcast";
            case 45:
            case 48:
                return "Fog";
            case 51:
                return "Light drizzle";
            case 53:
                return "Moderate drizzle";
            case 55:
                return "Dense drizzle";
            case 56:
            case 57:
                return "Freezing drizzle";
            case 61:
                return "Light rain";
            case 63:
                return "Moderate rain";
            case 65:
                return "Heavy rain";
            case 66:
            case 67:
                return "Freezing rain";
            case 71:
                return "Light snow";
            case 73:
                return "Moderate snow";
            case 75:
                return "Heavy snow";
            case 77:
                return "Snow grains";
            case 80:
                return "Light rain showers";
            case 81:
                return "Rain showers";
            case 82:
                return "Heavy rain showers";
            case 85:
                return "Snow showers";
            case 86:
                return "Heavy snow showers";
            case 95:
                return "Thunderstorm";
            case 96:
            case 99:
                return "Thunderstorm with hail";
            case 97:
                return "Heavy thunderstorm";
            default:
                return "Conditions unavailable";
        }
    }

    public static final class WeatherSnapshot {
        private final String location;
        private final double temperature;
        private final double apparentTemperature;
        private final double humidity;
        private final double windSpeed;
        private final int weatherCode;
        private final String time;

        private WeatherSnapshot(String location, double temperature, double apparentTemperature,
                                double humidity, double windSpeed, int weatherCode, String time) {
            this.location = location;
            this.temperature = temperature;
            this.apparentTemperature = apparentTemperature;
            this.humidity = humidity;
            this.windSpeed = windSpeed;
            this.weatherCode = weatherCode;
            this.time = time;
        }

        public String getLocation() {
            return location;
        }

        public double getTemperature() {
            return temperature;
        }

        public double getApparentTemperature() {
            return apparentTemperature;
        }

        public double getHumidity() {
            return humidity;
        }

        public double getWindSpeed() {
            return windSpeed;
        }

        public String getCondition() {
            return conditionForCode(weatherCode);
        }

        public String getTime() {
            return time;
        }
    }

    public static final class CityNotFoundException extends IOException {
        public CityNotFoundException(String city) {
            super("No matching city was found for \"" + city + "\".");
        }
    }
}