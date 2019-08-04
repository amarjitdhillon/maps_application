# Accessing the H2 Database for WSO2 Products

Most of the WSO2 products comes with the H2 database, I have been facing some issues in order to access this database. You can follow approach A or B, in my opinion B is easier. Common step is a must


## Common Step

Open the `carbon.xml` file to enable the access to `h2` database.

<center>![](../images/1.png)</center>

As I am using the WSO2 IoT server, this file is located in the `IoT_HOME/conf/carbon.xml` as shown above. Open this file and uncomment the following

```
<H2DatabaseConfiguration>
 <property name=”web” />
 <property name=”webPort”>8082</property>
 <property name=”webAllowOthers” />
 <property name=”webSSL” />
 <property name=”tcp” />
 <property name=”tcpPort”>9092</property>
 <property name=”tcpAllowOthers” />
 <property name=”tcpSSL” />
 <property name=”pg” />
 <property name=”pgPort”>5435</property>
 <property name=”pgAllowOthers” />
 <property name=”trace” />
 <property name=”baseDir”>${carbon.home}</property>
 </H2DatabaseConfiguration>
```

## Approach A: Using Web Browser

1. Install the `H2` database: If you are Mac user, then enter `brew install h2` in terminal to install h2 database and then type `h2` in terminal to start it .The h2 database will start and can be accessed from `http://192.168.0.16:8082/`. If you are using the windows or Linux then install `h2` from [here](http://www.h2database.com/html/download.html)

1. Then copy the path for the database you want to open, In my case the database path I want to access is shown below <center>![](../images/2.png)</center>


!!! Watch out 
	Do not copy the `h2.db` part of the file. For example path I copied is

	```sh
	/Users/amar/Documents/ThesisCode/CEP_codes/wso2iot-3.3.0_new/wso2/broker/repository/database/WSO2MB_DB
	```

Now go the `http://192.168.0.16:8082/` and choose the `generic H2` then enter the JDBC url . Append the `jdbc:h2:file:` in front of path as shown in below screen-shot. Then enter `username` and `password` as `wso2carbon`. Press connect<center>![](../images/3.png)</center>


## Approach B: Using IntelliJ IDEA

Now the same can be done in IntelliJ Idea which also provides access to databases. For accessing databases maybe you need a ultimate version of it, which is free for students under University account. Open IntelliJ Idea and click on add new database as shown below

<center>![](../images/4.png)</center>


Select `H2` . You have to install the H2 drivers for first time.

!!! info 
	Before going further, you have to close all existing connections to the `H2`, and stop `H2` if its running in terminal.
	<center>![](../images/5.png)</center>


As shown above, first choose Embedded database type from drop down menu, then browse your file using `...` option. Make sure the `h2.db` path does not contain `h2.db`. Enter `username` and `password` as `wso2carbon`. Thats it!

<center>![](../images/6.png)</center>


Hope, it helped 😀