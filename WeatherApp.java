import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherApp {

    private static final String API_KEY = "d1202c7ba9661b514401f422cb6cc326";  // Replace with your OpenWeatherMap API key

    public static void main(String[] args) {
        try {
            String location = "Chennai"; // Example location
            String weatherData = getWeatherData(location);

            if (weatherData != null) {
                displayWeatherData(weatherData);
            } else {
                System.out.println("No data found for the specified location.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getWeatherData(String location) throws Exception {
        String endpoint = "http://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=" + API_KEY + "&units=metric";
        URL url = new URL(endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) { // Success
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        } else {
            BufferedReader errorStream = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            String inputLine;
            StringBuilder errorResponse = new StringBuilder();

            while ((inputLine = errorStream.readLine()) != null) {
                errorResponse.append(inputLine);
            }
            errorStream.close();

            System.out.println("Error response: " + errorResponse.toString());
            return null;
        }
    }

    public static void displayWeatherData(String weatherData) {
        JSONObject jsonObj = new JSONObject(weatherData);
        
        String locationName = jsonObj.getString("name");
        JSONObject main = jsonObj.getJSONObject("main");
        double temperature = main.getDouble("temp");
        double feelsLike = main.getDouble("feels_like");
        int humidity = main.getInt("humidity");

        JSONObject weather = jsonObj.getJSONArray("weather").getJSONObject(0);
        String weatherDescription = weather.getString("description");

        JSONObject wind = jsonObj.getJSONObject("wind");
        double windSpeed = wind.getDouble("speed");
        int windDirection = wind.getInt("deg");

        System.out.println("Location: " + locationName);
        System.out.println("Temperature: " + temperature + "°C");
        System.out.println("Feels Like: " + feelsLike + "°C");
        System.out.println("Humidity: " + humidity + "%");
        System.out.println("Weather: " + weatherDescription);
        System.out.println("Wind Speed: " + windSpeed + " m/s");
        System.out.println("Wind Direction: " + windDirection + "°");
    }
}
