package com.mikulin.ma.security;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mikulin.ma.AuthUtils;

@PreMatching
@Priority(Priorities.AUTHENTICATION)
public class AuthFilter implements ContainerRequestFilter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.ws.rs.container.ContainerRequestFilter#filter(javax.ws.rs.container
	 * .ContainerRequestContext)
	 */
	@Override
	public void filter(ContainerRequestContext request) throws IOException {
		request.setSecurityContext(new Authorizer());
		if (!AuthUtils.isAuthenticated(request)) {
			request.abortWith(Response.status(Status.UNAUTHORIZED)
					.header(HttpHeaders.WWW_AUTHENTICATE, "Basic realm=\"Simple auth\"").entity("Login required.")
					.build());
		}
	}

}