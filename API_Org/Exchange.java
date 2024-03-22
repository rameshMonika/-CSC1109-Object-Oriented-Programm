/**
 * Description: The Exchange class represents a currency exchange system with predefined exchange rates.
 */

// Imported libraries

import java.util.HashMap;
import java.util.Map;

/**
 * The Exchange class facilitates currency exchange with predefined exchange
 * rates.
 * It supports four different currencies: USD, JPY, THB, and MYR.
 */
public class Exchange {
    /**
     * A map to store exchange rates for different currencies.
     */
    private Map<String, Double> currencyMap;

    /**
     * Default constructor that initializes the Exchange object with predefined
     * exchange rates.
     * <p>
     * The default constructor initializes the exchange rates for four different
     * currencies: USD, JPY, THB, and MYR.
     * The exchange rates are set as follows:
     * - USD: 0.68653311
     * - JPY: 111.67136
     * - THB: 26.69023
     * - MYR: 3.5286746
     */
    public Exchange() {
        currencyMap = new HashMap<>();
        this.currencyMap.put("USD", 0.68653311);
        this.currencyMap.put("EUR", 0.69);
        this.currencyMap.put("JPY", 111.67136);
        this.currencyMap.put("MYR", 3.5286746);
    }

    /**
     * Retrieves and prints the exchange rate for a given currency.
     *
     * @param c The currency for which the exchange rate is requested.
     */
    public void getRate(String c) {
        if (currencyMap.containsKey(c)) {
            System.out.println("Exchange rate for " + c + ": " + currencyMap.get(c));
        } else {
            System.out.println("Exchange rate not available for " + c);
        }
    }

    /**
     * Converts the specified amount from the given currency to USD using predefined
     * exchange rates.
     *
     * @param currency The currency from which to convert.
     * @param amount   The amount to convert.
     * @return The converted amount in USD, or null if the currency is not supported
     * or the amount is not positive.
     */
    public Double convertCurrency(String currency, Double amount) {
        if (amount <= 0 || !currencyMap.containsKey(currency)) {
            return null;
        }
        return amount * currencyMap.get(currency);
    }
}
