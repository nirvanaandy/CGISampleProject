# Sample Project for CGI/ANZ

## Environment

- IDEA 2019.2
- JDK 11
- Tested on MySql and H2
- H2 Console: http://localhost:8080/api/v1/h2-console
- API code is in ./APICode folder

## API Doc

- SWagger UI: http://localhost:8080/api/v1/swagger-ui/

- Two main APIs,
1. /api/v1/accounts/querybyuser/{userid} 

  Sample: /api/v1/accounts/querybyuser/123
  
2. /api/v1/transactions/querybyaccount/{accountNumber}

  Sample: /api/v1/transactions/querybyaccount/585309209

## Docker File

FROM openjdk:11-jre-slim

VOLUME /tmp

ADD target/sampleproject-0.0.1-SNAPSHOT.jar /app.jar

EXPOSE 8080

ENV JAVA_OPTS=""

ENTRYPOINT exec java $JAVA_OPTS -jar /app.jar

##Provide a Sample Front End ./SampleFrontEnd

To start the server for front side:

java -jar sampleprojectfrontend-0.0.1-SNAPSHOT.jar

Then access:
http://localhost:8083/account.html 

## Memo 
As could not discuss detail requirements, I implemented on my own assumptions on like DB structure, response type etc. Truly, there would be many discussion on the requirements in real development work.   

- Use EHCache, and this is configurable by "myapplication.setting.enableCache" in application.properties.
- Providing pagination from server side. Page size can be set by "myapplication.setting.pageSize" in application.properties.
- Use HATEOAS driven response, could be plain JSON, someone don't like HATEOAS.
