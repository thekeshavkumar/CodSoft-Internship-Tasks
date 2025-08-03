import java.util.Scanner;
import java.util.HashMap;

public class CurrencyConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] currencies = {"USD", "EUR", "GBP", "INR", "JPY"};

        System.out.println("Available currencies:");
        for (String c : currencies) {
            System.out.print(c + " ");
        }
        System.out.println();

        System.out.print("Enter base currency: ");
        String base = scanner.nextLine().toUpperCase();

        System.out.print("Enter target currency: ");
        String target = scanner.nextLine().toUpperCase();

        if (!isValidCurrency(base, currencies) || !isValidCurrency(target, currencies)) {
            System.out.println("Invalid currency code.");
            scanner.close();
            return;
        }

        System.out.print("Enter amount to convert: ");
        double amount = scanner.nextDouble();

        double rate = getExchangeRate(base, target);
        double convertedAmount = amount * rate;

        System.out.printf("%.2f %s = %.2f %s\n", amount, base, convertedAmount, target);

        scanner.close();
    }

    public static boolean isValidCurrency(String currency, String[] validCurrencies) {
        for (String c : validCurrencies) {
            if (c.equals(currency)) {
                return true;
            }
        }
        return false;
    }

    public static double getExchangeRate(String base, String target) {
        HashMap<String, Double> ratesPerUSD = new HashMap<>();
        ratesPerUSD.put("USD", 1.0);
        ratesPerUSD.put("EUR", 0.9);
        ratesPerUSD.put("GBP", 0.77);
        ratesPerUSD.put("INR", 75.0);
        ratesPerUSD.put("JPY", 133.0);

        double basePerUSD = ratesPerUSD.get(base);
        double targetPerUSD = ratesPerUSD.get(target);

        return targetPerUSD / basePerUSD;
    }
}
