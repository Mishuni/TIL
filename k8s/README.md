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



# lightweight kubernetes (k3s)

```sh
## master
$ curl -sfL https://get.k3s.io | sh -s - --node-external-ip 182.252.132.33
$ sudo cat /var/lib/rancher/k3s/server/node-token
K10ef02c656a8f3a8165a3f7a260499ca129304c61d9c4d8652724a994b5436a9af::server:3ec52712687e3bc56c35b7ffd0f7d291
```

```sh
## worker
$ curl -sfL https://get.k3s.io | K3S_URL=https://182.252.132.33:6443 K3S_TOKEN=K10ef02c656a8f3a8165a3f7a260499ca129304c61d9c4d8652724a994b5436a9af::server:3ec52712687e3bc56c35b7ffd0f7d291 sh - 

or

$ sudo k3s agent --server https://182.252.132.33:6443 --token K10ef02c656a8f3a8165a3f7a260499ca129304c61d9c4d8652724a994b5436a9af::server:3ec52712687e3bc56c35b7ffd0f7d291
```