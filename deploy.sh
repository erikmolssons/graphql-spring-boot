#!/bin/bash
mvn clean package -Dmaven.test.skip=true
docker build -t graphql .
docker-compose up -d
