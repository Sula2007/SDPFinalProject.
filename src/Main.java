// ============================================================
// MAIN.JAVA - INTEGRATION OF ALL 6 DESIGN PATTERNS
// Complete Restaurant Ordering System
// ============================================================
// 
// This demonstrates how all 6 design patterns work together:
//
// STUDENT 1 PATTERNS:
//   1. Factory Pattern - Creates different meal types
//   2. Builder Pattern - Builds custom pizzas
//   3. Decorator Pattern - Adds toppings to meals
//
// STUDENT 2 PATTERNS:
//   4. Strategy Pattern - Handles different payment methods
//   5. Observer Pattern - Notifies about order status
//   6. Facade Pattern - Simplifies the entire ordering process
//
// ============================================================

public class Main {
    
    public static void main(String[] args) {
        System.out.println("╔" + "=".repeat(58) + "╗");
        System.out.println("║" + " ".repeat(10) + "RESTAURANT ORDERING SYSTEM" + " ".repeat(21) + "║");
        System.out.println("║" + " ".repeat(8) + "Demonstrating 6 Design Patterns" + " ".repeat(18) + "║");
        System.out.println("╚" + "=".repeat(58) + "╝");
        System.out.println();
        
        // Display pattern overview
        displayPatternOverview();
        
        System.out.println("\n" + "═".repeat(60));
        System.out.println("STARTING COMPLETE ORDER WORKFLOW");
        System.out.println("═".repeat(60));
        
        pauseForEffect(800);
        
        // ========================================
        // SCENARIO 1: Simple Order with Factory
        // ========================================
        System.out.println("\n" + "┌" + "─".repeat(58) + "┐");
        System.out.println("│ SCENARIO 1: Simple Order Using Factory Pattern" + " ".repeat(11) + "│");
        System.out.println("└" + "─".repeat(58) + "┘");
        
        // Pattern 1: FACTORY - Create a basic meal
        System.out.println("\n[Pattern 1: FACTORY] Creating meal...");
        Meal basicBurger = MealFactory.createMeal("Burger");
        System.out.println("✓ Created: " + basicBurger.getDescription());
        System.out.println("  Price: $" + basicBurger.getPrice());
        
        pauseForEffect(500);
        
        // ========================================
        // SCENARIO 2: Enhanced Order with Decorator
        // ========================================
        System.out.println("\n" + "┌" + "─".repeat(58) + "┐");
        System.out.println("│ SCENARIO 2: Enhanced Order Using Decorator Pattern" + " ".repeat(6) + "│");
        System.out.println("└" + "─".repeat(58) + "┘");
        
        // Pattern 3: DECORATOR - Add toppings dynamically
        System.out.println("\n[Pattern 3: DECORATOR] Adding toppings...");
        Meal deluxeBurger = new CheeseDecorator(basicBurger);
        deluxeBurger = new BaconDecorator(deluxeBurger);
        System.out.println("✓ Enhanced: " + deluxeBurger.getDescription());
        System.out.println("  New Price: $" + deluxeBurger.getPrice());
        
        pauseForEffect(500);
        
        // ========================================
        // SCENARIO 3: Custom Pizza with Builder
        // ========================================
        System.out.println("\n" + "┌" + "─".repeat(58) + "┐");
        System.out.println("│ SCENARIO 3: Custom Pizza Using Builder Pattern" + " ".repeat(11) + "│");
        System.out.println("└" + "─".repeat(58) + "┘");
        
        // Pattern 2: BUILDER - Build complex custom pizza
        System.out.println("\n[Pattern 2: BUILDER] Building custom pizza...");
        CustomPizza customPizza = new PizzaBuilder()
                .setDough("Thin Crust")
                .setSauce("BBQ Sauce")
                .addTopping("Chicken")
                .addTopping("Mushrooms")
                .addTopping("Onions")
                .build();
        
        System.out.println("✓ Built: " + customPizza.getDescription());
        System.out.println("  Price: $" + customPizza.getPrice());
        
        pauseForEffect(500);
        
        // ========================================
        // SCENARIO 4: Order with Observer Notifications
        // ========================================
        System.out.println("\n" + "┌" + "─".repeat(58) + "┐");
        System.out.println("│ SCENARIO 4: Order Tracking Using Observer Pattern" + " ".repeat(8) + "│");
        System.out.println("└" + "─".repeat(58) + "┘");
        
        // Pattern 5: OBSERVER - Set up order tracking
        System.out.println("\n[Pattern 5: OBSERVER] Setting up order tracking...");
        Order order1 = new Order(1001, "Alice Johnson");
        order1.addItem(deluxeBurger.getDescription());
        order1.addItem("Coca Cola");
        
        // Attach observers
        Observer kitchen = new Kitchen("Main Kitchen");
        Observer customer = new Customer("Alice Johnson", "+1-555-0123");
        Observer waiter = new Waiter("Bob", 5);
        
        order1.attach(kitchen);
        order1.attach(customer);
        order1.attach(waiter);
        
        System.out.println("✓ Observers attached: Kitchen, Customer, Waiter");
        
        pauseForEffect(800);
        
        // Simulate order status changes
        System.out.println("\nSimulating order workflow...");
        order1.setStatus("Confirmed");
        pauseForEffect(1000);
        
        order1.setStatus("Preparing");
        pauseForEffect(1000);
        
        order1.setStatus("Ready");
        pauseForEffect(500);
        
        // ========================================
        // SCENARIO 5: Payment with Strategy
        // ========================================
        System.out.println("\n" + "┌" + "─".repeat(58) + "┐");
        System.out.println("│ SCENARIO 5: Payment Using Strategy Pattern" + " ".repeat(15) + "│");
        System.out.println("└" + "─".repeat(58) + "┘");
        
        // Pattern 4: STRATEGY - Process payment
        System.out.println("\n[Pattern 4: STRATEGY] Processing payment...");
        PaymentContext paymentContext = new PaymentContext();
        
        double totalAmount = deluxeBurger.getPrice() + 2.50; // + drink
        
        // Try different payment strategies
        PaymentStrategy creditCard = new CreditCardPayment(
            "4532123456789012",
            "Alice Johnson",
            "123",
            "12/25"
        );
        
        paymentContext.setPaymentStrategy(creditCard);
        boolean paymentSuccess = paymentContext.executePayment(totalAmount);
        
        if (paymentSuccess) {
            order1.setStatus("Paid");
            pauseForEffect(500);
        }
        
        // ========================================
        // SCENARIO 6: Complete Flow with Facade
        // ========================================
        System.out.println("\n" + "┌" + "─".repeat(58) + "┐");
        System.out.println("│ SCENARIO 6: Simplified Ordering Using Facade Pattern" + " ".repeat(5) + "│");
        System.out.println("└" + "─".repeat(58) + "┘");
        
        System.out.println("\nInstead of manually coordinating all patterns,");
        System.out.println("the Facade pattern provides a simple interface:");
        System.out.println();
        
        // Pattern 6: FACADE - Simplified interface for complex operations
        System.out.println("[Pattern 6: FACADE] Using RestaurantFacade for complete order...");
        System.out.println("-".repeat(60));
        
        // Create integrated facade (this would use real implementations)
        IntegratedRestaurantFacade facade = new IntegratedRestaurantFacade();
        
        System.out.println("\nCalling: facade.placeCompleteOrder(...)");
        System.out.println("This ONE method internally uses ALL 6 patterns!\n");
        
        facade.placeCompleteOrder(
            "Charlie Brown",           // customer name
            "Pizza",                    // meal type (Factory)
            true,                       // add decorations (Decorator)  
            "Thin Crust",              // pizza dough (Builder)
            "Pepperoni",               // pizza topping (Builder)
            "PayPal"                   // payment method (Strategy)
        );
        
        pauseForEffect(1000);
        
        // ========================================
        // FINAL SUMMARY
        // ========================================
        System.out.println("\n\n" + "╔" + "═".repeat(58) + "╗");
        System.out.println("║" + " ".repeat(18) + "FINAL SUMMARY" + " ".repeat(27) + "║");
        System.out.println("╚" + "═".repeat(58) + "╝");
        
        displayFinalSummary();
        
        System.out.println("\n" + "═".repeat(60));
        System.out.println("ALL 6 DESIGN PATTERNS SUCCESSFULLY DEMONSTRATED!");
        System.out.println("═".repeat(60));
    }
    
