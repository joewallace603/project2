package com.jpw.ws;

import java.util.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.jpw.app.MemoryPersistenceService;
import com.jpw.client.ClientEventComment;
import com.jpw.dto.EventComment;
import com.jpw.interfaces.EventManager;
import com.jpw.manager.ManagerFactory;

@Path( "comments" )
public class WSComments
{
	@SuppressWarnings( "unused" )
	@Context
	private UriInfo context;
	
	private static final ObjectMapper objectMapper = new ObjectMapper( );

	/**
	 * Default constructor.
	 */
	public WSComments( )
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * Retrieves representation of an instance of GetComments
	 * 
	 * @return an instance of String
	 */
	@GET
	@Path( "/{eventId}" )
	@Produces( "application/json" )
	public String getEventComments( @PathParam( "eventId" ) String eventId )
	{
		System.out.println( "WSComments.GET - comments called with eventId = " + eventId );
		
		String eventCommentsString = "";
		
		EventManager eventManager = ManagerFactory.getEventManager( );
		
		List<ClientEventComment> clientEventComments = eventManager.getEventComments( eventId );
		
		try
		{
			eventCommentsString = objectMapper.writeValueAsString( clientEventComments );
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
		
		return eventCommentsString;
	
		/*
		 * 
		 * System.out.println("Called getComments at " +
		 * System.currentTimeMillis()); String commentsString =""; ObjectMapper
		 * mapper = new ObjectMapper(); List<Comment> comments = new
		 * ArrayList<Comment>();
		 * 
		 * Comment a = new Comment(); a.setComment("Hey who's gonna drive up?");
		 * a.setCommentor("Joe"); a.setDate(new GregorianCalendar(2013, 12, 7,
		 * 7, 30).getTime()); a.setEventId(22);
		 * 
		 * comments.add(a);
		 * 
		 * Comment b = new Comment();
		 * b.setComment("I can.  I'll be going up at noon");
		 * b.setCommentor("Paul"); b.setDate(new GregorianCalendar(2013, 12, 7,
		 * 7, 34).getTime()); b.setEventId(22);
		 * 
		 * comments.add(b);
		 * 
		 * Comment c = new Comment(); c.setComment("Can I catch a ride too?");
		 * c.setCommentor("Fabio"); c.setDate(new GregorianCalendar(2013, 12, 7,
		 * 7, 41).getTime()); c.setEventId(22);
		 * 
		 * Comment d = new Comment(); d.setComment(
		 * "Now, a seven-year investigation by the Kentucky State Police has resulted in charges against the two police officers, both of whom have since left the force, and a third man. One of the former officers was a detective who appeared at the crime scene with other officers to investigate the killings, said one former officer who was first on the scene"
		 * ); d.setCommentor( "Jim"); d.setDate( new Date()); d.setEventId(22);
		 * 
		 * comments.add(d); comments.add(c); comments.add(c); comments.add(c);
		 * comments.add(c); comments.add(c);
		 */
/*
		List<EventComment> eventComments = MemoryPersistenceService.getComments( Integer
				.decode( eventId ) );
		System.out.println( "WSComments.getComments() returning "
				+ eventComments.size() + " comments." );
		ObjectMapper mapper = new ObjectMapper( );
		String commentsString = "";

		try
		{
			commentsString = mapper.writeValueAsString( eventComments );
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}

		return commentsString;
	*/
	}

	@POST
	@Consumes( "application/json" )
	public Response addCommentToEvent( ClientEventComment clientEventComment )
	{
		System.out.println( "POST - comment:  " + clientEventComment
				+ System.currentTimeMillis( ) );
		
		EventManager eventManager = ManagerFactory.getEventManager( );

		boolean success = eventManager.addCommentToEvent( clientEventComment );

		int statusCode = 201;

		if( !success )
		{
			statusCode = 424;
		}

		return Response.status( statusCode ).build( );
		
	//	comment.setDate( new Date( ) );

		//MemoryPersistenceService.addComment( Integer.decode( eventId ), eventComment );
	}
	
	/*(
	@POST
	@Consumes( "application/x-www-form-urlencoded" )
	public void addCommentToEvent( @PathParam( "eventid" ) String eventId,
			@FormParam( "comment" ) String commentString )
	{
		System.out.println( "POST - comment:  " + commentString
				+ " for eventId = " + eventId + " called at "
				+ System.currentTimeMillis( ) );

		Comment comment = new Comment( );
		comment.setComment( commentString );
		comment.setCommentor( "Joe" );
		comment.setDate( new Date( ) );

		MemoryPersistenceService.addComment( Integer.decode( eventId ), comment );
	}
	*/

}