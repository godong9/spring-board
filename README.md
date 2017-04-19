# spring-board
Spring board project

# Generate doc
```
$ ./apidoc.sh
```

# Server build
```
$ cd /board 
$ ./gradlew build -x test 
```

# Start spring server
```
$ cd /board/build/libs
$ java -Dspring.profiles.active=local -jar board-0.0.1-SNAPSHOT.jar
```