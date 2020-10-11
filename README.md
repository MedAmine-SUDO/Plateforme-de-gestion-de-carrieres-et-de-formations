# Plateforme-de-gestion-de-carrieres-et-de-formations

# How to run a service using docker compose:
Simply choose the service (or services) you want to run and add them to docker-compose.yml and then in the terminal: docker-compose up

## Docker compose of each service:
 competence-data-service:  
    image: akremzerelli/stage2020:competence-data-service  
    ports:  
      - 8051:8051  

  authentication-service:  
    image: akremzerelli/stage2020:authentication-service  
    ports:  
      - 8081:8081  

  profiles-data-service:  
    image: akremzerelli/stage2020:profiles-data-service  
    ports:  
      - 8082:8082  

  formations-data-service:  
    image: akremzerelli/stage2020:formations-data-service  
    ports:  
      - 8083:8083  

  test-niveau-service:  
    image: aminos007/stage-ete:test-niveau-service  
    ports:  
      - 8060:8060  

  test-profile-service:  
    image: aminos007/stage-ete:test-profile-service  
    ports:  
      - 8061:8061  
