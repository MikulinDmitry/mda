package com.mikulin.ma.rest;

import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletContext;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mikulin.ma.AuthUtils;
import com.mikulin.ma.HTMLTransformer;
import com.mikulin.ma.db.MongoDBManager;

/**
 * Service to transform input line according to the existed rules.
 *
 */
@Path("/transform")
public class MarkdownAssignmentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MarkdownAssignmentService.class);

	@Context
	private ServletContext context;


	@Autowired
	private MongoDBManager mongoManager;

	@RolesAllowed("admin")
	@POST
	@Produces(MediaType.TEXT_HTML)
	public Response doConvert(@FormParam("initial") String initialInput, @Context ContainerRequestContext request) {
		LOGGER.debug("input {}", initialInput);
		String result = transform(initialInput);
		mongoManager.addLogRecord(AuthUtils.getUsername(request), initialInput, result);
		LOGGER.debug("user {}, input {}, result {}", AuthUtils.getUsername(request), initialInput, result);
		return Response.ok().entity(result).build();
	}

	protected String transform(String input) {
		return HTMLTransformer.transform(input);
	}

}
