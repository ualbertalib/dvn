## Build Application

### System Requirements

* Java 6 <http://www.oracle.com/technetwork/java/javase/overview/index.html>
* Apache Maven 3 <http://maven.apache.org/>
* Git <http://git-scm.com/>

### Usage

#### Build application war file

```shell
$ git clone https://github.com/ualbertalib/dvn.git
$ cd dvn/DVN-root
$ mvn package
$ cp DVN-web/target/DVN-web.war ${autodeploy.dir}
```

#### Build project documents

```shell
$ git clone https://github.com/ualbertalib/dvn.git -b gh-pages dvn-gh-pages
$ cd dvn/DVN-root/DVN-web
$ mvn javadoc:javadoc site -Dsite.output.dir=${dvn-gh-pages.dir}
$ cd ${dvn-gh-pages.dir}
$ git add .
$ git commit -m "Update project documents."
$ git push
```
