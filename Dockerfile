FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY target/SmaguciaiTest-0.0.1-SNAPSHOT.jar app.jar

CMD java -Dserver.port=$PORT -jar app.jar
