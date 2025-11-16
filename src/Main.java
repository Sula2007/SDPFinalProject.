import React, { useState } from 'react';
import { Info, Factory, Hammer, Palette } from 'lucide-react';

const RestaurantApp = () => {
  // State to track which tab is active
  const [activeTab, setActiveTab] = useState('main');
  
  // Output states for each pattern demo
  const [factoryOutput, setFactoryOutput] = useState('');
  const [builderOutput, setBuilderOutput] = useState('');
  const [decoratorOutput, setDecoratorOutput] = useState('');

  // Factory Pattern Demo Function
  const runFactoryDemo = () => {
    const output = [];
    output.push('='.repeat(60));
    output.push('FACTORY PATTERN DEMONSTRATION');
    output.push('='.repeat(60));
    output.push('');
    
    // Creating Pizza
    output.push('Creating Pizza:');
    output.push('Factory: Creating Pizza...');
    output.push('Preparing Pizza...');
    output.push('Adding tomato sauce and mozzarella cheese');
    output.push('Baking in wood-fired oven at 450°F');
    output.push('Price: $12.99');
    output.push('Cooking time: 15 minutes');
    output.push('');
    
    // Creating Burger
    output.push('Creating Burger:');
    output.push('Factory: Creating Burger...');
    output.push('Preparing Burger...');
    output.push('Grilling beef patty to perfection');
    output.push('Adding lettuce, tomato, cheese, and special sauce');
    output.push('Price: $8.99');
    output.push('Cooking time: 10 minutes');
    output.push('');
    
    // Creating Salad
    output.push('Creating Salad:');
    output.push('Factory: Creating Salad...');
    output.push('Preparing Salad...');
    output.push('Washing fresh romaine lettuce');
    output.push('Adding croutons, parmesan cheese, and Caesar dressing');
    output.push('Price: $6.99');
    output.push('Cooking time: 5 minutes');
    output.push('');
    
    output.push('='.repeat(60));
    output.push('✓ Factory Pattern successfully created 3 different meal types!');
    output.push('='.repeat(60));
    
    setFactoryOutput(output.join('\n'));
  };

  // Builder Pattern Demo Function
  const runBuilderDemo = () => {
    const output = [];
    output.push('='.repeat(60));
    output.push('BUILDER PATTERN DEMONSTRATION');
    output.push('='.repeat(60));
    output.push('');
    
    output.push('Building Custom Pizza:');
    output.push('-'.repeat(60));
    output.push('Builder: Dough set to Thin Crust');
    output.push('Builder: Sauce set to Tomato Sauce');
    output.push('Builder: Added topping - Mozzarella');
    output.push('Builder: Added topping - Pepperoni');
    output.push('Builder: Added topping - Mushrooms');
    output.push('Builder: Added topping - Olives');
    output.push('Builder: Pizza construction complete!');
    output.push('');
    
    output.push('Preparing Custom Pizza:');
    output.push('- Dough: Thin Crust');
    output.push('- Sauce: Tomato Sauce');
    output.push('- Toppings: Mozzarella, Pepperoni, Mushrooms, Olives');
    output.push('Price: $16.99');
    output.push('Cooking time: 19 minutes');
    output.push('');
    
    output.push('='.repeat(60));
    output.push('✓ Builder Pattern successfully created complex pizza object!');
    output.push('='.repeat(60));
    
    setBuilderOutput(output.join('\n'));
  };

  // Decorator Pattern Demo Function
  const runDecoratorDemo = () => {
    const output = [];
    output.push('='.repeat(60));
    output.push('DECORATOR PATTERN DEMONSTRATION');
    output.push('='.repeat(60));
    output.push('');
    
    output.push('Step 1: Creating base burger');
    output.push('-'.repeat(60));
    output.push('Basic Burger = $8.99');
    output.push('');
    
    output.push('Step 2: Adding cheese');
    output.push('-'.repeat(60));
    output.push('Decorator: Adding Cheese (+$1.50)');
    output.push('Basic Burger, with Extra Cheese = $10.49');
    output.push('');
    
    output.push('Step 3: Adding bacon');
    output.push('-'.repeat(60));
    output.push('Decorator: Adding Bacon (+$2.50)');
    output.push('Basic Burger, with Extra Cheese, with Bacon = $12.99');
    output.push('');
    
    output.push('Step 4: Adding special sauce');
    output.push('-'.repeat(60));
    output.push('Decorator: Adding Special Sauce (+$1.00)');
    output.push('Basic Burger, with Extra Cheese, with Bacon, with Special Sauce = $13.99');
    output.push('');
    
    output.push('Final meal preparation:');
    output.push('='.repeat(60));
    output.push('Preparing Basic Burger...');
    output.push('Grilling beef patty');
    output.push('Adding extra cheese topping...');
    output.push('Adding crispy bacon strips...');
    output.push('Drizzling special house sauce...');
    output.push('');
    output.push('Final Price: $13.99');
    output.push('Final Cooking Time: 13 minutes');
    output.push('');
    
    output.push('Decorator Structure:');
    output.push('-'.repeat(60));
    output.push('SauceDecorator (wraps)');
    output.push('  └─ BaconDecorator (wraps)');
    output.push('      └─ CheeseDecorator (wraps)');
    output.push('          └─ Basic Burger');
    output.push('');
    
    output.push('='.repeat(60));
    output.push('✓ Decorator Pattern successfully added features dynamically!');
    output.push('='.repeat(60));
    
    setDecoratorOutput(output.join('\n'));
  };

  // Tab configuration
  const tabs = [
    { id: 'main', label: 'Main Info', icon: Info },
    { id: 'factory', label: 'Factory Pattern', icon: Factory },
    { id: 'builder', label: 'Builder Pattern', icon: Hammer },
    { id: 'decorator', label: 'Decorator Pattern', icon: Palette }
  ];

  return (
    <div className="min-h-screen bg-gradient-to-br from-orange-50 to-red-50 p-6">
      <div className="max-w-6xl mx-auto">
        
        {/* Header */}
        <div className="bg-white rounded-lg shadow-lg p-6 mb-6">
          <h1 className="text-3xl font-bold text-gray-800 mb-2">
            Restaurant Ordering System
          </h1>
          <p className="text-gray-600">Student 1 - Design Patterns Implementation</p>
        </div>

        {/* Tab Navigation */}
        <div className="bg-white rounded-lg shadow-lg mb-6">
          <div className="flex border-b">
            {tabs.map((tab) => {
              const Icon = tab.icon;
              return (
                <button
                  key={tab.id}
                  onClick={() => setActiveTab(tab.id)}
                  className={`flex items-center gap-2 px-6 py-4 font-medium transition-colors ${
                    activeTab === tab.id
                      ? 'bg-orange-600 text-white border-b-2 border-orange-600'
                      : 'text-gray-600 hover:bg-gray-50'
                  }`}
                >
                  <Icon className="w-5 h-5" />
                  {tab.label}
                </button>
              );
            })}
          </div>
        </div>

        {/* Tab Content */}
        <div className="bg-white rounded-lg shadow-lg p-8">
          
          {/* MAIN TAB */}
          {activeTab === 'main' && (
            <div className="space-y-6">
              <h2 className="text-2xl font-bold text-gray-800">Project Overview</h2>
              
              <div className="bg-orange-50 border-l-4 border-orange-600 p-5 rounded">
                <h3 className="font-bold text-lg mb-2">📚 About This Project</h3>
                <p className="text-gray-700 mb-3">
                  This is a <strong>Restaurant Ordering System</strong> that demonstrates <strong>6 fundamental design patterns</strong> used in software engineering.
                </p>
                <p className="text-gray-700">
                  The project is divided between <strong>2 students</strong>, each implementing <strong>3 patterns</strong> that work together to create a complete ordering system.
                </p>
              </div>

              <div className="grid md:grid-cols-2 gap-6">
                
                {/* Student 1 - My Part */}
                <div className="border-2 border-blue-500 bg-blue-50 rounded-lg p-5">
                  <h3 className="font-bold text-xl mb-4 text-blue-900">
                    👤 Student 1 - My Implementation
                  </h3>
                  
                  <div className="space-y-4">
                    <div className="bg-white p-4 rounded-lg">
                      <div className="flex items-center gap-2 mb-2">
                        <Factory className="w-5 h-5 text-blue-600" />
                        <h4 className="font-bold">1. Factory Pattern</h4>
                      </div>
                      <p className="text-sm text-gray-700">Creates different meal types (Pizza, Burger, Salad)</p>
                      <p className="text-xs text-gray-600 mt-1">
                        <strong>Classes:</strong> MealFactory, Meal, Pizza, Burger, Salad
                      </p>
                    </div>

                    <div className="bg-white p-4 rounded-lg">
                      <div className="flex items-center gap-2 mb-2">
                        <Hammer className="w-5 h-5 text-blue-600" />
                        <h4 className="font-bold">2. Builder Pattern</h4>
                      </div>
                      <p className="text-sm text-gray-700">Builds custom pizzas step-by-step</p>
                      <p className="text-xs text-gray-600 mt-1">
                        <strong>Classes:</strong> PizzaBuilder, CustomPizza
                      </p>
                    </div>

                    <div className="bg-white p-4 rounded-lg">
                      <div className="flex items-center gap-2 mb-2">
                        <Palette className="w-5 h-5 text-blue-600" />
                        <h4 className="font-bold">3. Decorator Pattern</h4>
                      </div>
                      <p className="text-sm text-gray-700">Adds toppings dynamically to meals</p>
                      <p className="text-xs text-gray-600 mt-1">
                        <strong>Classes:</strong> MealDecorator, CheeseDecorator, BaconDecorator, SauceDecorator
                      </p>
                    </div>
                  </div>
                </div>

                {/* Student 2 - Partner's Part */}
                <div className="border border-green-300 bg-green-50 rounded-lg p-5">
                  <h3 className="font-bold text-xl mb-4 text-green-900">
                    👤 Student 2 - Partner's Implementation
                  </h3>
                  
                  <div className="space-y-3">
                    <div className="bg-white p-3 rounded">
                      <h4 className="font-bold text-sm">4. Strategy Pattern</h4>
                      <p className="text-xs text-gray-700">Different payment methods</p>
                    </div>

                    <div className="bg-white p-3 rounded">
                      <h4 className="font-bold text-sm">5. Observer Pattern</h4>
                      <p className="text-xs text-gray-700">Order status notifications</p>
                    </div>

                    <div className="bg-white p-3 rounded">
                      <h4 className="font-bold text-sm">6. Facade Pattern</h4>
                      <p className="text-xs text-gray-700">Simplifies ordering process</p>
                    </div>
                  </div>
                </div>
              </div>

              <div className="bg-gray-50 rounded-lg p-5">
                <h3 className="font-bold text-lg mb-3">🔗 How Patterns Work Together</h3>
                <div className="bg-white p-4 rounded border font-mono text-sm text-gray-700">
                  Main.java (Student 2)<br/>
                  &nbsp;&nbsp;↓<br/>
                  RestaurantFacade (Student 2)<br/>
                  &nbsp;&nbsp;↓<br/>
                  1. <span className="text-blue-600 font-bold">MealFactory (Student 1)</span> - creates meal<br/>
                  2. <span className="text-blue-600 font-bold">Decorator (Student 1)</span> - adds toppings<br/>
                  3. Order + Observer (Student 2) - notifies<br/>
                  4. PaymentStrategy (Student 2) - processes payment
                </div>
              </div>

              <div className="bg-purple-50 border-l-4 border-purple-600 p-5 rounded">
                <h3 className="font-bold text-lg mb-3">📦 My Files (Student 1)</h3>
                <div className="grid grid-cols-2 gap-2">
                  <code className="bg-white px-3 py-2 rounded text-sm">Meal.java</code>
                  <code className="bg-white px-3 py-2 rounded text-sm">Pizza.java</code>
                  <code className="bg-white px-3 py-2 rounded text-sm">Burger.java</code>
                  <code className="bg-white px-3 py-2 rounded text-sm">Salad.java</code>
                  <code className="bg-white px-3 py-2 rounded text-sm">MealFactory.java</code>
                  <code className="bg-white px-3 py-2 rounded text-sm">PizzaBuilder.java</code>
                  <code className="bg-white px-3 py-2 rounded text-sm">MealDecorator.java</code>
                  <code className="bg-white px-3 py-2 rounded text-sm">CheeseDecorator.java</code>
                  <code className="bg-white px-3 py-2 rounded text-sm">BaconDecorator.java</code>
                  <code className="bg-white px-3 py-2 rounded text-sm">SauceDecorator.java</code>
                </div>
              </div>

              <div className="bg-yellow-50 border-l-4 border-yellow-600 p-5 rounded">
                <h3 className="font-bold text-lg mb-2">💡 Instructions</h3>
                <ul className="list-disc list-inside text-gray-700 space-y-1">
                  <li>Click on each pattern tab to see detailed explanations</li>
                  <li>Press "Run Demo" button to see the pattern in action</li>
                  <li>Review console output to understand how patterns work</li>
                  <li>All patterns will integrate with Student 2's part later</li>
                </ul>
              </div>
            </div>
          )}

          {/* FACTORY PATTERN TAB */}
          {activeTab === 'factory' && (
            <div className="space-y-6">
              <h2 className="text-2xl font-bold text-gray-800">Factory Pattern</h2>
              
              <div className="bg-blue-50 border-l-4 border-blue-600 p-5 rounded">
                <h3 className="font-bold text-lg mb-2">📖 What is Factory Pattern?</h3>
                <p className="text-gray-700">
                  The Factory Pattern provides an interface for creating objects without specifying their exact classes. 
                  Instead of using <code className="bg-white px-2 py-1 rounded">new Pizza()</code>, we use a factory method 
                  that decides which object to create based on input.
                </p>
              </div>

              <div className="bg-white border border-gray-200 rounded-lg p-5">
                <h3 className="font-bold text-lg mb-3">🏗️ Structure</h3>
                <ul className="space-y-2 text-gray-700">
                  <li><strong className="text-blue-600">Interface:</strong> <code className="bg-gray-100 px-2 py-1 rounded">Meal</code> - Common interface for all meals</li>
                  <li><strong className="text-blue-600">Concrete Classes:</strong> <code className="bg-gray-100 px-2 py-1 rounded">Pizza</code>, <code className="bg-gray-100 px-2 py-1 rounded">Burger</code>, <code className="bg-gray-100 px-2 py-1 rounded">Salad</code></li>
                  <li><strong className="text-blue-600">Factory:</strong> <code className="bg-gray-100 px-2 py-1 rounded">MealFactory.createMeal(String type)</code></li>
                </ul>
              </div>

              <div className="bg-white border border-gray-200 rounded-lg p-5">
                <h3 className="font-bold text-lg mb-3">💻 Code Example</h3>
                <pre className="bg-gray-900 text-green-400 p-4 rounded text-sm overflow-auto">
{`// Factory method
public static Meal createMeal(String mealType) {
    switch (mealType.toLowerCase()) {
        case "pizza":
            return new Pizza();
        case "burger":
            return new Burger();
        case "salad":
            return new Salad();
        default:
            throw new IllegalArgumentException("Unknown meal type");
    }
}

// Usage
Meal pizza = MealFactory.createMeal("Pizza");
pizza.prepare();
System.out.println("Price: $" + pizza.getPrice());`}
                </pre>
              </div>

              <div className="bg-green-50 border-l-4 border-green-600 p-5 rounded">
                <h3 className="font-bold text-lg mb-2">✅ Benefits</h3>
                <ul className="list-disc list-inside text-gray-700 space-y-1">
                  <li>Encapsulates object creation logic</li>
                  <li>Easy to add new meal types without changing existing code</li>
                  <li>Client code doesn't need to know about concrete classes</li>
                  <li>Promotes loose coupling between client and concrete classes</li>
                </ul>
              </div>

              <button
                onClick={runFactoryDemo}
                className="w-full bg-orange-600 text-white py-3 px-6 rounded-lg font-bold hover:bg-orange-700 transition-colors"
              >
                🚀 Run Factory Pattern Demo
              </button>

              {factoryOutput && (
                <div>
                  <h3 className="font-bold text-lg mb-2">Console Output:</h3>
                  <pre className="bg-gray-900 text-green-400 p-4 rounded-lg overflow-auto font-mono text-sm whitespace-pre">
                    {factoryOutput}
                  </pre>
                </div>
              )}
            </div>
          )}

          {/* BUILDER PATTERN TAB */}
          {activeTab === 'builder' && (
            <div className="space-y-6">
              <h2 className="text-2xl font-bold text-gray-800">Builder Pattern</h2>
              
              <div className="bg-blue-50 border-l-4 border-blue-600 p-5 rounded">
                <h3 className="font-bold text-lg mb-2">📖 What is Builder Pattern?</h3>
                <p className="text-gray-700">
                  The Builder Pattern constructs complex objects step by step. It's useful when an object has many optional parameters. 
                  Instead of a constructor with many parameters, we use a builder with a fluent interface (method chaining).
                </p>
              </div>

              <div className="bg-white border border-gray-200 rounded-lg p-5">
                <h3 className="font-bold text-lg mb-3">🏗️ Structure</h3>
                <ul className="space-y-2 text-gray-700">
                  <li><strong className="text-blue-600">Product:</strong> <code className="bg-gray-100 px-2 py-1 rounded">CustomPizza</code> - The complex object being built</li>
                  <li><strong className="text-blue-600">Builder:</strong> <code className="bg-gray-100 px-2 py-1 rounded">PizzaBuilder</code> - Provides methods to set properties</li>
                  <li><strong className="text-blue-600">Methods:</strong> setDough(), setSauce(), addTopping(), build()</li>
                </ul>
              </div>

              <div className="bg-white border border-gray-200 rounded-lg p-5">
                <h3 className="font-bold text-lg mb-3">💻 Code Example</h3>
                <pre className="bg-gray-900 text-green-400 p-4 rounded text-sm overflow-auto">
{`// Builder with fluent interface
CustomPizza pizza = new PizzaBuilder()
    .setDough("Thin Crust")      // Returns this
    .setSauce("Tomato Sauce")    // Returns this
    .addTopping("Mozzarella")    // Returns this
    .addTopping("Pepperoni")     // Returns this
    .build();                    // Creates final object

// Each method returns 'this' for chaining
public PizzaBuilder setDough(String dough) {
    this.dough = dough;
    return this; // Enables method chaining
}`}
                </pre>
              </div>

              <div className="bg-green-50 border-l-4 border-green-600 p-5 rounded">
                <h3 className="font-bold text-lg mb-2">✅ Benefits</h3>
                <ul className="list-disc list-inside text-gray-700 space-y-1">
                  <li>Clear and readable object construction</li>
                  <li>Allows step-by-step building of complex objects</li>
                  <li>Flexible - can create different configurations easily</li>
                  <li>Separates construction logic from representation</li>
                </ul>
              </div>

              <button
                onClick={runBuilderDemo}
                className="w-full bg-orange-600 text-white py-3 px-6 rounded-lg font-bold hover:bg-orange-700 transition-colors"
              >
                🚀 Run Builder Pattern Demo
              </button>

              {builderOutput && (
                <div>
                  <h3 className="font-bold text-lg mb-2">Console Output:</h3>
                  <pre className="bg-gray-900 text-green-400 p-4 rounded-lg overflow-auto font-mono text-sm whitespace-pre">
                    {builderOutput}
                  </pre>
                </div>
              )}
            </div>
          )}

          {/* DECORATOR PATTERN TAB */}
          {activeTab === 'decorator' && (
            <div className="space-y-6">
              <h2 className="text-2xl font-bold text-gray-800">Decorator Pattern</h2>
              
              <div className="bg-blue-50 border-l-4 border-blue-600 p-5 rounded">
                <h3 className="font-bold text-lg mb-2">📖 What is Decorator Pattern?</h3>
                <p className="text-gray-700">
                  The Decorator Pattern adds new functionality to objects dynamically without modifying their structure. 
                  Decorators wrap the original object and add features like extra toppings, updating both description and price.
                </p>
              </div>

              <div className="bg-white border border-gray-200 rounded-lg p-5">
                <h3 className="font-bold text-lg mb-3">🏗️ Structure</h3>
                <ul className="space-y-2 text-gray-700">
                  <li><strong className="text-blue-600">Component:</strong> <code className="bg-gray-100 px-2 py-1 rounded">Meal</code> - Interface for objects that can be decorated</li>
                  <li><strong className="text-blue-600">Concrete Component:</strong> <code className="bg-gray-100 px-2 py-1 rounded">Burger</code> - Base object</li>
                  <li><strong className="text-blue-600">Base Decorator:</strong> <code className="bg-gray-100 px-2 py-1 rounded">MealDecorator</code> - Abstract decorator class</li>
                  <li><strong className="text-blue-600">Concrete Decorators:</strong> CheeseDecorator, BaconDecorator, SauceDecorator</li>
                </ul>
              </div>

              <div className="bg-white border border-gray-200 rounded-lg p-5">
                <h3 className="font-bold text-lg mb-3">💻 Code Example</h3>
                <pre className="bg-gray-900 text-green-400 p-4 rounded text-sm overflow-auto">
{`// Start with base meal
Meal burger = new Burger(); // $8.99

// Wrap with decorators
burger = new CheeseDecorator(burger); // +$1.50 = $10.49
burger = new BaconDecorator(burger);  // +$2.50 = $12.99
burger = new SauceDecorator(burger);  // +$1.00 = $13.99

// Each decorator wraps the previous one
// and adds to price and description
System.out.println(burger.getDescription());
// Output: Basic Burger, with Extra Cheese, with Bacon, with Special Sauce`}
                </pre>
              </div>

              <div className="bg-green-50 border-l-4 border-green-600 p-5 rounded">
                <h3 className="font-bold text-lg mb-2">✅ Benefits</h3>
                <ul className="list-disc list-inside text-gray-700 space-y-1">
                  <li>Adds functionality dynamically without modifying original classes</li>
                  <li>More flexible than inheritance</li>
                  <li>Can combine multiple decorators in any order</li>
                  <li>Follows Open/Closed Principle (open for extension, closed for modification)</li>
                  <li>Each decorator adds a single responsibility</li>
                </ul>
              </div>

              <button
                onClick={runDecoratorDemo}
                className="w-full bg-orange-600 text-white py-3 px-6 rounded-lg font-bold hover:bg-orange-700 transition-colors"
              >
                🚀 Run Decorator Pattern Demo
              </button>

              {decoratorOutput && (
                <div>
                  <h3 className="font-bold text-lg mb-2">Console Output:</h3>
                  <pre className="bg-gray-900 text-green-400 p-4 rounded-lg overflow-auto font-mono text-sm whitespace-pre">
                    {decoratorOutput}
                  </pre>
                </div>
              )}
            </div>
          )}

        </div>

        {/* Footer */}
        <div className="mt-6 text-center text-gray-600 text-sm">
          <p className="font-medium">Restaurant Ordering System - Design Patterns Project</p>
          <p className="mt-1">Student 1: Factory, Builder, Decorator Patterns</p>
        </div>
      </div>
    </div>
  );
};

export default RestaurantApp;
