# Restaurant Ordering System

[![Java](https://img.shields.io/badge/Java-11+-orange.svg)](https://www.oracle.com/java/)
[![Design Patterns](https://img.shields.io/badge/Design%20Patterns-6-blue.svg)]()
[![License](https://img.shields.io/badge/License-MIT-green.svg)]()

> A comprehensive Java demonstration of six fundamental design patterns working together in a cohesive restaurant management system.

## 📋 Table of Contents

- [Overview](#overview)
- [Design Patterns](#design-patterns)
- [SOLID Principles](#solid-principles)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Usage Examples](#usage-examples)
- [UML Diagrams](#uml-diagrams)
- [Future Enhancements](#future-enhancements)
- [Contributors](#contributors)

---

## 🎯 Overview

The **Restaurant Ordering System** is an educational Java application that demonstrates the practical implementation and integration of six classic object-oriented design patterns. It models a complete restaurant workflow including meal creation, order customization, status tracking, payment processing, and simplified ordering interface.

### Key Features

- **🍕 Flexible Meal Creation** - Create different meal types (Pizza, Burger, Salad) using Factory Pattern
- **🔨 Custom Pizza Builder** - Build complex custom pizzas step-by-step with Builder Pattern
- **🎨 Dynamic Enhancements** - Add toppings and extras dynamically using Decorator Pattern
- **💳 Multiple Payment Methods** - Support various payment strategies (Credit Card, Cash, PayPal)
- **🔔 Real-time Notifications** - Track order status with Observer Pattern notifications
- **🎭 Simplified Interface** - One-call ordering using Facade Pattern

### Why This Project?

This project serves as:
- ✅ **Educational Resource** - Learn design patterns through practical examples
- ✅ **Best Practices Demonstration** - See SOLID principles in action
- ✅ **Integration Example** - Understand how patterns work together
- ✅ **Code Quality Reference** - Study clean, maintainable code structure

---

## 🎨 Design Patterns

### 1. Factory Pattern (Creational)

**Intent:** Define an interface for creating objects, but let subclasses decide which class to instantiate.

**Implementation:**
- `MealFactory` creates different meal types based on string input
- Returns `Meal` interface, hiding concrete implementations
- Easy to extend with new meal types

```java
// Usage
Meal pizza = MealFactory.createMeal("Pizza");
Meal burger = MealFactory.createMeal("Burger");
```

**Benefits:**
- Centralized object creation
- Loose coupling between client and concrete classes
- Easy addition of new meal types

---

### 2. Builder Pattern (Creational)

**Intent:** Separate the construction of a complex object from its representation, allowing step-by-step creation.

**Implementation:**
- `PizzaBuilder` constructs `CustomPizza` with fluent interface
- Method chaining for readable code
- Validation in `build()` method

```java
// Usage
CustomPizza pizza = new PizzaBuilder()
    .setDough("Thin Crust")
    .setSauce("BBQ Sauce")
    .addTopping("Chicken")
    .addTopping("Mushrooms")
    .build();
```

**Benefits:**
- Readable, self-documenting code
- Flexible construction process
- Immutable product after construction

---

### 3. Decorator Pattern (Structural)

**Intent:** Attach additional responsibilities to objects dynamically without modifying their structure.

**Implementation:**
- `MealDecorator` wraps `Meal` objects
- Concrete decorators: `CheeseDecorator`, `BaconDecorator`, `SauceDecorator`
- Adds cost and description transparently

```java
// Usage
Meal burger = new Burger();
burger = new CheeseDecorator(burger);  // +$1.50
burger = new BaconDecorator(burger);   // +$2.50
burger = new SauceDecorator(burger);   // +$1.00
// Final: $13.99 with all toppings
```

**Benefits:**
- Add features at runtime
- Multiple decorators can wrap same object
- Open/Closed Principle compliance

---

### 4. Strategy Pattern (Behavioral)

**Intent:** Define a family of algorithms, encapsulate each one, and make them interchangeable.

**Implementation:**
- `PaymentStrategy` interface with multiple implementations
- `PaymentContext` switches strategies at runtime
- Strategies: `CreditCardPayment`, `CashPayment`, `PayPalPayment`

```java
// Usage
PaymentContext payment = new PaymentContext();
payment.setPaymentStrategy(new CreditCardPayment(...));
payment.executePayment(45.99);

// Switch strategy
payment.setPaymentStrategy(new PayPalPayment(...));
payment.executePayment(45.99);
```

**Benefits:**
- Algorithm selection at runtime
- Each algorithm isolated in own class
- Easy to add new payment methods

---

### 5. Observer Pattern (Behavioral)

**Intent:** Define one-to-many dependency so when one object changes state, dependents are notified automatically.

**Implementation:**
- `Order` is Subject maintaining list of `Observer`s
- Concrete observers: `Kitchen`, `Customer`, `Waiter`
- Automatic notification on status changes

```java
// Usage
Order order = new Order(1001, "Alice");
order.attach(new Kitchen("Main Kitchen"));
order.attach(new Customer("Alice", "+1-555-0123"));
order.attach(new Waiter("Bob", 5));

order.setStatus("Confirmed");  // All observers notified
order.setStatus("Preparing");  // All observers notified
order.setStatus("Ready");      // All observers notified
```

**Benefits:**
- Loose coupling between subject and observers
- Dynamic subscription/unsubscription
- Broadcast communication

---

### 6. Facade Pattern (Structural)

**Intent:** Provide a unified interface to a set of interfaces in a subsystem, making it easier to use.

**Implementation:**
- `IntegratedRestaurantFacade` coordinates all patterns
- Single method call replaces complex multi-pattern workflow
- Simplifies client code dramatically

```java
// Usage - ONE method call instead of coordinating 6 patterns!
RestaurantFacade facade = new RestaurantFacade();
facade.placeCompleteOrder(
    "Charlie Brown",     // customer name
    "Pizza",            // meal type (Factory)
    true,               // add extras (Decorator)
    "Thin Crust",       // pizza dough (Builder)
    "Pepperoni",        // topping (Builder)
    "PayPal"            // payment method (Strategy)
);
// Behind the scenes: Uses ALL 6 patterns!
```

**Benefits:**
- Simplified interface to complex subsystem
- Reduces client dependencies
- Makes system easier to understand and use

---

## 🏛️ SOLID Principles

This project strictly adheres to SOLID principles:

### Single Responsibility Principle (SRP)
> A class should have only one reason to change.

**Examples:**
- `MealFactory` - Only responsible for creating meals
- `Order` - Only manages order items and status
- `PaymentContext` - Only handles payment execution
- Each decorator class adds one specific feature

### Open/Closed Principle (OCP)
> Software entities should be open for extension, closed for modification.

**Examples:**
- New meal types can be added to `MealFactory` without changing existing code
- New decorators can be created without modifying `MealDecorator`
- New payment strategies added without changing `PaymentContext`
- New observer types added without changing `Order`

### Liskov Substitution Principle (LSP)
> Objects should be replaceable with instances of their subtypes without altering correctness.

**Examples:**
- All `Meal` implementations (`Pizza`, `Burger`, `Salad`) are substitutable
- All `PaymentStrategy` implementations work interchangeably
- All `Observer` implementations can replace each other
- Decorated meals can be used wherever base `Meal` is expected

### Interface Segregation Principle (ISP)
> Clients should not depend on interfaces they don't use.

**Examples:**
- `Meal` interface has only essential methods
- `Observer` interface has single `update()` method
- `PaymentStrategy` interface focused on payment only
- Each interface is minimal and cohesive

### Dependency Inversion Principle (DIP)
> Depend on abstractions, not concretions.

**Examples:**
- `Order` depends on `Observer` interface, not concrete observers
- `PaymentContext` depends on `PaymentStrategy` interface
- `MealDecorator` depends on `Meal` interface
- `RestaurantFacade` uses interfaces for all subsystems

---

## 📁 Project Structure

```
RestaurantOrderingSystem/
├── src/
│   ├── factory/
│   │   ├── Meal.java                    # Product interface
│   │   ├── Pizza.java                   # Concrete product
│   │   ├── Burger.java                  # Concrete product
│   │   ├── Salad.java                   # Concrete product
│   │   └── MealFactory.java             # Factory class
│   │
│   ├── builder/
│   │   ├── CustomPizza.java             # Complex product
│   │   └── PizzaBuilder.java            # Builder class
│   │
│   ├── decorator/
│   │   ├── MealDecorator.java           # Abstract decorator
│   │   ├── CheeseDecorator.java         # Concrete decorator
│   │   ├── BaconDecorator.java          # Concrete decorator
│   │   └── SauceDecorator.java          # Concrete decorator
│   │
│   ├── strategy/
│   │   ├── PaymentStrategy.java         # Strategy interface
│   │   ├── CreditCardPayment.java       # Concrete strategy
│   │   ├── CashPayment.java             # Concrete strategy
│   │   ├── PayPalPayment.java           # Concrete strategy
│   │   └── PaymentContext.java          # Context class
│   │
│   ├── observer/
│   │   ├── Observer.java                # Observer interface
│   │   ├── Order.java                   # Subject class
│   │   ├── Kitchen.java                 # Concrete observer
│   │   ├── Customer.java                # Concrete observer
│   │   └── Waiter.java                  # Concrete observer
│   │
│   ├── facade/
│   │   └── RestaurantFacade.java        # Facade class
│   │
│   └── Main.java                        # Application entry point
│
├── docs/
│   ├── uml/                             # PlantUML diagrams
│   │   ├── factory_pattern.puml
│   │   ├── builder_pattern.puml
│   │   ├── decorator_pattern.puml
│   │   ├── strategy_pattern.puml
│   │   ├── observer_pattern.puml
│   │   ├── facade_pattern.puml
│   │   └── system_architecture.puml
│   │
│   └── screenshots/                     # Application screenshots
│       ├── factory_demo.png
│       ├── builder_demo.png
│       ├── decorator_demo.png
│       ├── observer_demo.png
│       ├── strategy_demo.png
│       └── facade_demo.png
│
├── README.md                            # This file
├── REPORT.pdf                           # Full project report
└── .gitignore
```

---

## 🚀 Getting Started

### Prerequisites

- Java Development Kit (JDK) 11 or higher
- Any Java IDE (IntelliJ IDEA, Eclipse, VS Code)

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/YOUR_USERNAME/RestaurantOrderingSystem.git
cd RestaurantOrderingSystem
```

2. **Compile the project**
```bash
javac -d bin src/**/*.java src/*.java
```

3. **Run the application**
```bash
java -cp bin Main
```

### Quick Start

```java
// Run individual pattern demos
java -cp bin FactoryPatternDemo
java -cp bin BuilderPatternDemo
java -cp bin DecoratorPatternDemo
java -cp bin StrategyPatternDemo
java -cp bin ObserverPatternDemo
java -cp bin FacadePatternDemo

// Run complete integrated demo
java -cp bin Main
```

---

## 💡 Usage Examples

### Example 1: Creating Meals with Factory

```java
// Create different meal types
Meal pizza = MealFactory.createMeal("Pizza");
Meal burger = MealFactory.createMeal("Burger");
Meal salad = MealFactory.createMeal("Salad");

pizza.prepare();
System.out.println(pizza.getDescription() + " - $" + pizza.getPrice());
```

**Output:**
```
Factory: Creating Pizza...
Preparing Pizza...
Adding tomato sauce and mozzarella cheese
Baking in wood-fired oven at 450°F
Margherita Pizza - $12.99
```

---

### Example 2: Building Custom Pizza

```java
// Build complex custom pizza
CustomPizza customPizza = new PizzaBuilder()
    .setDough("Thin Crust")
    .setSauce("BBQ Sauce")
    .addTopping("Chicken")
    .addTopping("Mushrooms")
    .addTopping("Onions")
    .build();

customPizza.prepare();
System.out.println("Price: $" + customPizza.getPrice());
System.out.println("Cooking time: " + customPizza.getCookingTime() + " min");
```

**Output:**
```
Builder: Dough set to Thin Crust
Builder: Sauce set to BBQ Sauce
Builder: Added topping - Chicken
Builder: Added topping - Mushrooms
Builder: Added topping - Onions
Builder: Pizza construction complete!
Preparing Custom Pizza:
- Dough: Thin Crust
- Sauce: BBQ Sauce
- Toppings: Chicken, Mushrooms, Onions
Price: $15.49
Cooking time: 18 minutes
```

---

### Example 3: Decorating Meals

```java
// Start with basic burger
Meal burger = new Burger();
System.out.println("Base: " + burger.getDescription() + " = $" + burger.getPrice());

// Add cheese
burger = new CheeseDecorator(burger);
System.out.println("With cheese: " + burger.getDescription() + " = $" + burger.getPrice());

// Add bacon
burger = new BaconDecorator(burger);
System.out.println("With bacon: " + burger.getDescription() + " = $" + burger.getPrice());

// Add sauce
burger = new SauceDecorator(burger);
System.out.println("Final: " + burger.getDescription() + " = $" + burger.getPrice());
```

**Output:**
```
Base: Basic Burger = $8.99
With cheese: Basic Burger, with Extra Cheese = $10.49
With bacon: Basic Burger, with Extra Cheese, with Bacon = $12.99
Final: Basic Burger, with Extra Cheese, with Bacon, with Special Sauce = $13.99
```

---

### Example 4: Payment Strategies

```java
PaymentContext payment = new PaymentContext();

// Pay with Credit Card
payment.setPaymentStrategy(new CreditCardPayment(
    "4532123456789012",
    "Alice Johnson",
    "123",
    "12/25"
));
payment.executePayment(45.99);

// Switch to PayPal
payment.setPaymentStrategy(new PayPalPayment(
    "alice@email.com",
    "securePass123"
));
payment.executePayment(45.99);
```

**Output:**
```
Payment method set to: Credit Card
Processing Credit Card Payment...
Card Holder: Alice Johnson
Card Number: **** **** **** 9012
Amount: $45.99
✓ Payment completed successfully!

Payment method set to: PayPal
Processing PayPal Payment...
PayPal Account: alice@email.com
Amount: $45.99
✓ Payment completed successfully!
```

---

### Example 5: Observer Notifications

```java
// Create order
Order order = new Order(1001, "Alice Johnson");
order.addItem("Margherita Pizza");
order.addItem("Caesar Salad");

// Attach observers
order.attach(new Kitchen("Main Kitchen"));
order.attach(new Customer("Alice Johnson", "+1-555-0123"));
order.attach(new Waiter("Bob Smith", 5));

// Change status - all observers notified automatically
order.setStatus("Confirmed");
order.setStatus("Preparing");
order.setStatus("Ready");
```

**Output:**
```
Order #1001 created for Alice Johnson
Observer attached: Kitchen (Main Kitchen)
Observer attached: Customer (Alice Johnson)
Observer attached: Waiter (Bob Smith - Table 5)

Order #1001 status changing: Created → Confirmed
[KITCHEN] Order #1001 confirmed. Adding to cooking queue...
[CUSTOMER] Your order #1001 has been confirmed!
[WAITER] Order #1001 confirmed for Table 5

Order #1001 status changing: Confirmed → Preparing
[KITCHEN] Order #1001 is now being prepared
[CUSTOMER] Your order #1001 is being prepared
[WAITER] Order #1001 is being prepared in kitchen

Order #1001 status changing: Preparing → Ready
[KITCHEN] Order #1001 is ready for pickup!
[CUSTOMER] Your order #1001 is ready!
[WAITER] Order #1001 ready at pickup counter!
```

---

### Example 6: Complete Order with Facade

```java
// Complex workflow simplified to ONE method call
RestaurantFacade facade = new RestaurantFacade();

facade.placeCompleteOrder(
    "Charlie Brown",     // Customer name
    "Pizza",            // Meal type
    true,               // Add extras
    "Thin Crust",       // Pizza dough
    "Pepperoni",        // Pizza topping
    "PayPal"            // Payment method
);
```

**Output:**
```
Processing complete order for: Charlie Brown
→ Using Factory Pattern to create Pizza
  ✓ Base meal created: Margherita Pizza
→ Using Decorator Pattern to add extras
  ✓ Enhanced: Margherita Pizza, with Extra Cheese
→ Using Builder Pattern for custom pizza
  ✓ Custom pizza ready
→ Using Observer Pattern to set up notifications
  ✓ Observers attached
→ Using Strategy Pattern for payment
  ✓ Payment successful!
✓ ORDER COMPLETE! All 6 patterns worked together.
```

---

## 📊 UML Diagrams

Detailed UML diagrams for each pattern are available in `docs/uml/`:

### Class Diagrams
- **Factory Pattern** - Shows MealFactory creating different Meal types
- **Builder Pattern** - Illustrates PizzaBuilder constructing CustomPizza
- **Decorator Pattern** - Demonstrates decorator wrapping hierarchy
- **Strategy Pattern** - Shows PaymentStrategy implementations
- **Observer Pattern** - Illustrates Order notifying multiple observers
- **Facade Pattern** - Shows RestaurantFacade coordinating all patterns

### Sequence Diagrams
- **Complete Order Flow** - End-to-end interaction of all patterns
- **Observer Notification** - How status changes propagate to observers
- **Payment Processing** - Strategy pattern in action

### Component Diagram
- **System Architecture** - High-level view of pattern integration

*See `docs/uml/` directory for PlantUML source files*

---

## 🔮 Future Enhancements

### Short-term (Planned)
- [ ] Add Maven/Gradle build configuration
- [ ] Implement unit tests (JUnit 5)
- [ ] Add logging framework (SLF4J + Logback)
- [ ] Create comprehensive JavaDoc documentation

### Medium-term (Possible)
- [ ] Database integration (JPA/Hibernate)
- [ ] RESTful API layer (Spring Boot)
- [ ] Web interface (React/Angular)
- [ ] Additional patterns (Singleton, Proxy, Command)

### Long-term (Vision)
- [ ] Microservices architecture
- [ ] Cloud deployment (AWS/Azure)
- [ ] Mobile app integration
- [ ] Real-time analytics dashboard

---

## 👥 Contributors

- **Student 1** - Factory, Builder, Decorator Patterns
- **Student 2** - Strategy, Observer, Facade Patterns

---

## 📝 License

This project is created for educational purposes as part of Software Design Patterns course.

---

## 🙏 Acknowledgments

- **Gang of Four** - Design Patterns: Elements of Reusable Object-Oriented Software
- **Head First Design Patterns** - Freeman, Robson, Bates, Sierra
- **Refactoring Guru** - Excellent pattern explanations and examples
- Course instructors and teaching assistants

---

## 📚 References

1. Gamma, E., Helm, R., Johnson, R., & Vlissides, J. (1994). *Design Patterns: Elements of Reusable Object-Oriented Software*. Addison-Wesley.

2. Freeman, E., Robson, E., Bates, B., & Sierra, K. (2004). *Head First Design Patterns*. O'Reilly Media.

3. Martin, R. C. (2008). *Clean Code: A Handbook of Agile Software Craftsmanship*. Prentice Hall.

4. Refactoring.Guru - Design Patterns: https://refactoring.guru/design-patterns

5. Oracle Java Documentation: https://docs.oracle.com/javase/

---

<div align="center">

**⭐ Star this repository if you find it helpful!**

Made with ❤️ for Software Design Patterns Course

</div>
