
Mac provides `curl`, however sometime using `wget` is more handy. This post is for installing `wget` for Mac. by following the below mentined steps:


##  Method 1

In most of tutorials, installing `wget` using `curl` is recommended . However I ran into several issues using this approach. I will share it with you guys. First you need to install the `xcode command line tools`, easiest way to do so by running in the Terminal

```sh
xcode-select --install
```

1. Download `wget` using `curl`

```sh
curl -O http://ftp.gnu.org/gnu/wget/wget-1.15.tar.gz
```

2. Unpack it using

```sh
tar -zxvf wget-1.15.tar.gz
```

3. cd to folder using

```sh
cd wget-1.15/
```
4. Then configure

```sh
./configure
```


I have got following error that

!!! error
    `configure: error: --with-ssl=gnutls was given, but GNUTLS is not available.`

    we can skip this error using

    ```sh
    ./configure --with-ssl=openssl

    ```

    However, this also leads to another error shown below

    ```sh
    configure: error: --with-ssl=openssl was given, but SSL is not available
    ```

##  Method 2

1.  We  can easily go around aforementioned error as `brew` will automatically install dependencies for` wget` and `openssl`. Gor doing this you must have `brew` installed

```sh
brew install wget
```

as shown below, it will automatically download the dependency

```sh
==> Installing dependencies for wget: openssl
==> Installing wget dependency: openssl
==> Downloading https://homebrew.bintray.com/bottles/openssl-1.0.2j.sierra.bottle.tar.gz
######################################################################## 100.0%
==> Pouring openssl-1.0.2j.sierra.bottle.tar.gz
```


2.  For checking is `wget` is installed successfully, please `cd` to paricular folder and enter

```sh
wget -O sample.txt http://www.gutenberg.org/cache/epub/1787/pg1787.txt
```

text will be generated from link `http://www.gutenberg.org/cache/epub/1787/pg1787.txt` and saved as `sample.txt`


!!! success
    Congrats, we have successfully installed wget on mac.If you have some issues or suggestions, please feel free to comment  below.

