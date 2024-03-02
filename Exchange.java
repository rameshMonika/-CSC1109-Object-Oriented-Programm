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

    public static void main(String[] args) {
        Exchange exchange = new Exchange();
        exchange.getRate(Currency.MYR);
    }
}


