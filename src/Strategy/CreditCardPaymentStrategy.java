package Strategy;

public class CreditCardPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        // Логика оплаты кредитной картой
        System.out.println("Paid " + amount + " using Credit Card.");
    }
}
