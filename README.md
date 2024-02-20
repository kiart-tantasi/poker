# prerequisites
- JDK version 11+
- Command lines "javac", "jar" and "java"

# Build
```
javac app/App.java
jar cfm app.jar ./manifest.txt app/*
```

# Run
```
cat poker-hands.txt | java -jar app.jar
```

# Unit-testing
```
javac app/App.java
java app.App test
```
