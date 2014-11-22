package com.jpw.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import com.jpw.app.MemoryPersistenceService;

@Path( "admin/events" )
public class WSAdminEvents
{
	@SuppressWarnings( "unused" )
	@Context
	private UriInfo context;

	/**
	 * Default constructor.
	 */
	public WSAdminEvents( )
	{
	}

	@POST
	@Consumes( "application/x-www-form-urlencoded" )
	public void clearEvents( )
	{
		MemoryPersistenceService.clearEvents( );
	}

}