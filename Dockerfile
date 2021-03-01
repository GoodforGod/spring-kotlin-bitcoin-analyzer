FROM adoptopenjdk/openjdk11-openj9:alpine-slim

ARG TARGET=/opt/application
ARG SOURCE=build/libs/*.jar

RUN mkdir -p "$TARGET"
ADD $SOURCE $TARGET/app.jar

EXPOSE 8080/tcp

CMD ["/bin/sh","-c","java -jar -Dfile.encoding=UTF-8 -XX:+UseContainerSupport /opt/application/app.jar" ]