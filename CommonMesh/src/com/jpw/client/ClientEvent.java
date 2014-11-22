package com.jpw.client;

import java.sql.Timestamp;
import java.util.*;

import com.jpw.app.UserInvitationStatus;
import com.jpw.dto.*;

public class ClientEvent
{
	private Integer eventId;

	private String title;

	private String description;

	private Timestamp eventCreateDate;

	private Timestamp eventDate;

	private Integer eventDuration;

	private String location;

	private String eventImageName;

	private Timestamp replyDueDate;

	private String creatorId;
	
	private String userEventStatus;
	
	private ClientEventInvitationList clientEventInvitationList;
	
	private Set<String> usersGoing = new HashSet<String>( );
	
	private Set<String> usersTentative = new HashSet<String>( );
	
	private Set<String> usersDeclined = new HashSet<String>( );
	
	private Set<String> usersNotResponded = new HashSet<String>( );
	
	private List<ClientEventComment> clientEventComments = new ArrayList<ClientEventComment>( );
	
	public ClientEvent( )
	{
		
	}
	
	public ClientEvent( Set<EventUserStatus> eventUserStatuses, String userId )
	{
		addEventUserStatuses( eventUserStatuses, userId );
	}
	
	public ClientEvent( Event event )
	{
		eventId = event.getEventId( );

		title = event.getTitle( );

		description = event.getDescription( );

		eventCreateDate = event.getEventCreateDate( );

		eventDate = event.getEventDate( );

		eventDuration = event.getEventDuration( );

		location = event.getLocation( );

		eventImageName = event.getEventImageName( );

		replyDueDate = event.getReplyDueDate( );

		creatorId = event.getCreatorId( );
	}
	
	public void addEventComments( List<EventComment> eventComments )
	{
		for( EventComment eventComment : eventComments )
		{
			ClientEventComment clientEventComment = new ClientEventComment( eventComment );
			clientEventComments.add( clientEventComment );
		}
	}
	
		
	public void addEventUserStatuses( Set<EventUserStatus> eventUserStatuses, String userId )
	{
		
		for( EventUserStatus eventUserStatus : eventUserStatuses )
		{
			System.out.println( "EventUserStatus:  " + eventUserStatus );
			System.out.println( "user event status = '" + eventUserStatus.getUserEventStatus( ) + "'" );
			UserInvitationStatus userInvitationStatus = UserInvitationStatus.valueOf( eventUserStatus.getUserEventStatus( ) );
			
			System.out.println( "creatorId = " + creatorId + ", eventUserStatus = " + eventUserStatus );
			
			//if( ( creatorId != null && creatorId.equals( eventUserStatus.getUser( ).getUserId( ) ) ) ||
			if( userId != null && userId.equals( eventUserStatus.getUser( ).getUserId( ) ) )  //)
			{
				userEventStatus = eventUserStatus.getUserEventStatus( );
				System.out.println( "Setting user status to " + userEventStatus );
			}
			
			switch( userInvitationStatus )
			{
				case NO_RESPONSE:
					usersNotResponded.add( eventUserStatus.getUser( ).getUserId( ) );
					break;
				case DECLINED:
					usersDeclined.add( eventUserStatus.getUser( ).getUserId( ) );
					break;
				case TENTATIVE:
					usersTentative.add( eventUserStatus.getUser( ).getUserId( ) );
					break;
				case ACCEPTED:
					usersGoing.add( eventUserStatus.getUser( ).getUserId( ) );
					break;
				case DATE_TIME_LOCATION_CHANGE:
				case DATE_TIME_CHANGE:
				case LOCATION_CHANGE:
					usersNotResponded.add( eventUserStatus.getUser( ).getUserId( ) );
					break;
				default:
					usersNotResponded.add( eventUserStatus.getUser( ).getUserId( ) );
			}

		}
		
		System.out.println( "going=" + usersGoing.size( ) + ", usersDeclined=" + usersDeclined.size() + ", userNotResponded=" + usersNotResponded.size( ) +
				            ", usersTentative=" + usersTentative.size());
	}

	public Integer getEventId( )
	{
		return eventId;
	}

	public void setEventId( Integer eventId )
	{
		this.eventId = eventId;
	}

	public String getTitle( )
	{
		return title;
	}

	public void setTitle( String title )
	{
		this.title = title;
	}

	public String getDescription( )
	{
		return description;
	}

	public void setDescription( String description )
	{
		this.description = description;
	}

	public Timestamp getEventCreateDate( )
	{
		return eventCreateDate;
	}

	public void setEventCreateDate( Timestamp eventCreateDate )
	{
		this.eventCreateDate = eventCreateDate;
	}

	public Timestamp getEventDate( )
	{
		return eventDate;
	}

	public void setEventDate( Timestamp eventDate )
	{
		this.eventDate = eventDate;
	}

	public Integer getEventDuration( )
	{
		return eventDuration;
	}

	public void setEventDuration( Integer eventDuration )
	{
		this.eventDuration = eventDuration;
	}

	public String getLocation( )
	{
		return location;
	}

	public void setLocation( String location )
	{
		this.location = location;
	}

	public String getEventImageName( )
	{
		return eventImageName;
	}

	public void setEventImageName( String eventImageName )
	{
		this.eventImageName = eventImageName;
	}

	public Timestamp getReplyDueDate( )
	{
		return replyDueDate;
	}

	public void setReplyDueDate( Timestamp replyDueDate )
	{
		this.replyDueDate = replyDueDate;
	}

	public String getCreatorId( )
	{
		return creatorId;
	}

	public void setCreatorId( String creatorId )
	{
		this.creatorId = creatorId;
	}

	public String getUserEventStatus( )
	{
		return userEventStatus;
	}

	public void setUserEventStatus( String userEventStatus )
	{
		this.userEventStatus = userEventStatus;
	}

	public Set<String> getUsersGoing( )
	{
		return usersGoing;
	}

	public void setUsersGoing( Set<String> usersGoing )
	{
		this.usersGoing = usersGoing;
	}

	public Set<String> getUsersTentative( )
	{
		return usersTentative;
	}

	public void setUsersTentative( Set<String> usersTentative )
	{
		this.usersTentative = usersTentative;
	}

	public Set<String> getUsersDeclined( )
	{
		return usersDeclined;
	}

	public void setUsersDeclined( Set<String> usersDeclined )
	{
		this.usersDeclined = usersDeclined;
	}

	public Set<String> getUsersNotResponded( )
	{
		return usersNotResponded;
	}

	public void setUsersNotResponded( Set<String> usersNotResponded )
	{
		this.usersNotResponded = usersNotResponded;
	}

	public List<ClientEventComment> getClientEventComments( )
	{
		return clientEventComments;
	}

	public void setClientEventComments( List<ClientEventComment> clientEventComments )
	{
		this.clientEventComments = clientEventComments;
	}

	public ClientEventInvitationList getClientEventInvitationList( )
	{
		return clientEventInvitationList;
	}

	public void setClientEventInvitationList(
			ClientEventInvitationList clientEventInvitationList )
	{
		this.clientEventInvitationList = clientEventInvitationList;
	}
	

}
