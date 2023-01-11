#define base docker image 
FROM openjdk:8-alpine	
#COPY target/crud-0.0.1-SNAPSHOT.jar
#CMD java -jar crud-0.0.1-SNAPSHOT.jar


ADD target/crud-0.0.1-SNAPSHOT.jar crud.jar


ENTRYPOINT ["java","-jar","crud.jar"]



