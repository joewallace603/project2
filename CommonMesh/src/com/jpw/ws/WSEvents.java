package com.jpw.ws;

import com.jpw.app.*;
import com.jpw.manager.*;
import com.jpw.interfaces.*;
import com.jpw.client.ClientEvent;
import com.jpw.client.ClientEventForm;
import com.jpw.dto.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.sql.*;
import java.text.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.FormParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.jpw.app.EventTemp;
import com.jpw.dto.*;

@Path( "events" )
public class WSEvents
{
	@SuppressWarnings( "unused" )
	@Context
	private UriInfo context;
	private static final ObjectMapper objectMapper = new ObjectMapper( );
	private static final Logger logger = LoggerFactory.getLogger( WSEvents.class );

	/**
	 * Default constructor.
	 */
	public WSEvents()
	{
	}

	@GET
	@Path( "/{user}/{startMillis}/{endMillis}" )
	@Produces( "application/json" )
	public String getUserEventsOverTimePeriod( @PathParam( "user" ) String userId,
											   @PathParam( "startMillis" ) String startMillis,
											   @PathParam( "endMillis" ) String endMillis )
	{
		System.out.println( "getUserEventsOverTimePeriod:  user = " + userId +
				            ", startMillis = " + startMillis + ", endMillis = " + endMillis );
		
		String eventsString = "";
		
		try
		{
			Timestamp startTime = new Timestamp( Long.valueOf( startMillis ) );
			Timestamp endTime = new Timestamp( Long.valueOf( endMillis ) );
			
			logger.info( "IN getUserEventsOverTimePeriod {} from {} to {}.", 
					userId, DateFormat.getInstance( ).format( startTime ), DateFormat.getInstance( ).format( endTime ) );
			
			EventManager eventManager = ManagerFactory.getEventManager( );
			List<ClientEvent> userEvents = eventManager.getUserEventsOverTimePeriod( userId, startTime, endTime );
			
			
//			System.out.println( "WSEvents.getUserEventsOverTimePeriod returning " + userEvents.size( ) + " events." );
			logger.info( "getUserEventsOverTimePeriod found {} events.", userEvents.size( ) );

			try
			{
				eventsString = objectMapper.writeValueAsString( userEvents );
			}
			catch ( Exception e )
			{
				logger.error( "getUserEventsOverTimePeriod failed to convert event string in JSON for user {} from {} to {}.", 
						userId, DateFormat.getInstance( ).format( startTime ), DateFormat.getInstance( ).format( endTime ) );
				e.printStackTrace( );
			}
		}
		catch( NumberFormatException  e )
		{
//			System.err.println("Strange one of the start/stop times is null");
			logger.error( "IN getUserEventsOverTimePeriod  {} from {} to {}.  NumberFormatException ", userId, startMillis, endMillis );
			e.printStackTrace( );
		}
		
		System.out.println( "WSEvent.getUserEventsOverTimePeriod returning:  " + eventsString );
		logger.info( "OUT getUserEventsOverTimePeriod" );
		logger.debug( "OUT getUserEventsOverTimePeroid returning {}  ", eventsString );
		
		return eventsString;
	}

	
	@GET
	@Path( "/{eventId}/{userId}" )
	@Produces( "application/json" )
	public String getEventById( @PathParam( "eventId" ) String eventId, @PathParam( "userId" ) String userId )
	{
		System.out.println( "!!!!!!!!!!WSEvent.getEventById:  userId = " + userId + ", eventId = " + eventId );
		logger.info( "In getEventById {} for user {}", eventId, userId );
		
		String eventString = "";

		EventManager eventManager = ManagerFactory.getEventManager( );
		List<ClientEvent> clientEvents = eventManager.getEventById( eventId, userId );

		try
		{
			eventString = objectMapper.writeValueAsString( clientEvents );
		}
		catch ( Exception e )
		{
			e.printStackTrace( );
		}

		System.out.println( "WSEvent.getEventById returning:  " + eventString );
		logger.info( "Out getEventById returning {}", eventString );

		return eventString;
	}
	
	@GET
	@Path( "previous/{eventId}/{userId}")
	@Produces( "application/json" )
	public String getPreviousEventById( @PathParam( "eventId" ) String eventId, @PathParam( "userId" ) String userId )
	{
		System.out.println( "WSEvent.getPreviousEventById:  userId = " + userId + ", eventId = " + eventId );
		
		String eventString = "";

		EventManager eventManager = ManagerFactory.getEventManager( );
		List<ClientEvent> clientEvents = eventManager.getPreviousEventById( eventId, userId );

		try
		{
			eventString = objectMapper.writeValueAsString( clientEvents );
		}
		catch ( Exception e )
		{
			e.printStackTrace( );
		}

		System.out.println( "WSEvent.getEventById returning:  " + eventString );

		return eventString;
	}
	
	@GET
	@Path( "next/{eventId}/{userId}")
	@Produces( "application/json" )
	public String getNextEventById( @PathParam( "eventId" ) String eventId, @PathParam( "userId" ) String userId )
	{
		System.out.println( "WSEvent.getNextEventById:  userId = " + userId + ", eventId = " + eventId );
		
		String eventString = "";

		EventManager eventManager = ManagerFactory.getEventManager( );
		List<ClientEvent> clientEvents = eventManager.getNextEventById( eventId, userId );

		try
		{
			eventString = objectMapper.writeValueAsString( clientEvents );
		}
		catch ( Exception e )
		{
			e.printStackTrace( );
		}

		System.out.println( "WSEvent.getEventById returning:  " + eventString );

		return eventString;
	}
	
	/*
	@GET
	@Path( "invitations/{eventId}" )
	@Produces( "application/json" )
	public String getEventInvitationList( @PathParam( "eventId" ) String eventId )
	{
		String invitationList = "";
		
		return invitationList;
	}
	*/
	
	@PUT
	@Consumes( "application/json" )
	public Response updateEvent( ClientEventForm clientEventForm )
	{
		logger.info( "IN updateEvent clientEventForm:  {}", clientEventForm );
		System.out.println( "PUT:  updateEvent:  " + clientEventForm );
		
		EventManager eventManager = ManagerFactory.getEventManager( );
		
		Response response = Response.ok( ).build( );
		
		try
		{
			boolean success = eventManager.updateEvent( clientEventForm );
		}
		catch( IllegalArgumentException e )
		{
			// put message in response
			response = Response.status( Status.BAD_REQUEST ).entity( e.getMessage( ) ).build( );
		}
		
		return response;
	}

	/**
	 * PUT method for updating or creating an instance of GetEventsWeek
	 * 
	 * @param content
	 *            representation for the resource
	 * @return an HTTP response with content of the updated or created resource.
	 */
	@POST
	@Consumes( "application/json" )
	public Response createEvent( ClientEventForm clientEventForm )
	{
		// TODO add proper error handling by checking client supplied parameters in event form.
		
		System.out.println( "POST:  createEvent:  " + clientEventForm );

		EventManager eventManager = ManagerFactory.getEventManager( );

		boolean success = eventManager.createEvent( clientEventForm );

		int statusCode = 201;

		if( !success )
		{
			statusCode = 424;
		}

		return Response.status( statusCode ).build( );
	}

}