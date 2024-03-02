import java.util.HashMap;
import java.util.Map;

/** The Exchange class represents a currency exchange system with predefined exchange rates. */
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
     * Default constructor that initializes the Exchange object with predefined exchange rates.
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
