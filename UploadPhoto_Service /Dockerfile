FROM adoptopenjdk/openjdk8
WORKDIR /opt
ENV PORT 8091
EXPOSE 8091
COPY target/*.jar /opt/app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar
