package com.jpw.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import java.io.*;

import org.glassfish.jersey.media.sse.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;


@Path( "notifications" )
public class WSUserNotifications
{
	@SuppressWarnings( "unused" )
	@Context
	private UriInfo context;
	private static final ObjectMapper objectMapper = new ObjectMapper( );
	private static final Logger logger = LoggerFactory.getLogger( WSEvents.class );

	/**
	 * Default constructor.
	 */
	public WSUserNotifications()
	{
	}

	@GET
	@Path( "/{user}" )
	@Produces( SseFeature.SERVER_SENT_EVENTS )
	public EventOutput getUserNotifications( @PathParam( "user" ) String userId )
	{
		logger.info( "getUserNotification called for user {}.", userId );
		final EventOutput eventOutput = new EventOutput( );
		
		/*
		- get the scheduled executer service
		- pass it a runable to run every 1 second
		- runnable must take the EventOutput as a member
		- handle the exception somehow and close writer
		*/
		
		new Thread( new Runnable( ) { 
			
			@Override
			public void run( )
			{ 
				try
				{
					for( int i = 0; i < 5; i++ )
					{
						final OutboundEvent.Builder notificationEventBuilder = new OutboundEvent.Builder( );
						notificationEventBuilder.name( "my-notification" );
						notificationEventBuilder.data( String.class, "Hello my " + i + " world!!!" );
						
						final OutboundEvent notificationEvent = notificationEventBuilder.build( );
						logger.info( "about to send {} notification event", notificationEvent );
						eventOutput.write( notificationEvent );
					}
				}
				catch( IOException e )
				{
					throw new RuntimeException( "Error writing notification event.", e );
				}
				catch( Exception e )
				{
					logger.info("Something went terribly wrong!!!", e );
				}
				finally
				{
					try
					{
						eventOutput.close( );
						logger.info( "closing event output" );
					}
					catch( IOException ioClose )
					{
						throw new RuntimeException( "Error closing notification event output", ioClose );
					}
				}
			} 
		}).start( );
		
		return eventOutput;
	}

}
