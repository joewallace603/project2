package com.jpw.ws;

import java.util.*;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jpw.interfaces.EventManager;
import com.jpw.manager.ManagerFactory;
import com.jpw.dto.*;
import com.jpw.client.*;

@Path("invitationstatus")
public class WSInvitations 
{
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;
    
    private static final ObjectMapper objectMapper = new ObjectMapper( );
    private static final Logger logger = LoggerFactory.getLogger( WSEvents.class );

    /**
     * Default constructor. 
     */
    public WSInvitations() 
    {
    }

    @PUT
	@Path( "/{userId}/{eventId}/{invitationResponse}" )
	public Response handleResposeToInvitation( @PathParam( "userId" ) String userId,
										       @PathParam( "eventId" ) String eventId,
			                                   @PathParam( "invitationResponse" ) String invitationResponse )
	{
		System.out.println( "PUT:respondToInvitation:  userId = " + userId + ", eventId = " + eventId + 
				", response = " + invitationResponse );
		
		EventManager eventManager = ManagerFactory.getEventManager( );
		boolean success = eventManager.handleResposeToInvitation( userId, eventId, invitationResponse );
		
		Response response = success ? Response.ok( ).build( ) : Response.status( 500 ).build( );
		
		return response;
	}
    
    /*
    
    @GET
    @Path( "/{userId}/{eventId}" )
    @Produces( "application/json" )
    public String getEventUserStatusesByEventId( @PathParam( "userId" ) String userId, @PathParam( "eventId" ) String eventId )
    {
    	System.out.println( "GET WSInvitationResponse.getEventUserStatusesByEventId:  userId = " + userId + ", evenId = " + eventId );
    	
    	String eventUserStatusesString = "";
    	
    	EventManager eventManager = ManagerFactory.getEventManager( );
		List<ClientEventInvitationList> clientEventInvitationLists = eventManager.getEventUserStatusesByEventId( userId, eventId );

		try
		{
			eventUserStatusesString = objectMapper.writeValueAsString( clientEventInvitationLists );
		}
		catch ( Exception e )
		{
			e.printStackTrace( );
		}

		System.out.println( "WSInvitationResponse.getEventUserStatusesByEventId returning:  " + eventUserStatusesString );

    	return eventUserStatusesString;
    }
    */
    
    
    @GET
    @Path( "/{eventId}/{userId}" )
    @Produces( "application/json" )
    public String getEventInvitationStatuses( @PathParam( "eventId" ) String eventId,
    										  @PathParam( "userId" ) String userId )
    {
    	logger.info( "IN getEventInvitationStatuses for event {} and user {}. ", eventId, userId );
    	
    	EventManager eventManager = ManagerFactory.getEventManager( );
    	
    	List<ClientEvent> clientEvents = eventManager.getEventInvitationStatuses( eventId, userId );
    	
    	String eventInvitationStatus = "";

    	try
    	{
    		eventInvitationStatus = objectMapper.writeValueAsString( clientEvents );
    	}
    	catch ( Exception e )
    	{
    		e.printStackTrace( );
    	}

    	logger.info( "getEventInvitationStatuses returning {}.  ", eventInvitationStatus );

    	return eventInvitationStatus;
    }
    
}
