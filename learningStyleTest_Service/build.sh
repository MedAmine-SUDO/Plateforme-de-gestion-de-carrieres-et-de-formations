#!/bin/bash

mvn clean package 
docker build -t aminos007/stage-ete:test-profile-service .
docker login
docker push aminos007/stage-ete:test-profile-service
