#EXERCISE

The goal is to read a file named "input.txt" which will contain several phone numbers. These phones can have two possible valid formats, being classified into:

- shortNumber
- longNumber

It is expected that at the end of processing, the console will display a descending list of the number of phones by country.

To run the program just run the following command line:

java -jar exercise.jar input.txt

you should see output similar to this:

#Response.....
- Portugal=5
- Canada=1
- United Kingdom=1

to create the project we used maven (https://maven.apache.org/) which serves as a dependency manager and application build creation;

as main features of the algorithm, the api stream was used, as well as some functional interfaces.
(https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html)

a basic unit test class was also created with JUnit that validates the existence of the files.

improvement points:

1 - Better use regular expressions (regex) to solve some validations in a more optimized way;

2 - check a more performative way of identifying country codes in the function that creates this mapping.
