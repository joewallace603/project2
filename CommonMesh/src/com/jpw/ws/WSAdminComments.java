package com.jpw.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import com.jpw.app.MemoryPersistenceService;

@Path( "admin/comments" )
public class WSAdminComments
{
	@SuppressWarnings( "unused" )
	@Context
	private UriInfo context;

	/**
	 * Default constructor.
	 */
	public WSAdminComments( )
	{
		// TODO Auto-generated constructor stub
	}

	@POST
	@Consumes( "application/x-www-form-urlencoded" )
	public void clearComments( )
	{
		MemoryPersistenceService.clearComments( );
	}

}