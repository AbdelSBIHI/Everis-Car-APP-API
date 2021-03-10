FROM openjdk:8-jre-alpine
COPY startup.sh startup.sh
COPY target/car-api-microbundle.jar car-api.jar
EXPOSE 8080
CMD ["/bin/bash", "startup.sh"]