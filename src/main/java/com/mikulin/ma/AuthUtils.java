package com.mikulin.ma;

import java.nio.charset.Charset;
import java.util.Base64;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;

/**
 * Utility class for all authentication, authorization actions.
 *
 */
public class AuthUtils {

	private static final int BASIC_LENGTH = "Basic".length();

	private AuthUtils() {
	}

	/**
	 * Returns credentials from the provided auth header.
	 * 
	 * @param authHeader
	 *            auth header
	 * @return credentials that looks like USER:PASSWORD value
	 */
	public static String getCrdentials(String authHeader) {
		String base64Credentials = authHeader.substring(BASIC_LENGTH).trim();
		return new String(Base64.getDecoder().decode(base64Credentials), Charset.forName("UTF-8"));
	}


	public static String getUsername(final ContainerRequestContext requestContext) {
		String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		return authHeader == null ? null : getUsername(authHeader);
	}

	/**
	 * Checks whether user is authenticated.
	 * 
	 * @param requestContext
	 *            request context
	 * @return boolean true if user is authenticated, false otherwise.
	 */
	public static boolean isAuthenticated(final ContainerRequestContext requestContext) {
		String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		if (authHeader == null) {
			return false;
		} else {
			// authorization header looks like Basicuser:password
			String credentials = AuthUtils.getCrdentials(authHeader);

			// Next simple logic is implemented: pass if user starts with "t"
			// and password is "1".
			return (credentials.startsWith("t") && credentials.endsWith(":1")) == true;
		}
	}
	
	/**
	 * Returns user name from the auth header.
	 * 
	 * @param authHeader
	 *            auth header
	 * @return user name value
	 */
	public static String getUsername(String authHeader) {
		String credentials = getCrdentials(authHeader);
		return (credentials == null ? null : credentials.substring(0,credentials.indexOf(":")));
	}

}
