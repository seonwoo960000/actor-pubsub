./gradlew clean build
docker build -t devvy/spring-boot-akka .

kubectl create namespace spring-akka
kubectl apply -f ./local-deployment.yaml

# run this command in order to connect to your cluster
# kubectl port-forward svc/akka-cluster-spring-demo 8080:8080 -n spring-akka
