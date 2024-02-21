# Explanation

## Flowchart representing how app works

<img src='flowchart.png' >

## Tie

tie needs to be detailedly handled. several ranks need really specific method to compare players' hands
- `four of a kind` - comparing four-kind before comparing high-card
- `full house` - comparing three-kind before comparing two-pairs
- `three of a kind` - comparing three-kind before comparing high-card
- `two pairs` - comparing two-pairs before comparing high-card
- `pair` - comparing pair before comparing high-card
- `all other cases` - comparing high-card

<br><br>

# Build, Run and Test

## prerequisites
- JDK version 11+
- Command lines "javac", "jar" and "java"

## Build
```
javac app/App.java
jar cfm my-poker-solution.jar ./manifest.txt app/*.class
```

## Run
```
cat poker-hands.txt | java -jar my-poker-solution.jar
```

## Unit-testing
```
javac app/App.java
java app.App test
```
