import java.io.*;
import java.util.*;

class UserHistory {
    // !!!! TO BE AMMEND !!! Save csv file name to user's Account name as CSV file
    private final String FILE_PATH = "User_History/favorites.csv";

    // record a favorite currency
    public void recordFavoriteCurrency(String currency) {
        // Read existing data from the file
        Map<String, Integer> currencyCounts = readCurrencyCountsFromFile();

        // Increase count for the given currency
        currencyCounts.put(currency, currencyCounts.getOrDefault(currency, 0) + 1);

        // Write back the sorted data to the file
        writeCurrencyCountsToFile(currencyCounts);
    }

    // read currency counts from the CSV file
    private Map<String, Integer> readCurrencyCountsFromFile() {
        Map<String, Integer> currencyCounts = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String currency = parts[0];
                    int count = Integer.parseInt(parts[1]);
                    currencyCounts.put(currency, count);
                }
            }
        } catch (IOException e) {
            // Log or handle the exception appropriately
            e.printStackTrace();
        }
        return currencyCounts;
    }

    // write currency counts to the CSV file
    private void writeCurrencyCountsToFile(Map<String, Integer> currencyCounts) {
        // Sort the currency counts map by value in descending order
        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(currencyCounts.entrySet());
        sortedEntries.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Map.Entry<String, Integer> entry : sortedEntries) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            // Log or handle the exception appropriately
            e.printStackTrace();
        }
    }

    // print the top n most frequently used currencies
    public void printTopFavoriteCurrencies(int n) {
        Map<String, Integer> currencyCounts = readCurrencyCountsFromFile();

        System.out.println("Top " + n + " most frequently used currencies:");

        int count = 0;
        for (Map.Entry<String, Integer> entry : currencyCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " Viewed");
            count++;
            if (count >= n) {
                break;
            }
        }
    }
}
