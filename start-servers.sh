source ./stop-servers.sh
./gradlew clean build

docker build -t spring-boot-actor .


docker run -d -p 8080:8080 -e SPRING_PROFILES_ACTIVE=server-1 --name spring-boot-actor-1 spring-boot-actor
docker run -d -p 8081:8081 -e SPRING_PROFILES_ACTIVE=server-2 --name spring-boot-actor-2 spring-boot-actor
docker run -d -p 8082:8082 -e SPRING_PROFILES_ACTIVE=server-3 --name spring-boot-actor-3 spring-boot-actor
