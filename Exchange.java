/**
 * Description: Represents a currency exchange system with predefined exchange rates.
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
     * Enum representing different currencies.
     */
    public enum Currency {
        USD, JPY, THB, MYR
    }

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
     * - JPY: 111.67136
     * - THB: 26.69023
     * - MYR: 3.5286746
     */
    public Exchange() {
        currencyMap = new HashMap<>();
        currencyMap.put(Currency.USD, 0.68653311);
        currencyMap.put(Currency.JPY, 111.67136);
        currencyMap.put(Currency.THB, 26.69023);
        currencyMap.put(Currency.MYR, 3.5286746);
    }

    /**
     * Retrieves and prints the exchange rate for a given currency.
     *
     * @param c The currency for which the exchange rate is requested.
     */
    public void getRate(Currency c) {
        if (currencyMap.containsKey(c)) {
            System.out.println("Exchange rate for " + c + ": " + currencyMap.get(c));
        } else {
            System.out.println("Exchange rate not available for " + c);
        }
    }
}
