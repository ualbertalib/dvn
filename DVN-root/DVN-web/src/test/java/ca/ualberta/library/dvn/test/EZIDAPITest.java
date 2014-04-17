/**
 * Piyapong Charoenwattana
 * Project: DVN-web
 * $Id$
 */

package ca.ualberta.library.dvn.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import junit.framework.TestCase;

/**
 * The EZIDAPITest class.
 * 
 * @author <a href="mailto:piyapong.charoenwattana@gmail.com">Piyapong Charoenwattana</a>
 * @version $Revision$ $Date$
 */
public class EZIDAPITest extends TestCase {
	static String SERVER = "https://ezid.cdlib.org";
	static String USERNAME = "apitest";
	static String PASSWORD = "apitest";

	static class MyAuthenticator extends Authenticator {
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(USERNAME, PASSWORD.toCharArray());
		}
	}

	static class Response {

		int responseCode;
		String status;
		String statusLineRemainder;
		HashMap<String, String> metadata;

		@Override
		public String toString() {
			StringBuffer b = new StringBuffer();
			b.append("responseCode=");
			b.append(responseCode);
			b.append("\nstatus=");
			b.append(status);
			b.append("\nstatusLineRemainder=");
			b.append(statusLineRemainder);
			b.append("\nmetadata");
			if (metadata != null) {
				b.append(" follows\n");
				Iterator<Map.Entry<String, String>> i = metadata.entrySet().iterator();
				while (i.hasNext()) {
					Map.Entry<String, String> e = i.next();
					b.append(e.getKey() + ": " + e.getValue() + "\n");
				}
			} else {
				b.append("=null\n");
			}
			return b.toString();
		}

	}

	static String encode(String s) {
		return s.replace("%", "%25").replace("\n", "%0A").replace("\r", "%0D").replace(":", "%3A");
	}

	static String toAnvl(HashMap<String, String> metadata) {
		Iterator<Map.Entry<String, String>> i = metadata.entrySet().iterator();
		StringBuffer b = new StringBuffer();
		while (i.hasNext()) {
			Map.Entry<String, String> e = i.next();
			b.append(encode(e.getKey()) + ": " + encode(e.getValue()) + "\n");
		}
		return b.toString();
	}

	static String decode(String s) {
		StringBuffer b = new StringBuffer();
		int i;
		while ((i = s.indexOf("%")) >= 0) {
			b.append(s.substring(0, i));
			b.append((char) Integer.parseInt(s.substring(i + 1, i + 3), 16));
			s = s.substring(i + 3);
		}
		b.append(s);
		return b.toString();
	}

	static String[] parseAnvlLine(String line) {
		String[] kv = line.split(":", 2);
		kv[0] = decode(kv[0]).trim();
		kv[1] = decode(kv[1]).trim();
		return kv;
	}

	static Response issueRequest(String method, String path, HashMap<String, String> metadata) throws Exception {
		HttpURLConnection c = (HttpURLConnection) (new URL(SERVER + "/" + path)).openConnection();
		c.setRequestMethod(method);
		c.setRequestProperty("Accept", "text/plain");
		if (metadata != null) {
			c.setDoOutput(true);
			c.setRequestProperty("Content-Type", "text/plain; charset=UTF-8");
			OutputStreamWriter w = new OutputStreamWriter(c.getOutputStream(), "UTF-8");
			w.write(toAnvl(metadata));
			w.flush();
		}
		Response r = new Response();
		r.responseCode = c.getResponseCode();
		InputStream is = r.responseCode < 400 ? c.getInputStream() : c.getErrorStream();
		if (is != null) {
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String[] kv = parseAnvlLine(br.readLine());
			r.status = kv[0];
			r.statusLineRemainder = kv[1];
			HashMap<String, String> d = new HashMap<String, String>();
			String l;
			while ((l = br.readLine()) != null) {
				kv = parseAnvlLine(l);
				d.put(kv[0], kv[1]);
			}
			if (d.size() > 0)
				r.metadata = d;
		}
		return r;
	}

	public static void testArk() {

		try {

			Authenticator.setDefault(new MyAuthenticator());

			// Sample POST request.
			System.out.println("Issuing POST [ark] request...");
			HashMap<String, String> metadata = new HashMap<String, String>();
			metadata.put("erc.who", "Piyapong Charoenwattana");
			metadata.put("erc.what", "Test DOI");
			metadata.put("erc.when", "2014");
			// metadata.put("_target", "https://dataverse.library.ualberta.ca/");
			Response r = issueRequest("POST", "shoulder/ark:/99999/fk4", metadata);
			System.out.print(r);

			// Sample GET request.
			System.out.println("\nIssuing GET [ark] request...");
			String id = r.statusLineRemainder;
			r = issueRequest("GET", "id/" + URLEncoder.encode(id, "UTF-8"), null);
			System.out.print(r);

			assertTrue(true);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	public static void testDoi() {

		try {

			Authenticator.setDefault(new MyAuthenticator());

			// Sample POST request.
			System.out.println("Issuing POST [doi] request...");
			HashMap<String, String> metadata = new HashMap<String, String>();
			metadata = new HashMap<String, String>();
			metadata.put("datacite.creator", "Piyapong Charoenwattana");
			metadata.put("datacite.title", "Test DOI");
			metadata.put("datacite.publisher", "Publisher");
			metadata.put("datacite.publicationyear", "2014");
			metadata.put("datacite.resourcetype", "Text");
			// metadata.put("_target", "https://dataverse.library.ualberta.ca/");
			// metadata.put("_status", "public");

			Response r = issueRequest("POST", "shoulder/doi:10.5072/FK2", metadata);
			System.out.print(r);

			System.out.println("\nIssuing GET [doi] request...");
			String id = r.statusLineRemainder;
			r = issueRequest("GET", "id/" + URLEncoder.encode(id, "UTF-8"), null);
			System.out.print(r);
			assertTrue(true);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
}
