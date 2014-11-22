package com.jpw.dto;

import javax.persistence.*;

@Entity
@Table( name = "EventUserStatus" )
public class EventUserStatus
{
	@Id
	@GeneratedValue
	@Column( name = "EventStatusId" )
	private int eventStatusId;
	
	@ManyToOne
	@JoinColumn( name = "EventId" )
	private Event event;
	
	@ManyToOne
	@JoinColumn( name = "UserId")
	private User user;
	
//	@Column( name = "UserId" )
//	private String userId;
	 
	@Column( name = "UserEventStatus")
	private String userEventStatus;
	
	public EventUserStatus( )
	{
		
	}

	public int getEventStatusId( )
	{
		return eventStatusId;
	}

	public void setEventStatusId( int eventStatusId )
	{
		this.eventStatusId = eventStatusId;
	}

	public Event getEvent( )
	{
		return event;
	}

	public void setEvent( Event event )
	{
		this.event = event;
	}

	public User getUser( )
	{
		return user;
	}

	public void setUser( User user )
	{
		this.user = user;
	}
	
//	public String getUserId( )
//	{
//		return userId;
//	}
//
//	public void setUserId( String userId )
//	{
//		this.userId = userId;
//	}

	public String getUserEventStatus( )
	{
		return userEventStatus;
	}

	public void setUserEventStatus( String userEventStatus )
	{
		this.userEventStatus = userEventStatus;
	}
	
	public String toString( )
	{
		StringBuffer buf = new StringBuffer( );
		
		buf.append( "EventUserStatus:  eventStatusId = " + eventStatusId + ", eventId = " + event.getEventId( ) + ", userId = " + user.getUserId() + ", userEventStatus = " + userEventStatus );
		
		return buf.toString( );
	}
}
