FROM openjdk:21
COPY ./build/libs/store-service.jar store-service.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-Duser.timezone=Asia/Seoul", "-jar", "store-service.jar"]
#ENTRYPOINT ["java", "-Dspring.profiles.active=prod",  "-Duser.timezone=Asia/Seoul", "-jar", "store-service.jar"]