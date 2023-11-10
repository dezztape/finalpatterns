package Strategy;

public class BankTransferPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        // Логика оплаты банковским переводом
        System.out.println("Paid " + amount + " using Bank Transfer.");
    }
}