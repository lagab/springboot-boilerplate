#!/bin/sh

echo "The application will start in ${BOILERPLATE_SLEEP}s..." && sleep ${BOILERPLATE_SLEEP}
exec java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar "${HOME}/app.war" "$@"
