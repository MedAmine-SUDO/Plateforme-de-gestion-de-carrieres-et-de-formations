#!/bin/bash

mvn clean package
docker build -t akremzerelli/stage2020:competence-data-service .
docker login 
docker push akremzerelli/stage2020:competence-data-service
