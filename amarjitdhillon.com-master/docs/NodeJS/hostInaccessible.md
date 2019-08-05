# NPM registery settings

???+ tip "Warning"
	 WARN: registry Using stale data from http://yourHostName because the host is inaccessible -- are you offline?

This happens when you might be using proxy to get the packages. Steps to solve this issue are.

1. Setting strict ssl to false.
```sh
npm config set strict-ssl false
```

1. Then set npm to run with http, instead of https:
```sh
npm config set registry "http://registry.npmjs.org/"
```

1. Try to reisntall your packages or run `npm install` 

1. Additionally, you can set the proxy using the below commands

```sh
npm config set proxy ttp://myusername:mypassword@proxy.us.somecompany:8080
npm config set http-proxy ttp://myusername:mypassword@proxy.us.somecompany:8080
npm config set https-proxy ttp://myusername:mypassword@proxy.us.somecompany:8080
```

In this case you will need to encode the special characters in the URL.

----

You can update your `./bashrc` with the following settings. Credits to [Katie](https://stackoverflow.com/a/18346387/6020610) for sharing these settings.

```sh
######################
# User Variables (Edit These!)
######################
username="myusername"
password="mypassword"
proxy="mycompany:8080"

######################
# Environement Variables
# (npm does use these variables, and they are vital to lots of applications)
######################
export HTTPS_PROXY="http://$username:$password@$proxy"
export HTTP_PROXY="http://$username:$password@$proxy"
export http_proxy="http://$username:$password@$proxy"
export https_proxy="http://$username:$password@$proxy"
export all_proxy="http://$username:$password@$proxy"
export ftp_proxy="http://$username:$password@$proxy"
export dns_proxy="http://$username:$password@$proxy"
export rsync_proxy="http://$username:$password@$proxy"
export no_proxy="127.0.0.10/8, localhost, 10.0.0.0/8, 172.16.0.0/12, 192.168.0.0/16"

######################
# npm Settings
######################
npm config set registry http://registry.npmjs.org/
npm config set proxy "http://$username:$password@$proxy"
npm config set https-proxy "http://$username:$password@$proxy"
npm config set strict-ssl false
echo "registry=http://registry.npmjs.org/" > ~/.npmrc
echo "proxy=http://$username:$password@$proxy" >> ~/.npmrc
echo "strict-ssl=false" >> ~/.npmrc
echo "http-proxy=http://$username:$password@$proxy" >> ~/.npmrc
echo "http_proxy=http://$username:$password@$proxy" >> ~/.npmrc
echo "https_proxy=http://$username:$password@$proxy" >> ~/.npmrc
echo "https-proxy=http://$username:$password@$proxy" >> ~/.npmrc

######################
# WGET SETTINGS
# (Bonus Settings! Not required for npm to work, but needed for lots of other programs)
######################
echo "https_proxy = http://$username:$password@$proxy/" > ~/.wgetrc
echo "http_proxy = http://$username:$password@$proxy/" >> ~/.wgetrc
echo "ftp_proxy = http://$username:$password@$proxy/" >> ~/.wgetrc
echo "use_proxy = on" >> ~/.wgetrc

######################
# CURL SETTINGS
# (Bonus Settings! Not required for npm to work, but needed for lots of other programs)
######################
echo "proxy=http://$username:$password@$proxy" > ~/.curlrc
```
