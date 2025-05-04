# Java 21 slim 이미지 사용
FROM openjdk:21-jdk
# 앱 폴더 생성 및 jar 복사
COPY build/libs/*SNAPSHOT.jar app.jar
# 앱 실행
ENTRYPOINT ["java", "-jar", "/app.jar"]