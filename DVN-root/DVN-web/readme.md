# DVN-web Modifications

* Modified login module to authenticate against university's LDAP server.
* Removed all create account link from all pages.
* Changed wordings, username to CCID.

## LDAP Authentication

* Added JVM Options on Glassfish configuration, domain.xml
..*
```
<jvm-options>-Ddvn.ldap.provider.url=ldaps://directory.srv.ualberta.ca:636</jvm-options>
<jvm-options>-Ddvn.ldap.user.pattern=uid={0},ou=people,dc=ualberta,dc=ca</jvm-options>
<jvm-options>-Ddvn.ldap.user.base=ou=people,dc=ualberta,dc=ca</jvm-options>
```
	