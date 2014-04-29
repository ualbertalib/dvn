## Application Configuration

### LDAP Configuration

* Add JVM Options on Glassfish admin interface or domain.xml

```shell
<jvm-options>-Ddvn.ldap.provider.url=${ldap.parovider.url}</jvm-options>
<jvm-options>-Ddvn.ldap.user.pattern=${user.pattern}</jvm-options>
<jvm-options>-Ddvn.ldap.user.base=${user.base}</jvm-options>
```

### DOI Configuration

```
<!-- -Ddoi.baseurlstring is missing from the document -->
<jvm-options>-Ddoi.baseurlstring=https://ezid.cdlib.org/</jvm-options>
<jvm-options>-Ddoi.username=${ezid.username}</jvm-options>
<jvm-options>-Ddoi.password=${ezid.password}</jvm-options>

``` 

* Build, deploy and restart Glassfish server.
	
	