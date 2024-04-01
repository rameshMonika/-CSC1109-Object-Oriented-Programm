import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class ForeignExchange {
    private final String accessKey;
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    // Constructor to initialize ForeignExchange object with an access key
    public ForeignExchange() {
        this.accessKey = "5158abc2440729c89e7e250f";
    }

    // Method to get supported currency codes from the API
    public Optional<String> getSupportedCodes() {
        try {
            String url = BASE_URL + "codes?access_key=" + accessKey;
            return Optional.ofNullable(makeRequest(url)); // Make HTTP request to get supported codes
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    // Method to make an HTTP GET request and return the response as a String
    private String makeRequest(String url) throws IOException {
        URL urlObj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                return response.toString();
            }
        } else {
            throw new IOException("Failed to get response: HTTP error code " + responseCode);
        }
    }

    // Method to parse the conversion rate from JSON response
    private double parseConversionRate(String json) {
        int startIndex = json.indexOf("\"conversion_rate\":") + "\"conversion_rate\":".length();
        int endIndex = json.indexOf(",", startIndex);
        if (endIndex == -1) {
            endIndex = json.length();
        }
        String rateStr = json.substring(startIndex, endIndex);
        return Double.parseDouble(rateStr);
    }

    // Method to convert currency from one to another
    public double convertCurrency(String from, String to, double amount) {
        try {
            String url = BASE_URL + accessKey + "/pair/" + from + "/" + to + "/" + amount;
            String json = makeRequest(url); // Make HTTP request to get conversion rate
            double conversionRate = parseConversionRate(json);
            double calAmt = conversionRate * amount;
            return Math.round(calAmt * 100.0) / 100.0; // Return the converted amount
        } catch (IOException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    // Method to view conversion rates of a base currency
    public void viewCurrency(String baseCurrency) {
        try {
            String url = BASE_URL + this.accessKey + "/latest/" + baseCurrency;
            System.out.println(url);
            String json = makeRequest(url);

            int startIndex = json.indexOf("\"conversion_rates\":{") + "\"conversion_rates\":{".length();
            int endIndex = json.indexOf("}", startIndex);
            String ratesString = json.substring(startIndex, endIndex);

            System.out.println("Conversion rates for " + baseCurrency + ":");
            for (String ratePair : ratesString.split(",")) {
                String[] pair = ratePair.split(":");
                String targetCurrency = pair[0].trim().replaceAll("\"", "");
                double rate = Double.parseDouble(pair[1].trim());
                if (!targetCurrency.equals(baseCurrency)) {
                    System.out.println(targetCurrency + ": " + rate);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        ForeignExchange foreignExchange = new ForeignExchange();

        foreignExchange.viewCurrency("SGD"); // View conversion rates for JPY
        double amount = 100.0;
        double calculateCurrency = foreignExchange.convertCurrency("USD", "SGD", amount); // Convert USD to SGD
        System.out.println(amount + " USD = " + calculateCurrency + " SGD");
    }
}
