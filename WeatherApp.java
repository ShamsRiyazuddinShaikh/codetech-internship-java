import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WeatherApp {

    public static void main(String[] args) {
        // Your OpenWeatherMap API Key
        String apiKey = "YOUR_API_KEY";
        // City for which you want to fetch the weather data
        String city = "London";
        // Base URL for OpenWeatherMap API (current weather data)
        String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";

        try {
            // Creating a URL object from the apiUrl
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Reading the response from the API
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parsing the response JSON data
            JSONObject weatherData = new JSONObject(response.toString());
            displayWeatherData(weatherData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to display weather data in structured format
    public static void displayWeatherData(JSONObject weatherData) {
        // Extracting necessary data from the JSON response
        String cityName = weatherData.getString("name");
        JSONObject main = weatherData.getJSONObject("main");
        double temperature = main.getDouble("temp");
        double humidity = main.getDouble("humidity");

        // Weather conditions
        JSONObject weather = weatherData.getJSONArray("weather").getJSONObject(0);
        String description = weather.getString("description");

        // Wind data
        JSONObject wind = weatherData.getJSONObject("wind");
        double windSpeed = wind.getDouble("speed");

        // Displaying the extracted data
        System.out.println("Weather Information for " + cityName + ":");
        System.out.println("Temperature: " + temperature + "°C");
        System.out.println("Humidity: " + humidity + "%");
        System.out.println("Weather: " + description);
        System.out.println("Wind Speed: " + windSpeed + " m/s");
    }
}
