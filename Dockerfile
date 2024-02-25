# Use Maven to build the application
FROM maven:3.8.6 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Ensure that the package without tests is properly compiled
RUN mvn clean package -DskipTests

# Use OpenJDK to run the application
FROM openjdk:18
WORKDIR /app
# Copy the built jar file from the previous stage into this image
COPY --from=build /app/target/ss.demo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]


