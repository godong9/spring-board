# spring-board
Spring board project

# Generate doc
```
$ ./apidoc.sh
```

# MySQL 설치 및 test 계정 추가
- id: test, pw: test 계정 추가 및 DB 권한 추가 (board 테이블 모든 권한 추가)

# DB 설정
- board.sql 쿼리 실행

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