# Recruitment_task
## Backend
Spring Boot application  with Junit5 Mockit for tests. Application can be run as standalone, but it requires changing **application.properties** file to specify connection to database

hostname - for local 'localhost', once dockerized, 'postgres'
spring.datasource.url=jdbc:postgresql://{hostname}:5432/github_user


Running localhost mode will need additional steps to run postgres DB instance in a separate container or standalone application 

### Buidling application
Can be build using Intellij gradle commands or from command line
```
./gradlew build
```
### Dockerizing

Change directory to **recruitment_task/backend**  

```
docker build --build-arg JAR_FILE=build/libs/application-0.0.1-SNAPSHOT.jar -t test/backend .
```

Tag for docker container *test/backend* is neccessary becaouse it's specified in docker-compose.yml for whole task
instantiation

## Frontend

React application, implementing cart

### Dockerizing

Change directory to **recruitment_task/frontend**  

```
docker build -t test/frontend .
```

Tag for docker container *test/backend* is neccessary becaouse it's specified in docker-compose.yml for whole task
instantiation


### Docker compose
When all the images were builded, change directory to project root **recruitment_task**. 

```
docker compose up -d or docker-compose up -d (for previouces docker compose versions)
```

### Testing Application

Frontend available at http://localhost:3030
Backend available at http://localhost:8080
PgAdmin container for checking Postgres DB available at http://localhost:80

To connect to DB use credentials specified in **recruitment_task/docker-compose.yml** as an environment variables










