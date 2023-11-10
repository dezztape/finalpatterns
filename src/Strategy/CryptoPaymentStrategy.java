package Strategy;

public class CryptoPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        // Логика оплаты криптовалютой
        System.out.println("Paid " + amount + " using Cryptocurrency.");
    }
}