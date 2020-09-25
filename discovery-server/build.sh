#!/bin/bash

mvn clean package
docker build -t akremzerelli/stage2020:discovery-server .
docker login 
docker push akremzerelli/stage2020:discovery-server
