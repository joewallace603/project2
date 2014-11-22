package com.jpw.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.jpw.dto.*;
import com.jpw.interfaces.*;
import com.jpw.manager.ManagerFactory;
import com.jpw.client.*;

@Path("friends")
public class WSFriends
{
	@SuppressWarnings("unused")
	@Context
	private UriInfo context;
	private static final ObjectMapper objectMapper = new ObjectMapper( );

	/**
	 * Default constructor.
	 */
	public WSFriends()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * Retrieves representation of an instance of WSFriends
	 * 
	 * @return an instance of String
	 */
	@GET
	@Path ( "/{userId}")
	@Produces("application/json")
	public String getUsers( @PathParam( "userId" ) String userId )
	{
		System.out.println( "WSFriends.getUserFriends called for User = " + userId );
		// For now return all Users.
		
		String clientContactsString = "";
		
		FriendManager friendManager = ManagerFactory.getFriendManager( );
		List<ClientContact> clientContacts = friendManager.getUsers( userId );

		try
		{
			clientContactsString = objectMapper.writeValueAsString( clientContacts );
		}
		catch ( Exception e )
		{
			e.printStackTrace( );
		}
		
		System.out.println( "WSFriends.getUsers returning:  " + clientContactsString );
		return clientContactsString;
	}

	/**
	 * PUT method for updating or creating an instance of WSFriends
	 * 
	 * @param content
	 *            representation for the resource
	 * @return an HTTP response with content of the updated or created resource.
	 */
	@PUT
	@Consumes("application/json")
	public void putXml( String content )
	{
	}

}