// ============================================================
// FILE: src/strategy/PaymentStrategy.java
// ============================================================
package strategy;

/**
 * PaymentStrategy interface - Strategy Pattern
 * Defines contract for different payment algorithms
 */
public interface PaymentStrategy {
    /**
     * Process payment for given amount
     * @param amount Amount to pay
     * @return true if payment successful, false otherwise
     */
    boolean pay(double amount);
    
    /**
     * Get the name of this payment method
     * @return Payment method name
     */
    String getPaymentMethodName();
}

// ============================================================
// FILE: src/strategy/CreditCardPayment.java
// ============================================================
package strategy;

/**
 * CreditCardPayment - Concrete strategy for credit card payments
 * Implements credit card payment processing logic
 */
public class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cardHolderName;
    private String cvv;
    private String expiryDate;
    
    /**
     * Constructor - initializes credit card details
     * @param cardNumber Card number
     * @param cardHolderName Name on card
     * @param cvv CVV code
     * @param expiryDate Expiry date (MM/YY)
     */
    public CreditCardPayment(String cardNumber, String cardHolderName, 
                            String cvv, String expiryDate) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }
    
    @Override
    public boolean pay(double amount) {
        System.out.println("Processing Credit Card Payment...");
        System.out.println("Card Holder: " + cardHolderName);
        System.out.println("Card Number: **** **** **** " + 
                          cardNumber.substring(cardNumber.length() - 4));
        System.out.println("Amount: $" + String.format("%.2f", amount));
        
        try {
            System.out.println("Connecting to payment gateway...");
            Thread.sleep(500);
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

// ============================================================
// FILE: src/strategy/CashPayment.java
// ============================================================
package strategy;

/**
 * CashPayment - Concrete strategy for cash payments
 * Implements cash payment processing logic with change calculation
 */
public class CashPayment implements PaymentStrategy {
    private double cashReceived;
    
    /**
     * Constructor - takes the amount of cash received
     * @param cashReceived Amount of cash given by customer
     */
    public CashPayment(double cashReceived) {
        this.cashReceived = cashReceived;
    }
    
    @Override
    public boolean pay(double amount) {
        System.out.println("Processing Cash Payment...");
        System.out.println("Amount Due: $" + String.format("%.2f", amount));
        System.out.println("Cash Received: $" + String.format("%.2f", cashReceived));
        
        if (cashReceived < amount) {
            System.out.println("Insufficient cash! Need $" + 
                             String.format("%.2f", amount - cashReceived) + " more.");
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

// ============================================================
// FILE: src/strategy/PayPalPayment.java
// ============================================================
package strategy;

/**
 * PayPalPayment - Concrete strategy for PayPal payments
 * Implements PayPal payment processing logic
 */
public class PayPalPayment implements PaymentStrategy {
    private String email;
    private String password;
    
    /**
     * Constructor - initializes PayPal account details
     * @param email PayPal account email
     * @param password PayPal account password
     */
    public PayPalPayment(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    @Override
    public boolean pay(double amount) {
        System.out.println("Processing PayPal Payment...");
        System.out.println("PayPal Account: " + email);
        System.out.println("Amount: $" + String.format("%.2f", amount));
        
        try {
            System.out.println("Logging into PayPal...");
            Thread.sleep(500);
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

// ============================================================
// FILE: src/strategy/PaymentContext.java
// ============================================================
package strategy;

/**
 * PaymentContext - Context class for Strategy Pattern
 * Allows switching between different payment strategies at runtime
 */
public class PaymentContext {
    private PaymentStrategy paymentStrategy;
    
    /**
     * Set the payment strategy to use
     * @param strategy Payment strategy to use
     * @throws IllegalArgumentException if strategy is null
     */
    public void setPaymentStrategy(PaymentStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("Payment strategy cannot be null");
        }
        this.paymentStrategy = strategy;
        System.out.println("Payment method set to: " + strategy.getPaymentMethodName());
    }
    
    /**
     * Execute payment using the current strategy
     * @param amount Amount to pay
     * @return true if payment successful, false otherwise
     * @throws IllegalStateException if payment strategy not set
     * @throws IllegalArgumentException if amount is invalid
     */
    public boolean executePayment(double amount) {
        if (paymentStrategy == null) {
            throw new IllegalStateException(
                "Payment strategy not set. Please set a payment method first.");
        }
        
        if (amount <= 0) {
            throw new IllegalArgumentException("Payment amount must be greater than zero");
        }
        
        System.out.println("\n" + "-".repeat(60));
        System.out.println("Executing payment using: " + 
                          paymentStrategy.getPaymentMethodName());
        System.out.println("-".repeat(60));
        
        boolean success = paymentStrategy.pay(amount);
        
        if (success) {
            System.out.println("\n✓ Payment completed successfully!");
        } else {
            System.out.println("\n✗ Payment failed!");
        }
        
        return success;
    }
    
    /**
     * Get current payment method name
     * @return Payment method name or "No payment method set"
     */
    public String getCurrentPaymentMethod() {
        if (paymentStrategy == null) {
            return "No payment method set";
        }
        return paymentStrategy.getPaymentMethodName();
    }
}