    // ========================================
    // Helper Methods
    // ========================================
    
    private static void displayPatternOverview() {
        System.out.println("┌" + "─".repeat(58) + "┐");
        System.out.println("│ PATTERN OVERVIEW" + " ".repeat(41) + "│");
        System.out.println("├" + "─".repeat(58) + "┤");
        System.out.println("│ Student 1 Patterns:" + " ".repeat(39) + "│");
        System.out.println("│   1. Factory   - Creates meal objects" + " ".repeat(20) + "│");
        System.out.println("│   2. Builder   - Builds custom pizzas" + " ".repeat(20) + "│");
        System.out.println("│   3. Decorator - Adds toppings dynamically" + " ".repeat(16) + "│");
        System.out.println("├" + "─".repeat(58) + "┤");
        System.out.println("│ Student 2 Patterns:" + " ".repeat(39) + "│");
        System.out.println("│   4. Strategy  - Different payment methods" + " ".repeat(16) + "│");
        System.out.println("│   5. Observer  - Order status notifications" + " ".repeat(15) + "│");
        System.out.println("│   6. Facade    - Simplifies entire process" + " ".repeat(16) + "│");
        System.out.println("└" + "─".repeat(58) + "┘");
    }
    
    private static void displayFinalSummary() {
        System.out.println();
        System.out.println("Pattern Usage Summary:");
        System.out.println("-".repeat(60));
        System.out.println("✓ Factory Pattern   : Created Burger and Pizza objects");
        System.out.println("✓ Builder Pattern   : Built custom pizza step-by-step");
        System.out.println("✓ Decorator Pattern : Added Cheese and Bacon toppings");
        System.out.println("✓ Strategy Pattern  : Processed Credit Card payment");
        System.out.println("✓ Observer Pattern  : Notified Kitchen, Customer, Waiter");
        System.out.println("✓ Facade Pattern    : Simplified entire ordering process");
        System.out.println();
        System.out.println("Pattern Interactions:");
        System.out.println("-".repeat(60));
        System.out.println("1. Factory creates base meal");
        System.out.println("2. Decorator enhances the meal");
        System.out.println("3. Builder constructs custom items");
        System.out.println("4. Observer tracks order through lifecycle");
        System.out.println("5. Strategy handles payment processing");
        System.out.println("6. Facade coordinates all patterns seamlessly");
    }
    
