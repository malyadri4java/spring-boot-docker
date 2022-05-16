FROM openjdk:8-jre-alpine
WORKDIR /opt
ENV PORT 8080
EXPOSE 8080
ARG JAR_FILE=spring-boot-docker.jar
ADD $JAR_FILE /opt/spring-boot-docker.jar
ADD entrypoint.sh /opt/entrypoint.sh
ENTRYPOINT exec java $JAVA_OPTS -jar spring-boot-docker.jar
#ENTRYPOINT ["bash", "entrypoint.sh"]
