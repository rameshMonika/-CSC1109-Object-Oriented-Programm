import java.util.HashMap;
import java.util.Map;

public class Exchange {
    public enum Currency {
        USD, JPY, THB, MYR
    }

    Map<Currency, Double> currencyMap;

    public Exchange() {
        currencyMap = new HashMap<>();
        currencyMap.put(Currency.USD, 0.68653311);
        currencyMap.put(Currency.JPY, 111.67136);
        currencyMap.put(Currency.THB, 26.69023);
        currencyMap.put(Currency.MYR, 3.5286746);
    }

    void getRate(Currency c) {
        if (currencyMap.containsKey(c)) {
            System.out.println("Exchange rate for " + c + ": " + currencyMap.get(c));
        } else {
            System.out.println("Exchange rate not available for " + c);
        }
    }

    // (Optional) - to show all currencies 
    void printAllCurrencies() {
        System.out.println("Exchange Rates:");
        for (Map.Entry<Currency, Double> entry : currencyMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    // public static void main(String[] args) {
    // Exchange exchange = new Exchange();
    // exchange.getRate(Currency.MYR);
    // }
}
