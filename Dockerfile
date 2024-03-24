# Stage 1: Use Maven to build the application
FROM maven:3.8.6 AS build
WORKDIR /app
# Copy the pom.xml file separately to leverage Docker layer caching
COPY pom.xml .
# Ideally, download dependencies in a separate step to leverage caching
RUN mvn dependency:go-offline
# Copy the source code and package the application without running tests
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Use OpenJDK to run the application
FROM openjdk:18
WORKDIR /app
# Copy the built jar file from the build stage into this image
COPY --from=build /app/target/ss.demo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]



