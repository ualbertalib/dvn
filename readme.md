![alt text](http://www.toolkit.ualberta.ca/Toolkit%20Downloads/~/media/identity/Toolkit/Logos/UA/UA-COLOUR-180px.png "University of Alberta")

# Dataverse Network (DVN)

* The Dataverse Network Project is an open source web application for sharing, citing, analyzing, and preserving research data.
* To learn more, please visit <http://thedata.org> 
* Development takes place on the "develop" branch: <https://github.com/IQSS/dvn/tree/develop> 

Fork from <https://github.com/IQSS/dvn> on 2014.02.23 for Digital Initiative Dataverse Network, University of Alberta Libraries

## Quick Start Guide for Eclipse User

* Download Eclipse Kepler (4.3.1) for Java EE Developers <http://eclipse.org/downloads/>
* Clone project, ```Window -> Open Perspective -> Git -> Copy and paste <https://github.com/ualbertalib/dvn.git>``` to Git Repositories tab 
* Import project, ```File -> Import -> Existing Maven Project -> Select dvn/DVN-root```
* You should get projects ```DVN-root and DVN-web``` in your workspace.
* Build project, ```DVN-root -> Run As -> 1. Maven build -> Goles: package -> Run```


## Build from Source Code

### System Requirements

* Java 6 <http://www.oracle.com/technetwork/java/javase/overview/index.html>
* Apache Maven 3 <http://maven.apache.org/>
* Git <http://git-scm.com/>

### Run commands

```shell
$ git clone https://github.com/ualbertalib/dvn.git
$ cd dvn/DVN-root
$ mvn package
```


