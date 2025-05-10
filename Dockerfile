FROM openjdk:21

# JAR 복사
COPY ./build/libs/store-service.jar store-service.jar

# 기본 ENV 설정 (선택 사항)
ENV SPRING_PROFILE=prod
ENV TZ=Asia/Seoul

# 실행 시점에 외부에서 프로파일 주입 가능하게 설정
ENTRYPOINT ["sh", "-c", "java -Dspring.profiles.active=${SPRING_PROFILE} -Duser.timezone=${TZ} -jar store-service.jar"]
