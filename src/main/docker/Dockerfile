FROM openjdk:8-jre-alpine

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    BOILERPLATE_SLEEP=0 \
    JAVA_OPTS=""

# Add a boilerplate user to run our application so that it doesn't need to run as root
RUN adduser -D -s /bin/sh boilerplate
WORKDIR /home/boilerplate

ADD entrypoint.sh entrypoint.sh
RUN chmod 755 entrypoint.sh && chown boilerplate:boilerplate entrypoint.sh
USER boilerplate

ADD *.war app.war

ENTRYPOINT ["./entrypoint.sh"]

EXPOSE 8081

