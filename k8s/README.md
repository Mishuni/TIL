# Kubernetes(k8s)

>  쿠버네티스

```sh
# start (Linux)
$ sudo minikube start --vm-driver=none

# start ingress
$ sudo minikube addons enable ingress
$ sudo kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/master/deploy/static/provider/cloud/deploy.yaml

# 모든 리소스들 삭제
$ sudo kubectl delete deployment,pods,rs,services,secret --all
```

