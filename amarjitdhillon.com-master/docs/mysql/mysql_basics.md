## Installing MySQL

```bash
brew install mysql
```

Now, you have installed your MySQL database without a root password. To secure it run the following

```bash
mysql_secure_installation
```
## Connecting to MySQL

Enter the below command
```bash
mysql -u root
```

Use brew to manage MySQL using following commands

```bash
brew services start mysql 	# This will register to run it at bot
brew services run 	  		# Run the service formula without registering to launch at login
brew services stop  		# Stop service immediately and unregister it
```

To run as background process, by default run the following 

```bash
mysql.server start  # start MySQL
mysql.server status # check status
mysql.server stop   # stop the server
```

To reset the password for MySQL you first must create a new file with the following contents

```
ALTER USER 'root'@'localhost' IDENTIFIED BY 'PASSWORD';
```

Now, login using the password by using the below command

```
mysql -u root -p
```

## Forgot MySQL password?

perform the following steps

1. Stop the server
1. Start server using `sudo mysqld_safe -skip-grant-tables -skip-netwroking &`
1. Connect to server using root as `mysql -u root`. Now, run the following commands in terminal
	```sql
	use mysql;
	​update user set authentication_string=password('NEWPASSWORD') where user='root';
	​flush privileges;
	​quit
	```
1. Login to MySQL server again and enter the new password.

## change database using

```sql
show databases;
use yourDatabaseName; # replace yourDatabaseName with your database name
show tables; 
```

# Downloading using GUI (preferred option)

Go to https://dev.mysql.com/downloads/mysql/ and download MySQL for your OS.



