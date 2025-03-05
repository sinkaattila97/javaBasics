Why the Java Streams API has not been included in the Collection API?

### Example:
Let's say we have a list of people (instances of some person) and we want to calculate the average of the age of the people.
But not all people from the list, but only the ones above 20. This is a map/filter/reduce pattern.
Considering that age is a property of a person: ___people -> map -> age___
- basically transforming the people object to an age
- takeing an entity and transforming it to another entity
THIS IS A MAP OPERATION
- 2nd step, we filter the ages above 20: ___age -> filter -> age > 20___
- It will take an age, and it will check if the age is okay, and it is going to decide whether this age should be kept or not
THIS IS A FILTER OPERATION
- filter does not change the type of the object, it just removes some of the objects
  (___age > 20 -> reduce -> avg(age > 20)___ let's say that the reduce step is an aggregation)

- So the map is going to take an object of a type and return an object of another type.
Does mot change the number and the order of elements.

### Iterator pattern
- The iterator pattern is a pattern that allows us to iterate over a collection of objects.

```java
import entities.Person;

List<Person> people = new ArrayList<>();

int sum = 0;
int count = 0;

for(
Person person :people){
        if(person.

getAge() >20){
sum +=person.

getAge();

count++;
        }
        }

double average = 0d;
if(count >0){
average =(double)sum /count;
}
```

- Filtering DOES NOT change the type of the object but it can change the number of elements.

#### 1 million people -> map -> 1 million age 
- If we have a list of 1 mill people, the map (since it does not change the number of elements) will create a new list with 1 mill ages(we are going to have 2 list with 1 million in each)

#### 1 million age -> filter -> 500k age > 20
- The filter will remove some of the ages, so we are going to have a list with 500k ages

```java
import entities.Person;

List<Person> people = new ArrayList<>();

double average =
        people.map(person -> person.getAge())
                .filter(age -> age > 20)
                .average();
```
- This would be the same implementation as the one above, but using the Java Streams API, but still very costly in terms of memory.
- Hence the Collection API does not include the Java Streams API map/filter/reduce algorithm

### To solve the duplication issue, we are going to create a Stream
- List<Person>: contains people
- person.stream: returns a Stream<Person>
- Since by construction, a stream is empty
Stream API is an implementation of map/filter/reduce pattern ___It does not duplicate data and does not loads the memory___

```java
import entities.Person;

List<Person> people = new ArrayList<>();

people.

stream()
        .

map(person ->person.

getAge())
        .

filter(age ->age >20)
        .

average();
``` 

- So for that, we just called .stream on this list and this ___is going to return a stream object.___
Now if we want to map this stream, we just ___call the map method___, pass a function that models the mapping as a parameter and ___this is going to create a new stream object___. Every time you call a method on a stream
that returns another stream, it is going to be a new stream object, but remember, ___there is no data in a stream___.
So since there is no data, creating a second stream is also free, it does not create any load neither on the CPU, neither on the memory.
- Same thing with filter. Filter is another method of the Stream API which takes a predicate. and returns the stream. Which is a new stream object created by filter.
- the average does not return a new stream but the result. It is a terminal operation. It is going to consume the stream and return the result
> If you have a patter which uses ___a stream, it needs to end up using a terminal operation___/method, otherwise, the stream is not going to be consumed and the result is not going to be calculated, you will have an empty stream.

- You are not allowed to use a stream after a terminal operation, you will get an exception. You can only use a stream once. So ___the following code is NOT CORRECT___
```java
        Stream<Person> stream = people.stream();
        Stream<String> nameStream = stream.map(p -> p.getName());
        Stream<String> emptyNames  = nameStream.filter(name -> name.isEmpty());
        Stream<String> nonEmptyNames  = nameStream.filter(name -> !name.isEmpty()); //2nd processing WRONG
        long countEmptyNames = emptyNames.count();
        long countNonEmptyNames = nonEmptyNames.count(); //2nd processing WRONG

        System.out.println("Empty names = " + countEmptyNames);
        System.out.println("Non empty names = " + countNonEmptyNames); //2nd processing WRONG
```

Same code snipp but corrected:
```java
        Stream<Person> stream = people.stream();
        Stream<String> nameStream = stream.map(p -> p.getName());
        Stream<String> emptyNames  = nameStream.filter(name -> name.isEmpty());
        long countEmptyNames = emptyNames.count();

        Stream<Person> stream2 = people.stream();
        Stream<String> nameStream2 = stream2.map(p -> p.getName());
        Stream<String> nonEmptyNames  = nameStream2.filter(name -> !name.isEmpty());
        long countNonEmptyNames = nonEmptyNames.count();

        System.out.println("Empty names = " + countEmptyNames);
        System.out.println("Non empty names = " + countNonEmptyNames);
```

AND the correct way of implementing all these without plus variables:

```java
        long countEmptyNames =
                people.stream()
                        .map(p -> p.getName())
                        .filter(name -> name.isEmpty())
                        .count();

        long countNonEmptyNames =
                people.stream()
                        .map(p -> p.getName())
                        .filter(name -> !name.isEmpty())
                        .count();
        

        System.out.println("Empty names = " + countEmptyNames);
        System.out.println("Non empty names = " + countNonEmptyNames);
```

### Flat mapping
- Let say we have a list of cities and for each city we have a list of people living in the cities
- I would like to extract all the people from all the cities and put them in a single list
<table>
  <tr>
    <th>New York</th>
    <th>Paris</th>
    <th>London</th>
<tbody>
  <tr>
    <td>Paul</td>
    <td>Julie</td>
    <td>Ann</td>
  </tr>
  <tr>
    <td>Sarah</td>
    <td>Charles</td>
    <td>Charlotte</td>
  </tr>
  <tr>
    <td>James</td>
    <td>Boris</td>
    <td>Emily</td>
  </tr>
</table>
And I want the output to be a list of people: Paul, Julie, Ann, Sarah, Charles, Charlotte, James, Boris, Emily <br>
- We have a Stream of Cities but we want a Stream of Persons

```java
List<City> cities = new ArrayList<>();

Function<City, Stream<Person>> flatMapper = 
    city -> city.getPeople().stream();

long count = 
        cities.stream()         //Stream<City>
        .flatMap(flatMapper)    //Stream<Person>
        .count();
```

___So FlatMap operation is defined by a function that takes an object and returns a stream of other objects___

---

### Converting a For Loop to a Stream

We are going to refactor this code:
```java
List<Person> people = ...;

int sum = 0;
int count = 0;

for(Person person : people){
    if(person.getAge() > 20){
        sum += person.getAge();
        count++;
    }
}

double average = 0d;
if(count > 0){
    average = (double) sum / count;
}
``` 
This code is going to calculate the average age of people above 20. We are going to refactor this code to use the Stream API.

```java
int sum = 0;
        int count = 0;

        double average = people.stream()
                .mapToInt(p -> p.getAge()) //Stream of int primitive, because we are going to calculate the sum which we can not do from INTEGER (which would be the return type of map)
                .filter(age -> age > 20)
                .average()
                .orElseThrow(); //orElseThrow is a terminal operation, it is going to consume the stream and return the result
        
        System.out.println("Average: " + average);
```
