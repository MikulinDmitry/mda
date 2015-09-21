package com.mikulin.ma.security;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

/**
 * Simple authorizer to pass everything.
 */
public class Authorizer implements SecurityContext {

	public Principal getUserPrincipal() {
		return null;
	}

	@Override
	public boolean isSecure() {
		return false;
	}

	@Override
	public boolean isUserInRole(String role) {
		return true;
	}

	@Override
	public String getAuthenticationScheme() {
		return null;
	}

}