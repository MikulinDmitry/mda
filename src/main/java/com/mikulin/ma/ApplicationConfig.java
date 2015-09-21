package com.mikulin.ma;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import com.mikulin.ma.security.AuthFilter;

/**
 * Is used to declare filters, resources,other dependencies.
 * 
 */
public class ApplicationConfig extends ResourceConfig {

	public ApplicationConfig() {
		register(AuthFilter.class);
		register(RolesAllowedDynamicFeature.class);
		packages(true, "com.mikulin.ma");
	}
}