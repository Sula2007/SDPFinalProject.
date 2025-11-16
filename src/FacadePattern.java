// ============================================================
// FACADE PATTERN - COMPLETE IMPLEMENTATION
// All classes needed for Facade Pattern in one file
// ============================================================

// NOTE: This file demonstrates Facade pattern independently.
// In the integrated Main.java, RestaurantFacade will use the actual
// Factory, Decorator, Observer, and Strategy classes from other files.

// ------------------------------------------------------------
// Simplified subsystem classes for demonstration
// In real integration, these will be replaced with actual implementations
// ------------------------------------------------------------

// Simple Meal interface for demo
interface SimpleMeal {
    String getDescription();
    double getPrice();
}

// Simple meal implementation
class SimplePizza implements SimpleMeal {
    public String getDescription() {
        return "Margherita Pizza";
    }
    
    public double getPrice() {
        return 12.99;
    }
}

// Simple factory for demo
class SimpleMealFactory {
    public static SimpleMeal createMeal(String type) {
        return new SimplePizza();
    }
}

// Simple decorator for demo
class SimpleMealDecorator implements SimpleMeal {
    private SimpleMeal meal;
    
    public SimpleMealDecorator(SimpleMeal meal) {
        this.meal = meal;
    }
    
    public String getDescription() {
        return meal.getDescription() + " with extra cheese";
    }
    
    public double getPrice() {
        return meal.getPrice() + 1.50;
    }
}

// Simple observer for demo
class SimpleObserver {
    private String name;
    
    public SimpleObserver(String name) {
        this.name = name;
    }
    
    public void notifyStatusChange(String status) {
        System.out.println("  [" + name + "] Order status: " + status);
    }
}

// Simple payment strategy for demo
class SimplePaymentStrategy {
    private String method;
    
    public SimplePaymentStrategy(String method) {
        this.method = method;
    }
    
    public boolean processPayment(double amount) {
        System.out.println("  Processing " + method + " payment: $" + String.format("%.2f", amount));
        return true;
    }
}

// ------------------------------------------------------------
// RestaurantFacade.java - Facade class
// This is the core of Facade Pattern
// Provides a simplified interface to complex subsystems
// ------------------------------------------------------------
class RestaurantFacade {
    // References to subsystems
    // In real implementation, these would be actual Factory, Observer, Strategy, etc.
    
    // Constructor - initializes all subsystems
    public RestaurantFacade() {
        System.out.println("Restaurant Facade initialized");
        System.out.println("All subsystems ready (Factory, Decorator, Observer, Strategy)");
    }
    
    // High-level method that uses multiple subsystems
    // This is the simplified interface provided by Facade
    // Hides the complexity of coordinating multiple design patterns
    public void placeOrder(String customerName, String mealType, boolean addExtras, String paymentMethod) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("RESTAURANT FACADE - PROCESSING ORDER");
        System.out.println("=".repeat(60));
        System.out.println("Customer: " + customerName);
        System.out.println("Meal: " + mealType);
        System.out.println("Add extras: " + addExtras);
        System.out.println("Payment: " + paymentMethod);
        System.out.println("-".repeat(60));
        
        try {
            // Step 1: Create meal using Factory Pattern
            System.out.println("\n[Step 1] Creating meal using Factory Pattern...");
            SimpleMeal meal = SimpleMealFactory.createMeal(mealType);
            System.out.println("  ✓ " + meal.getDescription() + " created");
            
            Thread.sleep(300);
            
            // Step 2: Add extras using Decorator Pattern (if requested)
            if (addExtras) {
                System.out.println("\n[Step 2] Adding extras using Decorator Pattern...");
                meal = new SimpleMealDecorator(meal);
                System.out.println("  ✓ Extras added: " + meal.getDescription());
            } else {
                System.out.println("\n[Step 2] No extras requested, skipping decoration");
            }
            
            Thread.sleep(300);
            
            // Step 3: Create order and set up notifications using Observer Pattern
            System.out.println("\n[Step 3] Setting up notifications using Observer Pattern...");
            SimpleObserver kitchen = new SimpleObserver("Kitchen");
            SimpleObserver customer = new SimpleObserver("Customer");
            SimpleObserver waiter = new SimpleObserver("Waiter");
            System.out.println("  ✓ Observers attached: Kitchen, Customer, Waiter");
            
            Thread.sleep(300);
            
            // Step 4: Notify order confirmed
            System.out.println("\n[Step 4] Notifying all observers - Order Confirmed...");
            kitchen.notifyStatusChange("Order confirmed - preparing");
            customer.notifyStatusChange("Your order is confirmed!");
            waiter.notifyStatusChange("Order confirmed for " + customerName);
            
            Thread.sleep(500);
            
            // Step 5: Process payment using Strategy Pattern
            System.out.println("\n[Step 5] Processing payment using Strategy Pattern...");
            SimplePaymentStrategy payment = new SimplePaymentStrategy(paymentMethod);
            double totalAmount = meal.getPrice();
            boolean paymentSuccess = payment.processPayment(totalAmount);
            
            if (paymentSuccess) {
                System.out.println("  ✓ Payment successful!");
            } else {
                System.out.println("  ✗ Payment failed!");
                return;
            }
            
            Thread.sleep(300);
            
            // Step 6: Notify order complete
            System.out.println("\n[Step 6] Notifying all observers - Order Complete...");
            kitchen.notifyStatusChange("Order ready for pickup");
            customer.notifyStatusChange("Your order is ready!");
            waiter.notifyStatusChange("Ready to serve to " + customerName);
            
            System.out.println("\n" + "=".repeat(60));
            System.out.println("ORDER SUCCESSFULLY PLACED!");
            System.out.println("=".repeat(60));
            System.out.println("Order Summary:");
            System.out.println("  Meal: " + meal.getDescription());
            System.out.println("  Total: $" + String.format("%.2f", totalAmount));
            System.out.println("  Payment: " + paymentMethod);
            System.out.println("  Status: Ready for pickup");
            System.out.println("=".repeat(60));
            
        } catch (InterruptedException e) {
            System.out.println("Error processing order: " + e.getMessage());
        }
    }
    
    // Another high-level method - cancel order
    public void cancelOrder(int orderId) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("CANCELLING ORDER #" + orderId);
        System.out.println("=".repeat(60));
        
        System.out.println("  → Notifying kitchen to stop preparation");
        System.out.println("  → Refunding payment");
        System.out.println("  → Notifying customer about cancellation");
        System.out.println("  ✓ Order #" + orderId + " successfully cancelled");
        
        System.out.println("=".repeat(60));
    }
    
    // Another high-level method - view order status
    public void viewOrderStatus(int orderId) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("ORDER STATUS - Order #" + orderId);
        System.out.println("=".repeat(60));
        
        System.out.println("  Status: In Progress");
        System.out.println("  Estimated time: 15 minutes");
        System.out.println("  Items: Pizza with extras");
        System.out.println("  Total: $14.49");
        
        System.out.println("=".repeat(60));
    }
}

