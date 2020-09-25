FROM adoptopenjdk/openjdk8
WORKDIR /opt
ENV PORT 8090
EXPOSE 8090
COPY target/*.jar /opt/app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar
