___Functional interfaces___ are interfaces with a single abstract method. They are used in lambda expressions and method references. 
The `@FunctionalInterface` annotation (_NOT MANDATORY_) is used to ensure that the interface has only one abstract method. 
If the interface has more than one abstract method, the compiler will throw an error.
- A functional interface has only one abstract method (and any number of static and default methods).

___Lambda expressions implements a Functional Interface.___ 
They are used to provide the implementation of the abstract method of a Functional Interface. 
Lambda expressions are used to provide the implementation of the abstract method of a Functional Interface. 

```java
Supplier<String> supplier = 
        () -> "Hello World";
```
A basic lambda has a parameter list, an arrow, and a body(implementation).


## The java.util.function package has more than 40 functional interfaces organized in 4+1 categories:
### Supplier
A Supplier is a functional interface that takes no input and returns a value.
_Does not take any argument and produces any kind of object as long as it matches the T type you define._
See declaration:
```java
@FunctionalInterface
public interface Supplier<T> {
    /**
     * Gets a result.
     *
     * @return a result
     */
    T get();
}
```
```java
Supplier<String> supplier = 
        () -> "Hello World";
```
---
### Consumer
A Consumer is a functional interface that takes a single input and returns nothing.
See declaration:
```java
public interface Consumer<T> {
    /**
     * Performs this operation on the given argument.
     *
     * @param t the input argument
     */
    void accept(T t);
}
```

```java
Consumer<String> consumer = 
        (s) -> System.out.println(s);
```

---


### Predicate 
Takes an object of type T and returns a boolean.
___It is used for the filtering operations of the Streams API.___
```java
@FunctionalInterface
public interface Predicate<T> {

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param t the input argument
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     */
    boolean test(T t);
}
```

For e.g. The following predicate checks if a string is empty and returns a true.
```java
Predicate<String> isEmpty = 
        s -> s.isEmpty(); //OR String::isEmpty
//OR
Predicate<String> lengthGreaterThan5 = 
        s -> s.length() > 5;
```

---

### Function
A Function is a functional interface that ___takes an object of type T___ as input and ___returns an object of type R___.t
___It is used for the mapping operations of the Streams API.___
```java
@FunctionalInterface
public interface Function<T, R> {
    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    R apply(T t);
}
```
---

### Bonus: Runnable interface
A Runnable is a functional interface that takes no input and returns nothing.
```java
@FunctionalInterface
public interface Runnable {
    /**
     * Runs this operation.
     */
    void run();
}
```
```java
Runnable runnable = 
        () -> System.out.println("Hello World");
//OR
Runnable alert = 
        () -> Toolkit.getDefaultToolkit().beep();
```
---

___SUMMARY:___
<table>
  <tr>
    <th>Interface</th>
    <th>Method</th>
    <th>Description</th>
    <th>Example</th>
  </tr>
  <tr>
    <td>Supplier&lt;T&gt;</td>
    <td><code>T get()</code></td>
    <td>Gets nothing, returns something</td>
    <td><code>() -&gt; "Hello!";</code></td>
  </tr>
  <tr>
    <td>Consumer&lt;T&gt;</td>
    <td><code>void accept(T t)</code></td>
    <td>Gets something, returns nothing</td>
    <td><code>() -&gt; System.out.println(s);</code></td>
  </tr>
  <tr>
    <td>Predicate&lt;T&gt;</td>
    <td><code>boolean test(T t)</code></td>
    <td>Takes something, returns a bool</td>
    <td><code>string -&gt; string.isEmpty();</code></td>
  </tr>
  <tr>
    <td>Function&lt;T, R&gt;</td>
    <td><code>R apply(T t)</code></td>
    <td>Takes something of a type, returns something else</td>
    <td><code>user -&gt; user.getName();</code></td>
  </tr>
  <tr>
    <td>Runnable</td>
    <td><code>void run()</code></td>
    <td>Does not take anything and returns nothing</td>
    <td><code>() -&gt; System.out.println("Something");</code></td>
  </tr>
</table>

---

Lambda expressions are very optimized so very performant.
When you are ___dealing with numbers___ (int, long, double), you have ___specialized lambda expressions___ which are more optimized than the generic ones.
e.g. ___It is faster to use IntPredicate than Predicate<Integer>___ when dealing with int values. 
Because you do not have to pay the price of boxing and unboxing.

You can always create your own lambdas by chaining together the already existing ones.

___A real world example of chaining Consumer<String> could be logging and then sending a notification.___
```java
import java.util.function.Consumer;

public class ChainingLambdas {
    public static void main(String[] args) {        
        // Consumer: logs the message
        Consumer<String> logMessage = message -> System.out.println("Logging: " + message);

        // Consumer: sends a notification with the message
        Consumer<String> sendNotification = message -> System.out.println("Sending notification: " + message);

        // Chaining consumers: first logs the message, then sends a notification
        Consumer<String> logAndNotify = logMessage.andThen(sendNotification);

        // Using the chained consumer
        logAndNotify.accept("System update completed.");
    }
}
```
The output of the code above is:
> Logging: System update completed. <br>
> Sending notification: System update completed.
---


