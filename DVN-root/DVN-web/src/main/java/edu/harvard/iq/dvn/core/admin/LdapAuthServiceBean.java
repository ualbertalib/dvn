/**
 * University of Alberta Libraries
 * Information Technology Services
 */
package edu.harvard.iq.dvn.core.admin;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.Hashtable;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchResult;

/**
 * The LdapAuthServiceBean class.
 * 
 * @author <a href="mailto:piyapong.charoenwattana@ualberta.ca">Piyapong Charoenwattana</a>
 * @version $Revision: 1181 $ $Date: 2012-07-11 15:46:27 -0600 (Wed, 11 Jul 2012) $
 */
@Stateless
public class LdapAuthServiceBean implements LdapAuthServiceLocal {

	private static final Logger logger = Logger.getLogger(LdapAuthServiceBean.class.getPackage().getName());
	private static final long serialVersionUID = 832857325410696207L;
	private static final String providerUrl = System.getProperty("dvn.ldap.provider.url");
	private static final String userPattern = System.getProperty("dvn.ldap.user.pattern");
	private static final String userBase = System.getProperty("dvn.ldap.user.base");

	public LdapAuthServiceBean() {
	}

	@Override
	public boolean authenticate(String uid, String pwd) {
		try {
			Hashtable<String, String> env = getEnv();
			env.put(Context.SECURITY_PRINCIPAL, MessageFormat.format(userPattern, uid));
			env.put(Context.SECURITY_CREDENTIALS, pwd);
			DirContext ctx = new InitialDirContext(env);
			ctx.close();
			return true;
		} catch (Exception e) {
			StringWriter ex = new StringWriter();
			e.printStackTrace(new PrintWriter(ex));
			if (!e.getMessage().contains("error code 49")) {
				logger.severe("Could not authenticate! (" + e.getMessage() + ")");
				logger.severe(ex.toString());
			}
			return false;
		}
	}

	@Override
	public Attributes getAttributes(String uid) {
		try {
			Hashtable<String, String> env = getEnv();
			SearchResult result = searchByUid(env, uid);
			return result.getAttributes();
		} catch (Exception e) {
			StringWriter ex = new StringWriter();
			e.printStackTrace(new PrintWriter(ex));
			logger.severe("Could not get user profile! (" + e.getMessage() + ")");
			logger.severe(ex.toString());
			return null;
		}
	}

	private Hashtable<String, String> getEnv() {
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put("com.sun.jndi.ldap.connect.pool", "true");
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, providerUrl);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		return env;
	}

	private SearchResult searchByUid(Hashtable<String, String> env, String uid) {
		try {
			DirContext ctx = new InitialDirContext(env);
			Attributes matchAttrs = new BasicAttributes(true); // ignore case
			matchAttrs.put(new BasicAttribute("uid", uid));
			NamingEnumeration<SearchResult> results = ctx.search(userBase, matchAttrs);
			ctx.close();
			return results.hasMore() ? results.next() : null;
		} catch (Exception e) {
			StringWriter ex = new StringWriter();
			e.printStackTrace(new PrintWriter(ex));
			logger.severe("Could not find user id! (" + e.getMessage() + ")");
			logger.severe(ex.toString());
			return null;
		}
	}
}
