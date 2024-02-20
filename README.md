# prerequisites
- JDK version 11+
- Unix-based machine (For windows, command to build and run java app might be different)

# Build
```
javac main/App.java
jar cfm app.jar ./manifest.txt main/*
```

# Run
```
cat poker-hands.txt | java -jar app.jar
```

# Unit-testing
```
javac main/App.java
java main.App test
```
