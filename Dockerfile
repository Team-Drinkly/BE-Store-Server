FROM openjdk:21

ARG SPRING_PROFILE=prod
ENV SPRING_PROFILE=$SPRING_PROFILE
ENV TZ=Asia/Seoul

COPY ./build/libs/store-service.jar store-service.jar

ENTRYPOINT ["sh", "-c", "java -Dspring.profiles.active=${SPRING_PROFILE} -Duser.timezone=${TZ} -jar store-service.jar"]