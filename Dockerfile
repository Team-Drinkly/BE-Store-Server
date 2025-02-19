FROM openjdk:21
COPY ./build/libs/store-service.jar store-service.jar
#ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "store-service.jar"]
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "store-service.jar"]