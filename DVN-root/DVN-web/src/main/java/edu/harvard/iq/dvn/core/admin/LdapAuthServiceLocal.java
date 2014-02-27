/**
 * University of Alberta Libraries
 * Information Technology Services
 */
package edu.harvard.iq.dvn.core.admin;

import javax.ejb.Local;
import javax.naming.directory.Attributes;

/**
 * The LdapAuthServiceLocal class.
 * 
 * @author <a href="mailto:piyapong.charoenwattana@ualberta.ca">Piyapong Charoenwattana</a>
 * @version $Revision: 1181 $ $Date: 2012-07-11 15:46:27 -0600 (Wed, 11 Jul 2012) $
 */
@Local
public interface LdapAuthServiceLocal extends java.io.Serializable {

	public boolean authenticate(String uid, String pwd);

	public Attributes getAttributes(String uid);

}
