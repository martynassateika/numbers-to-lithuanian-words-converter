# Numbers to Lithuanian words converter

A Java 8 library allowing conversion of numbers to Lithuanian words.

## Building from source

* Clone the project
* `cd` into the project directory
* Run `mvn clean install`

## Example

```java
NumberConverter converter = NumberConverter.create();
String words = converter.toLithuanian(123); // "šimtas dvidešimt trys"
```

## Links

- [GitHub project](https://github.com/martynassateika/numbers-to-lithuanian-words-converter)
- [Issue tracker](https://github.com/martynassateika/numbers-to-lithuanian-words-converter/issues/new)
