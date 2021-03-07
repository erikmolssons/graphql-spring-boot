FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/graphql-1.jar api.jar
ENTRYPOINT ["java","-jar","/api.jar"]
