/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.piwik.api.client;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author halfdan
 */
public class PiwikClient {
	
	/**
	 * Base URI to Piwik Installation. (URI-authority)
	 * 
	 * E.g. http://demo.piwik.org:8080
	 */
	protected final String piwikHost;	
	protected String userAgent;
	
	public PiwikClient(String piwikHost) {
		this.piwikHost = piwikHost;
		this.userAgent = "piwik-java-api/0.1";
	}
	
	/**
	 * Create full URI from path
	 *
	 * @param path
	 * @return URI
	 */
	protected String createUri(final String path) {
		return piwikHost + "/" + path;
	}
	
		/**
	 * Set the value to set as the user agent header on every request created.
	 * Specifying a null or empty agent parameter will reset this client to use
	 * the default user agent header value.
	 *
	 * @param agent
	 * @return this client
	 */
	public PiwikClient setUserAgent(final String agent) {
		userAgent = agent;
		return this;
	}
	
	/**
	 * Create connection to URI
	 *
	 * @param uri
	 * @return connection
	 * @throws IOException
	 */
	protected HttpURLConnection createConnection(String uri) throws IOException {
		URL url = new URL(createUri(uri));
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("GET");
		return connection;
	}
	
	public PiwikResponse executeRequest(PiwikRequest request) throws IOException {
		HttpURLConnection connection = createConnection(piwikHost);
		return new PiwikResponse();
	}
}
