
# Installing Apache Flink

Building Apache Flink is very easy yet it took approximately 30 minutes.


## Steps for installing apache Flink on Mac/ubuntu

Steps are:

1. Unix-like environment such as Linux, Mac OS X, Cygwin.

2. Git

3. Make sure you have java installed, check it in terminal using

    ```shell
    java -version
    ```

4. Maven is used as build tool, if you do not have maven install it using

    ```shell
    brew install maven
    ```

Unix-like environment (We use Linux, Mac OS X, Cygwin) is required

5. Go to [this link](https://flink.apache.org/downloads.html) and download the source version. You can also clone the source form `git` by entering following command in your terminal.
    ```sh
    git clone https://github.com/apache/flink
    ```

6. `cd` to the downloaded file and then unpack it by using

    ```shell
    tar xzf *.tgz
    ```

where `*` is filename. Alternatively in Mac you can also double click the tar file and it will be un-tared and unzipped.

7. Then `cd` to `un-tarred` file and enter following command in terminal

    ```shell
    mvn clean install -DskipTests
    ```

Let build will start and will take  almost 30 minutes and finally if everything is done successfully, then you will see following message.

<center>![Installation Success](../images/1.png)</center>


1. In my system the Flink is installed at the following location.

```sh
/Users/YOUR_USER_NAME/.m2/repository/org/apache/flink
```

!!! success
    Congrats, we have successfully build Apache-Flink on our system.If you have some issues or suggestions, please feel free to comment  below.


