## Zerotier

```sh
$ curl -s 'https://raw.githubusercontent.com/zerotier/ZeroTierOne/master/doc/contact%40zerotier.com.gpg' | sudo gpg --import && \
if z=$(curl -s 'https://install.zerotier.com/' | gpg); then echo "$z" | sudo bash; fi

or 

$ curl -s https://install.zerotier.com | sudo bash
*** Success! You are ZeroTier address [ 649f7e03be ].

# create network at ZeroTier Centeral
# join the network (networkID : 9f77fc393ecbd925)
$ sudo zerotier-cli join 9f77fc393ecbd925

# leave the network
$ sudo zerotier-cli leave 9f77fc393ecbd925

# uninstall zerotier
$ sudo apt remove zerotier-one

# zerotier settings
$ sudo vim /var/lib/zerotier-one/local.conf
{
 "settings":{
  "interfacePrefixBlacklist":["docker","veth","flannel","cni"]
 }
}
$ sudo service zerotier-one restart
```

## K3S

```sh
# master (172.30.226.60)
# if zerotier network interface is 'ztuzetpya3'
$ curl -sfL https://get.k3s.io | sh -s - --docker --node-ip 172.30.226.60 --advertise-address 172.30.226.60 --flannel-iface ztuzetpya3 --node-name master

# use command of k3s without sudo
$ sudo chmod -R 777 /etc/rancher/k3s/k3s.yaml
# use command of k3s without k3s and use python k8s client API
$ export KUBECONFIG=/etc/rancher/k3s/k3s.yaml
$ sudo cat /var/lib/rancher/k3s/server/node-token
K10ba4bd77d7f22ffbeeefa8125086cb237ac48127235b5ecc4b68bbc1f483a80b6::server:1620e7c68c23073149c232eb245ee11f

or 

$ sudo chmod -R 777 /etc/rancher/k3s/k3s.yaml && export KUBECONFIG=/etc/rancher/k3s/k3s.yaml && sudo cat /var/lib/rancher/k3s/server/node-token
```

```sh
# agent (172.30.77.241)
$ curl -sfL https://get.k3s.io | K3S_URL=https://172.30.226.60:6443 K3S_TOKEN=K10ba4bd77d7f22ffbeeefa8125086cb237ac48127235b5ecc4b68bbc1f483a80b6::server:1620e7c68c23073149c232eb245ee11f sh -s - --docker --flannel-iface ztuzetpya3 --node-ip 172.30.77.241 --node-name tx2-ms
```

#### Debug

```sh
# restart the k3s
$ sudo service k3s restart

# show network list
$ sudo zerotier-cli listnetworks

# iptable flush
$ sudo iptables -L
$ sudo iptables -F

# show log
$ vi /var/log/syslog
$ sudo service kubelet status
$ sudo journalctl -u k3s
```

#### reference

[zerotier](https://www.zerotier.com/)

[zerotier Centeral](https://my.zerotier.com/)

[k3s arguments](https://rancher.com/docs/k3s/latest/en/installation/install-options/server-config/)