// ------------------------------------------------------------
// Demo class to test Facade Pattern
// ------------------------------------------------------------
class FacadePatternDemo {
    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("FACADE PATTERN DEMONSTRATION");
        System.out.println("=".repeat(60));
        System.out.println();
        
        System.out.println("What is Facade Pattern?");
        System.out.println("-".repeat(60));
        System.out.println("Facade provides a simplified interface to a complex subsystem.");
        System.out.println("Instead of client interacting with multiple classes directly,");
        System.out.println("they use a single Facade class that coordinates everything.");
        System.out.println();
        
        System.out.println("Benefits:");
        System.out.println("  ✓ Simplifies complex systems");
        System.out.println("  ✓ Reduces dependencies between client and subsystems");
        System.out.println("  ✓ Provides a clean, easy-to-use interface");
        System.out.println("  ✓ Makes the system easier to understand and use");
        System.out.println();
        
        // Create facade
        System.out.println("Creating Restaurant Facade...");
        System.out.println("=".repeat(60));
        RestaurantFacade restaurant = new RestaurantFacade();
        System.out.println();
        
        // Example 1: Place order with extras
        System.out.println("\n" + "=".repeat(60));
        System.out.println("EXAMPLE 1: Complete order with extras");
        System.out.println("=".repeat(60));
        
        // Without facade, client would need to:
        // 1. Call MealFactory.createMeal()
        // 2. Wrap with decorators manually
        // 3. Create Order object
        // 4. Attach observers manually
        // 5. Call payment strategy
        // 6. Notify all observers
        
        // With facade, just one simple method call:
        restaurant.placeOrder("John Smith", "Pizza", true, "Credit Card");
        
        System.out.println("\n\n");
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Example 2: Place order without extras
        System.out.println("=".repeat(60));
        System.out.println("EXAMPLE 2: Simple order without extras");
        System.out.println("=".repeat(60));
        
        restaurant.placeOrder("Jane Doe", "Burger", false, "PayPal");
        
        System.out.println("\n\n");
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Example 3: Cancel order
        System.out.println("=".repeat(60));
        System.out.println("EXAMPLE 3: Cancel order");
        System.out.println("=".repeat(60));
        
        restaurant.cancelOrder(1001);
        
        System.out.println("\n");
        
        // Example 4: View order status
        System.out.println("=".repeat(60));
        System.out.println("EXAMPLE 4: View order status");
        System.out.println("=".repeat(60));
        
        restaurant.viewOrderStatus(1002);
        
        System.out.println("\n\n");
        
        // Summary
        System.out.println("=".repeat(60));
        System.out.println("FACADE PATTERN SUMMARY");
        System.out.println("=".repeat(60));
        System.out.println();
        System.out.println("Without Facade:");
        System.out.println("  Client code must:");
        System.out.println("    1. Understand Factory Pattern");
        System.out.println("    2. Understand Decorator Pattern");
        System.out.println("    3. Understand Observer Pattern");
        System.out.println("    4. Understand Strategy Pattern");
        System.out.println("    5. Coordinate all patterns manually");
        System.out.println("    6. Handle errors from each subsystem");
        System.out.println();
        System.out.println("With Facade:");
        System.out.println("  Client code simply calls:");
        System.out.println("    restaurant.placeOrder(name, meal, extras, payment);");
        System.out.println();
        System.out.println("✓ Facade Pattern successfully simplified the complex ordering system!");
        System.out.println("=".repeat(60));
    }
}
