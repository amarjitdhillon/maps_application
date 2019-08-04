
!!! info ""
	If you are getting `java_home is not defined correctly in Ubuntu`, then you are at right place.


I was getting this error as I was trying to update the Java home in `~\.bashrc` and `~\.bash_profile`. Follow these steps to solve this issue. 

1. Open the `/etc/environment` file as

	```bash
	vim /etc/environment
	```

1. Then set `java_home` using

	```bash
	JAVA_HOME="/usr/lib/jvm/java-8-oracle"
	export JAVA_HOME
	```

1. Open bash profile as

	```bash
	vim ~\.bash_profile
	```

1.  Then add the following,

	```bash
	.  /etc/environment
	```

	It will load the `/etc/environment` every time terminal is started

1. Then confirm the path using

	```bash
	echo $JAVA_HOME
	```
