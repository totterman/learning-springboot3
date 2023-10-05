# learning-springboot3

Test:

$ ./mvnw clean test

Run:

$ ./mvnw spring-boot:run

Uber-jar:

$ ./mvnw clean package

$ java -jar target/mvn-springboot3-0.0.1-SNAPSHOT.jar


Container:

$ ./mvnw spring-boot:build-image

$ docker run -p 8080:8080 docker.io/library/mvn-springboot3:0.0.1-SNAPSHOT