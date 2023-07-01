FROM amazoncorretto:11
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:9091
COPY target/*.jar app.jar
#ENV TZ="Europe/Moscow"
ENTRYPOINT ["java","-jar","/app.jar"]