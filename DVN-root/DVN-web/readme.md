# DVN-web Modifications

* Modified login module to authenticate against university's LDAP server.
* Removed all create account link from all pages.
* Changed wordings, username to CCID.
* Removed change password link from user account page.
* Allowed only networkAdmin to be able to change password.
* Disabled username, firstname, lastname and email address input fields on EditAccountPage.

## LDAP Authentication

* Add JVM Options on Glassfish configuration, domain.xml
```
<!-- replace variable values with yours -->
<jvm-options>-Ddvn.ldap.provider.url=${ldap.parovider.url}</jvm-options>
<jvm-options>-Ddvn.ldap.user.pattern=${user.pattern}</jvm-options>
<jvm-options>-Ddvn.ldap.user.base=${user.base}</jvm-options>
```
* Build, deploy and restart Glassfish server.
	
	