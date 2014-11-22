package com.jpw.dto;

import java.sql.Timestamp;
import java.util.*;

import com.jpw.client.*;

public class EventUpdate
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
	
	private Set<String> usersToNotifyDateTimeLocationChange = new HashSet<String>( );
	
	private Set<String> usersToInvite = new HashSet<String>( );
	
	private Set<String> userToUninvite = new HashSet<String>( );
	
	private boolean dateTimeChange;
	
	private boolean locationChange;
	
	public EventUpdate( )
	{
		
	}

	public EventUpdate( ClientEventForm clientEventForm )
	{
		this.eventId = clientEventForm.getEventId( );
		
		this.title = clientEventForm.getTitle( );
		
		this.description = clientEventForm.getDescription( );
		
		this.eventCreateDate = clientEventForm.getEventCreateDate( );
		
		this.eventDate = clientEventForm.getEventDate( );
		
		this.eventDuration = clientEventForm.getEventDuration( );
		
		this.location = clientEventForm.getLocation( );
		
		this.eventImageName = clientEventForm.getEventImageName( );
		
		this.replyDueDate = clientEventForm.getReplyDueDate( );
		
		this.creatorId = clientEventForm.getCreatorId( );
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

	public Set<String> getUsersToNotifyDateTimeLocationChange( )
	{
		return usersToNotifyDateTimeLocationChange;
	}

	public void setUsersToNotifyDateTimeLocationChange(
			Set<String> usersToNotifyDateTimeLocationChange )
	{
		this.usersToNotifyDateTimeLocationChange = usersToNotifyDateTimeLocationChange;
	}

	public Set<String> getUsersToInvite( )
	{
		return usersToInvite;
	}

	public void setUsersToInvite( Set<String> usersToInvite )
	{
		this.usersToInvite = usersToInvite;
	}

	public Set<String> getUsersToUninvite( )
	{
		return userToUninvite;
	}

	public void setUsersToUnivite( Set<String> usersToUnivite )
	{
		this.userToUninvite = usersToUnivite;
	}
	
	public boolean isDateTimeChange( )
	{
		return dateTimeChange;
	}

	public void setDateTimeChange( boolean dateTimeChange )
	{
		this.dateTimeChange = dateTimeChange;
	}

	public boolean isLocationChange( )
	{
		return locationChange;
	}

	public void setLocationChange( boolean locationChange )
	{
		this.locationChange = locationChange;
	}

	public String toString( )
	{
		StringBuffer buf = new StringBuffer( );
		
		buf.append( "EventId=" + eventId + ", Title=" + title + ", Desc=" + description );
		buf.append( ",EventCreateDate=" + eventCreateDate + ", EventDate=" + eventDate );
		buf.append( ",Duration=" + eventDuration + ", Location=" + location );
		buf.append( ",EventImageName=" + eventImageName );
		buf.append( ",ReplyDueDate=" + replyDueDate + ", CreatorId=" + creatorId );			    
		buf.append( ",usersToNotifyTimeLocationChange: " +  usersToNotifyDateTimeLocationChange );
		buf.append( ",usersToUnivite: " + userToUninvite );
		buf.append( ", usersToInvitte: " + usersToInvite );
		buf.append( ", dateTimeChange: " + dateTimeChange );
		buf.append( ", locationChange: " + locationChange );

		return buf.toString( );
	}
}
