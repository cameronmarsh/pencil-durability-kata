# pencil-durability-kata

This project is my implementation of the Pencil Durability Kata exercise. This code is a [Maven](https://maven.apache.org/download.cgi) project that is implemented in Java.
The program's model classes can be found in `src/main/java/` and testing files can be found in `src/test/java/`.

## Buiding and Running

To build and run this project from the command line, use the Maven CLI:

Build: `mvn compile`

Run tests: `mvn test`

## Assumptions

In implementing the requirements of this kata, I assumed the following during my implementation:

* Every pencil object is initialized with the following values, and will not receive bad input:
    * a Paper object on which to hold the current state of the text
    * the durability value of the pencil's point
    * the length of the pencil
    * the durability of the eraser
* Numbers and punctuation degrade the pencil point and eraser by a value of one
* The pencil's eraser is case sensitive, and if there is not an exact match in the existing print, no words will be erased
* When the pencil's point is dulled down to a value of one, it won't write uppercase characters, but will write the next lowercase or punctuation character
* All whitespace characters maintain the degradation value of the pencil's point and eraser
* The pencil is sharpened upon instantiation
* When trying to edit text and the replacement word goes beyond the length of the text, it will append the rest of the replacement word to the end of the existing text
