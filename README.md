#GraphQL Spring boot
Spring Boot web service with GraphQL & GraphiQL together with MongoDB, MongoDB Express. \
sends async HTTP requests to https://pokeapi.co/ for mongodb seeding. \
Fetches the first generation Pokemons.
## To run
while in working folder or run the deploy.sh shell script.
```
mvn clean package -Dmaven.test.skip=true
docker build -t graphql .
docker-compose up -d
```
## GraphiQL
GraphiQL is an in-browser tool for writing, validating, and
testing GraphQL queries.
* http://localhost:8080/graphiql
## Mongo Express
Web based MongoDB admin interface
* http://localhost:8081/
