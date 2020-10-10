FROM adoptopenjdk/openjdk8
WORKDIR /opt
ENV PORT 8051
EXPOSE 8051
COPY target/*.jar /opt/app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar
