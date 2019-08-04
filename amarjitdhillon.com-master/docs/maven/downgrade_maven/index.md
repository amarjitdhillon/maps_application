# Downgrading maven version in brew

Check current version of `maven` using

```
mvn -verison
```

This will give you current version and the installation path as well. In my case current version is 3.5.0 as shown below

<center><img src="images/1.png" width="90%"></center>


Then search for available versions of maven using

```
brew search maven
```

This will show you all the available versions as shown below

<center><img src="images/2.png" width="90%"></center>


If you want to install maven 3.0 for instance, then type

```bash
brew install maven@3.0
```

This will install the downgraded version and show some caveats as well

<center><img src="images/3.png" width="90%"></center>


Now the next step is to unlink older maven version and then overwrite it with version you just installed

```bash
brew unlink maven
brew link --overwrite maven@3.0
```

Now check version of maven again. if everything went well ,then you will see that version has been downgraded.

<center><img src="images/4.png" width="90%"></center>

