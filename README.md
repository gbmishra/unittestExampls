# What exactly is Unit Testing?

In extremely simple words, unit testing means, you test a very small portion of your code. It can be just a single method,
or a small feature consisting of a few methods etc. It depends on how you define a unit.

e.g., testing the below method could be a unit test:

```
public Integer addTwoIntegers(Integer x, Integer y) {
  return x + y;
}
```

Here, we can test
* is it correctly adding two positive numbers ?
* is it correctly adding two negative numbers ?
* what if the ints are TOO BIG?
* what if both x and y are null ?

# Why do we need any testing at all? I know my code works!
Building a software, is not just writing code. It is 'engineering'. Thats why programmers are also called as 'software engineers'.
So, lets see what other engineering field guys do: https://www.youtube.com/watch?v=n8vf9EJBBfw

Testing ensures:
* that your application works as expected
* that it handles exceptional cases well
* that its behavior is well defined in all possible scenarios. Comparing a software with a Car:
  * We ask for max speed of a bike or car. What if somebody asks whats the processing time of your API?
  * We say, how many people and how much luggage can it take? In software, a similar question will be, 'how much data can your application process?'
  * For cars, we are interested to know, if it can run fine on bad roads, heavy rains or in desert? In software, a similar question will be 'Can the application run when only 250MB is available?'

Software development is a subject of 'engineering', so we must follow good engineering practices to ensure the software runs smooth and nice.

## Alright, but cars kill people, software doesn't.

Think again: 
* 64 bit to 16 bit conversion caused billions of loss: http://www-users.math.umn.edu/~arnold/disasters/ariane.html
* Programmers cheating the car emission test caused worldwide shaming of Volkswagen: https://www.theguardian.com/business/ng-interactive/2015/sep/23/volkswagen-emissions-scandal-explained-diesel-cars
* Death due to bug in self driving car: https://arstechnica.com/tech-policy/2018/05/report-software-bug-led-to-death-in-ubers-self-driving-crash/

## What are other types of testing ?
End to end system testing.
Regression tests.
Integration tests.

# How is unit testing done?
In unit tests, you focus on a small unit at a time. For example, a calculator class does a lot of calculations: addition, multiplication, log,
sine, cosine, power etc. In unit test, you test one function at a time. 

A badly written code:
~~~
class Calculator {
   public Number calculate(Number a, Number b, Function whatToDo) {
     if (whatToDO == addition) {
        return a + b;
     }
     if (whatToDo == multiplication) {
        return a * b;
     }
     .....
     .....
   }
}
~~~

In the above sample code, a huge method does all the processing. Testing this kind of a method will always be difficult with so many if else
conditions and so much of business logic at one place.

Nicely written and well structured code is also very easy for testing. If we restructure the code as :

~~~
class Calculator {
   public Number add(Number a, Number b) {
      return a + b;
   }
   public Number multiply(Number a, Number b) {
      return a * b;
   }
   .....
}
~~~

In the above case, you can test each method independently, and the test cases will also be simple and maintainable.

In any unit test, there are a few variables involved:
* What is the input data ? // x = 1 and y = 2
* What is the expected output for addition ? // 3
* What is the real output ? // 5
* Is expected output == real output ? // 3 == 5 ?? OH NO, WE FORGOT MATHS !!!

## What is mocking?
Unfortunately, software isn't always as simple as the above calculator code. In reality, software can be as complex as a real car system, or 
probably even worse!

In reality, classes depend on each other. Programs write to database. Programs make network calls. Programs do many fancy things! So, if I don't
have a database installed in my system, how am I going to test it?

Lets see the above car crash video again.

The crash tests are not being done with a 'real human' inside. The car crash is not being done on the outside (they also do that, but that will be more like
end to end testing). The car is running with a 'dummy' human. The car is running on a 'dummy road'.

So, that is mocking. 
Lets add another variable to the above list then:

* Dependent systems behavior

In the example of a car:
* If it is heavily raining, can I drive at 100 kmph?
Now, we can't wait for a heavy rain to come and then test. So they 'simulate' rain with fake rain, and do the tests.
* If the car gets crashed head-on at 120 kmph, will the airbag protect the driver?
They wont of course test with a real human here, so they use a 'dummy'.

In short, they test for vehicle behavior in various *simulated* conditions. This 'simulation' is usually known as *mocked* in software testing world.

## When do you need mocking?

Similar to the car example above, lets say I have a class, that reads from database, does some network call, and writes to database.

~~~
class BuisnessLogic {
  private DatabaseRepository databaseRepository;
  private NetworkCall networkCall; // some remote service
  
  public void processSomething(int id) {
     SomeEntity someEntity = databaseRepository.findById(id);
     // do some fancy business logic
     businessLogic(someEntity);
     databaseRepository.save(someEntity);
  }
  
  private void businessLogic(SomeEntity someEntity) {
     boolean callResult = networkCall.call(someEntity);
     // if call fails
     if (!callResult) {
        throw new RuntimeException();
     }
     // otherwise do more business logic
     ....
     ....
  }
}
~~~

Lets see what things we need to mock here:

There are 2 dependent classes. `DatabaseRepository` and `NetworkCall`. If we don't mock them, then we cannot test it if we do not have
a database installed on our system, or we cannot test it if our wi-fi is not working (because of the network call). It is kind of similar to 
car crash testing!

So, we mock those 2 dependent things. We *set the behavior* of those 2 mocks, and test if our code works as expected in all those environments.
For e.g.,

* Case 1:
  * Given that
     * Database returns a valid entity
     * Network call with that entity succeeds
  * When I call `processSomething`
  * Then
     * it should read from db once
     * it should make network call once with the `someEntity` read from database
     * it should do the business logic
     * and write to database
     
 You can compare the above case to car crash test as, given that the driver is a good driver, and road condition is good, and weather is nice
 then the car should run smoothly.
     
* Case 2:
  * Given that
    * Database returns null (simulating that database cannot find the entity)
    * Network call with null entity fails
  * When I call `processSomething`
  * Then
    * It should read from db once
    * It should do network call with null entity
    * it should throw `RuntimeException`
    * It should not call write to database
    
    
In short, during unit tests, we 'define' the behavior of dependent systems, and then test how our own class/method works in those conditions.
To beginners, it becomes easier to understand, when you compare with real world situations.
    
     
  



