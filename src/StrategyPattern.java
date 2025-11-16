// ============================================================
// STRATEGY PATTERN - COMPLETE IMPLEMENTATION
// All classes needed for Strategy Pattern in one file
// ============================================================

// ------------------------------------------------------------
// PaymentStrategy.java - Interface for payment strategies
// This is the core of Strategy Pattern
// ------------------------------------------------------------
interface PaymentStrategy {
    // Process payment for given amount
    // Returns true if payment successful, false otherwise
    boolean pay(double amount);
    
    // Get the name of this payment method
    String getPaymentMethodName();
}

// ------------------------------------------------------------
// CreditCardPayment.java - Concrete Strategy #1
// Implements credit card payment logic
// ------------------------------------------------------------
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cardHolderName;
    private String cvv;
    private String expiryDate;
    
    // Constructor - initializes credit card details
    public CreditCardPayment(String cardNumber, String cardHolderName, String cvv, String expiryDate) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }
    
    @Override
    public boolean pay(double amount) {
        System.out.println("Processing Credit Card Payment...");
        System.out.println("Card Holder: " + cardHolderName);
        System.out.println("Card Number: **** **** **** " + cardNumber.substring(cardNumber.length() - 4));
        System.out.println("Amount: $" + String.format("%.2f", amount));
        
        // Simulate payment processing
        try {
            System.out.println("Connecting to payment gateway...");
            Thread.sleep(500); // Simulate network delay
            System.out.println("Verifying card details...");
            Thread.sleep(500);
            System.out.println("Payment authorized!");
            System.out.println("Transaction ID: CC-" + System.currentTimeMillis());
            return true;
        } catch (InterruptedException e) {
            System.out.println("Payment failed: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public String getPaymentMethodName() {
        return "Credit Card";
    }
}

// ------------------------------------------------------------
// CashPayment.java - Concrete Strategy #2
// Implements cash payment logic
// ------------------------------------------------------------
class CashPayment implements PaymentStrategy {
    private double cashReceived;
    
    // Constructor - takes the amount of cash received
    public CashPayment(double cashReceived) {
        this.cashReceived = cashReceived;
    }
    
    @Override
    public boolean pay(double amount) {
        System.out.println("Processing Cash Payment...");
        System.out.println("Amount Due: $" + String.format("%.2f", amount));
        System.out.println("Cash Received: $" + String.format("%.2f", cashReceived));
        
        if (cashReceived < amount) {
            System.out.println("Insufficient cash! Need $" + String.format("%.2f", amount - cashReceived) + " more.");
            return false;
        }
        
        double change = cashReceived - amount;
        System.out.println("Payment accepted!");
        
        if (change > 0) {
            System.out.println("Change to return: $" + String.format("%.2f", change));
        } else {
            System.out.println("Exact amount received. No change needed.");
        }
        
        System.out.println("Transaction ID: CASH-" + System.currentTimeMillis());
        return true;
    }
    
    @Override
    public String getPaymentMethodName() {
        return "Cash";
    }
}

// ------------------------------------------------------------
// PayPalPayment.java - Concrete Strategy #3
// Implements PayPal payment logic
// ------------------------------------------------------------
class PayPalPayment implements PaymentStrategy {
    private String email;
    private String password;
    
    // Constructor - initializes PayPal account details
    public PayPalPayment(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    @Override
    public boolean pay(double amount) {
        System.out.println("Processing PayPal Payment...");
        System.out.println("PayPal Account: " + email);
        System.out.println("Amount: $" + String.format("%.2f", amount));
        
        // Simulate PayPal authentication and payment
        try {
            System.out.println("Logging into PayPal...");
            Thread.sleep(500); // Simulate network delay
            System.out.println("Verifying account credentials...");
            Thread.sleep(500);
            System.out.println("Processing payment through PayPal...");
            Thread.sleep(500);
            System.out.println("Payment successful!");
            System.out.println("Transaction ID: PP-" + System.currentTimeMillis());
            System.out.println("Receipt sent to: " + email);
            return true;
        } catch (InterruptedException e) {
            System.out.println("Payment failed: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public String getPaymentMethodName() {
        return "PayPal";
    }
}

// ------------------------------------------------------------
// PaymentContext.java - Context class that uses Strategy
// This class allows switching between payment strategies at runtime
// ------------------------------------------------------------
class PaymentContext {
    private PaymentStrategy paymentStrategy;
    
    // Set the payment strategy to use
    // Allows changing payment method dynamically
    public void setPaymentStrategy(PaymentStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("Payment strategy cannot be null");
        }
        this.paymentStrategy = strategy;
        System.out.println("Payment method set to: " + strategy.getPaymentMethodName());
    }
    
    // Execute payment using the current strategy
    // Returns true if payment successful, false otherwise
    public boolean executePayment(double amount) {
        if (paymentStrategy == null) {
            throw new IllegalStateException("Payment strategy not set. Please set a payment method first.");
        }
        
        if (amount <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero");
        }
        
        System.out.println("\n" + "-".repeat(60));
        System.out.println("Executing payment using: " + paymentStrategy.getPaymentMethodName());
        System.out.println("-".repeat(60));
        
        boolean success = paymentStrategy.pay(amount);
        
        if (success) {
            System.out.println("\n✓ Payment completed successfully!");
        } else {
            System.out.println("\n✗ Payment failed!");
        }
        
        return success;
    }
    
    // Get current payment method name
    public String getCurrentPaymentMethod() {
        if (paymentStrategy == null) {
            return "No payment method set";
        }
        return paymentStrategy.getPaymentMethodName();
    }
}

// ------------------------------------------------------------
// Demo class to test Strategy Pattern
// ------------------------------------------------------------
class StrategyPatternDemo {
    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("STRATEGY PATTERN DEMONSTRATION");
        System.out.println("=".repeat(60));
        System.out.println();
        
        // Create payment context
        PaymentContext paymentContext = new PaymentContext();
        
        // Example 1: Pay with Credit Card
        System.out.println("Example 1: Paying with Credit Card");
        System.out.println("=".repeat(60));
        PaymentStrategy creditCard = new CreditCardPayment(
            "1234567812345678",
            "John Doe",
            "123",
            "12/25"
        );
        paymentContext.setPaymentStrategy(creditCard);
        paymentContext.executePayment(45.99);
        System.out.println();
        
        // Example 2: Pay with Cash
        System.out.println("\nExample 2: Paying with Cash");
        System.out.println("=".repeat(60));
        PaymentStrategy cash = new CashPayment(50.00);
        paymentContext.setPaymentStrategy(cash);
        paymentContext.executePayment(45.99);
        System.out.println();
        
        // Example 3: Pay with PayPal
        System.out.println("\nExample 3: Paying with PayPal");
        System.out.println("=".repeat(60));
        PaymentStrategy paypal = new PayPalPayment(
            "customer@email.com",
            "securePassword123"
        );
        paymentContext.setPaymentStrategy(paypal);
        paymentContext.executePayment(45.99);
        System.out.println();
        
        // Example 4: Insufficient cash payment
        System.out.println("\nExample 4: Failed payment - Insufficient cash");
        System.out.println("=".repeat(60));
        PaymentStrategy insufficientCash = new CashPayment(30.00);
        paymentContext.setPaymentStrategy(insufficientCash);
        paymentContext.executePayment(45.99);
        System.out.println();
        
        System.out.println("=".repeat(60));
        System.out.println("Strategy Pattern successfully demonstrated different payment methods!");
        System.out.println("The same payment operation used different algorithms at runtime.");
        System.out.println("=".repeat(60));
    }
}
