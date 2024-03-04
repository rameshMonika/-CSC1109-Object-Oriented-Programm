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
    private Map<Currency, Double> currencyMap;

    /**
     * Default constructor that initializes the Exchange object with predefined
     * exchange rates.
     * <p>
     * The default constructor initializes the exchange rates for four different
     * currencies: USD, JPY, THB, and MYR.
     * The exchange rates are set as follows:
     * - USD: 0.68653311
     * - EUR: 0.69
     * - JPY: 111.67136
     * - MYR: 3.5286746
     */
    public Exchange() {
        currencyMap = new HashMap<>();
        this.currencyMap.put(Currency.USD, 0.68653311);
        this.currencyMap.put(Currency.EUR, 0.69);
        this.currencyMap.put(Currency.JPY, 111.67136);
        this.currencyMap.put(Currency.MYR, 3.5286746);
    }

    /**
     * Retrieves and prints the exchange rate for a given currency.
     *
     * @param c The currency for which the exchange rate is requested.
     * @return The exchange rate for the given currency, or 0 if the currency is not
     */
    public double getRate(Currency c) {
        if (currencyMap.containsKey(c)) {
            System.out.println("Exchange rate for " + c + ": " + currencyMap.get(c));
            return currencyMap.get(c);
        } else {
            System.out.println("Exchange rate not available for " + c);
            return 0;
        }
    }

    /**
     * Converts the specified amount from the given currency to USD using predefined
     * exchange rates.
     *
     * @param currency The currency from which to convert.
     * @param amount   The amount to convert.
     * @return The converted amount in USD, or null if the currency is not supported
     *         or the amount is not positive.
     */
    public Double convertCurrency(Currency currency, Double amount) {
        if (amount <= 0 || !currencyMap.containsKey(currency)) {
            return null;
        }
        return amount * currencyMap.get(currency);
    }
}
