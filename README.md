# Development
```
cat test.txt | java main/App.java
```
d
# Build
```
javac main/App.java
jar cfm app.jar ./manifest.txt main/*
```

# Run
```
cat poker-hands.txt | java -jar app.jar
```
