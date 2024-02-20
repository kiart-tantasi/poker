# prerequisites
- JDK version 11+
- Unix-based machine (For windows, command to build and run java app might be different)

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