    private static void pauseForEffect(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// ============================================================
// IntegratedRestaurantFacade - Uses ALL 6 patterns together
// ============================================================
class IntegratedRestaurantFacade {
    
    public void placeCompleteOrder(String customerName, String mealType, 
                                   boolean addExtras, String dough, 
                                   String topping, String paymentMethod) {
        
        System.out.println("Processing complete order for: " + customerName);
        System.out.println("-".repeat(60));
        
        try {
            // Step 1: Use Factory to create meal
            System.out.println("\n→ Using Factory Pattern to create " + mealType);
            Meal meal = MealFactory.createMeal(mealType);
            System.out.println("  ✓ Base meal created: " + meal.getDescription());
            Thread.sleep(300);
            
            // Step 2: Use Decorator if extras requested
            if (addExtras) {
                System.out.println("\n→ Using Decorator Pattern to add extras");
                meal = new CheeseDecorator(meal);
                System.out.println("  ✓ Enhanced: " + meal.getDescription());
                Thread.sleep(300);
            }
            
            // Step 3: Use Builder for custom pizza
            System.out.println("\n→ Using Builder Pattern for custom pizza");
            CustomPizza customPizza = new PizzaBuilder()
                    .setDough(dough)
                    .setSauce("Tomato Sauce")
                    .addTopping(topping)
                    .build();
            System.out.println("  ✓ Custom pizza ready");
            Thread.sleep(300);
            
            // Step 4: Use Observer for notifications
            System.out.println("\n→ Using Observer Pattern to set up notifications");
            Order order = new Order(2001, customerName);
            order.addItem(meal.getDescription());
            order.addItem(customPizza.getDescription());
            
            Observer kitchen = new Kitchen("Kitchen");
            Observer customer = new Customer(customerName, "+1-555-9999");
            
            order.attach(kitchen);
            order.attach(customer);
            System.out.println("  ✓ Observers attached");
            Thread.sleep(300);
            
            order.setStatus("Confirmed");
            Thread.sleep(500);
            
            // Step 5: Use Strategy for payment
            System.out.println("\n→ Using Strategy Pattern for payment");
            PaymentContext payment = new PaymentContext();
            
            PaymentStrategy strategy;
            if (paymentMethod.equals("PayPal")) {
                strategy = new PayPalPayment("customer@email.com", "pass123");
            } else {
                strategy = new CreditCardPayment("4111111111111111", customerName, "123", "12/26");
            }
            
            payment.setPaymentStrategy(strategy);
            double total = meal.getPrice() + customPizza.getPrice();
            payment.executePayment(total);
            Thread.sleep(300);
            
            order.setStatus("Paid");
            Thread.sleep(300);
            order.setStatus("Ready");
            
            System.out.println("\n" + "=".repeat(60));
            System.out.println("✓ ORDER COMPLETE! All 6 patterns worked together.");
            System.out.println("=".repeat(60));
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
