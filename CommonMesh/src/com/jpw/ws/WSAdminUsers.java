package com.jpw.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

import com.jpw.app.MemoryPersistenceService;
import com.jpw.dto.User;
import com.jpw.interfaces.*;
import com.jpw.manager.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Path( "admin/users" )
public class WSAdminUsers
{
	@SuppressWarnings( "unused" )
	@Context
	private UriInfo context;
	private static final ObjectMapper objectMapper = new ObjectMapper( );
	private static final Logger logger = LoggerFactory.getLogger( WSAdminUsers.class );

	public WSAdminUsers( )
	{
	}
	
	@GET
	@Path( "/{userId}" )
	public Response verifyUser( @PathParam( "userId" ) String userId )
	{
		logger.info( "In verifyUser for user {}.", userId );
		Response response = Response.ok( ).build( );
		return response;
	}

	/**
	 * Retrieves representation of an instance of WSAdminUsers
	 * 
	 * @return an instance of String
	 */
	@GET
	@Produces( "application/json" )
	public String getAllUserIds( )
	{
		Set<String> allUserIds = MemoryPersistenceService.getAllUserIds( );
		String returnString = "";

		try
		{
			returnString = objectMapper.writeValueAsString( allUserIds );
		}
		catch ( Exception e )
		{
			System.out.println( "Exception during getAllUserIds:  " );
			e.printStackTrace( );
		}

		return returnString;
	}

	/*
	 * @POST
	 * 
	 * @Consumes("application/x-www-form-urlencoded") public void createUser(
	 * @FormParam("username") String username, @FormParam( "password") String
	 * password,
	 * 
	 * @FormParam("firstName") String firstName, @FormParam("lastName") String
	 * lastName,
	 * 
	 * @FormParam("gender") String gender, @FormParam("age") String age,
	 * @FormParam("zip") String zip ) {
	 * System.out.println("POST - createUser:  " + username + ", " + password +
	 * ", " + firstName + ", " + lastName + ", " + gender + ", " + age + ", " +
	 * zip + " called at " + System.currentTimeMillis());
	 * 
	 * int userId = RandomIdGenerator.getNewId();
	 * 
	 * User user = new User( );
	 * 
	 * user.setUsername(username); user.setUserPassword(password);
	 * user.setFirstName(firstName); user.setLastName(lastName);
	 * user.setGender(gender); user.setAge(Integer.decode( age ));
	 * user.setZipcode(zip); }
	 */
	@POST
	@Consumes( "application/json" )
	public Response createUser( User user )
	{
		System.out.println( "POST:  create user called " );
		System.out.println( "newUser:  " + user );

		UserManager userManager = ManagerFactory.getUserManager( );
		
		Response response;

		boolean success = userManager.createUser( user );
		
		if( success )
		{
			System.out.println( "WSAdminUsers:  User " + user.getUserId( ) + " created successfully.");
			response = Response.ok( ).build( );
		}
		else
		{
			response = Response.status( 500 ).build( );
			System.err.println( "WSAdminUsers:  Failed to create user " + user.getUserId( ) );
		}
		
		return response;
		/*
		int statusCode = 201;

		if ( !success )
		{
			Response reResponse.ok( ).build();
			statusCode = 424; // Abort due to method failure
			System.err.println( "WSAdminUser.createUser failed." );
		}
		else System.out.println( "WSAdminUser.createUser succeeded." );

		return Response.status( statusCode ).build( );
		*/
	}

}