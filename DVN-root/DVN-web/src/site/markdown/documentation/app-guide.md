## Application Configuration

### LDAP Configuration

* Add JVM Options on Glassfish admin interface or domain.xml

```shell
<!-- replace variable values with yours -->
<jvm-options>-Ddvn.ldap.provider.url=${ldap.parovider.url}</jvm-options>
<jvm-options>-Ddvn.ldap.user.pattern=${user.pattern}</jvm-options>
<jvm-options>-Ddvn.ldap.user.base=${user.base}</jvm-options>
```

* Build, deploy and restart Glassfish server.
	
	