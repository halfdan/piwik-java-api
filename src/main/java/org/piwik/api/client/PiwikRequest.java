package org.piwik.api.client;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 *
 */
public class PiwikRequest {

	private String basePath;
	private Map<String, String> params;
	private Type type;
	private String responseContentType;

	/**
	 * Create empty request
	 */
	public PiwikRequest() {
		this.basePath = "index.php";
		this.responseContentType = "application/json";
	}

	/**
	 * Create parameter string from params.
	 * 
	 * @return String
	 */
	protected String getParameterString() {
		final StringBuilder uri = new StringBuilder();
		for (Entry<String, String> param : this.params.entrySet()) {
			String name = encode(param.getKey());
			String value = encode(param.getValue());
			if (uri.length() > 0) {
				uri.append('&');
			}
			uri.append(name).append('=');
			if (value != null) {
				uri.append(value);
			}
		}
		return uri.toString();
	}

	/**
	 * Generate URI path + query.
	 *
	 * @return uri
	 */
	public String generateUri() {
		String urlParams = getParameterString();
		if (urlParams.length() > 0) {
			return basePath + '?' + urlParams;
		} else {
			return basePath;
		}
	}


	/**
	 * Set the URI-path for the request
	 * 
	 * @param path
	 * @return this request
	 */
	public PiwikRequest setPath(String path) {
		this.basePath = path;
		return this;
	}

	/**
	 * Get parameters for the PiwikRequest
	 * 
	 * @return params
	 */
	public Map<String, String> getParams() {
		return params;
	}

	/**
	 * Set parameters for the PiwikRequest.
	 * 
	 * @param params
	 * @return this request
	 */
	public PiwikRequest setParams(Map<String, String> params) {
		this.params = params;
		return this;
	}

	/**
	 * @return type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @param type
	 * @return this request
	 */
	public PiwikRequest setType(Type type) {
		this.type = type;
		return this;
	}

	/**
	 * @return responseContentType
	 */
	public String getResponseContentType() {
		return responseContentType;
	}

	/**
	 * Set the desired response content type
	 *
	 * @param responseContentType
	 * @return this request
	 */
	public PiwikRequest setResponseContentType(String responseContentType) {
		this.responseContentType = responseContentType;
		return this;
	}
	
	/**
	 * URL-encode value using 'UTF-8' character set
	 *
	 * @param value
	 * @return encoded value
	 */
	private static String encode(final String value) {
		try {
			return URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public int hashCode() {
		final String fullUri = generateUri();
		return fullUri != null ? fullUri.hashCode() : super.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof PiwikRequest)) {
			return false;
		}
		final String fullUri = generateUri();
		final String objUri = ((PiwikRequest) obj).generateUri();
		return fullUri != null && objUri != null && fullUri.equals(objUri);
	}

	@Override
	public String toString() {
		final String fullUri = generateUri();
		return fullUri != null ? fullUri : super.toString();
	}
}
