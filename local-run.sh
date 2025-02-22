docker build -t course-microservice .
minikube image load course-microservice:latest
kubectl delete secret course-service-secret
kubectl create secret generic course-service-secret --from-env-file=local.env
kubectl delete deployment course-service-deployment
kubectl apply -f local-deployment.yaml