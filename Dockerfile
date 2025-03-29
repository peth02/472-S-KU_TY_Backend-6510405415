FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

COPY . .

RUN chmod +x ./mvnw

RUN ./mvnw clean install -DskipTests

FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]