FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY target/SmaguciaiTest-1.0-SNAPSHOT.jar  app.jar

CMD java -Dserver.port=$PORT -jar app.jar
