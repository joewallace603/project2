package com.jpw.client;

import java.sql.Timestamp;
import java.util.*;

public class ClientEventForm
{
	private int eventId = 0;
	
	private String title;
	
	private String description;
	
	private Timestamp eventCreateDate;
	
	private Timestamp eventDate;
	
	private Integer eventDuration;
	
	private String location;
	
	private String eventImageName;
	
	private Timestamp replyDueDate;
	
	private Set<String> invitedContacts = new HashSet<String>( );
	
	private String creatorId;
	
	public ClientEventForm( )
	{
		
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

	public Set<String> getInvitedContacts( )
	{
		return invitedContacts;
	}

	public void setInvitedContacts( Set<String> invitedContacts )
	{
		this.invitedContacts = invitedContacts;
	}

	public String getCreatorId( )
	{
		return creatorId;
	}

	public void setCreatorId( String creatorId )
	{
		this.creatorId = creatorId;
	}
	
	public int getEventId( )
	{
		return eventId;
	}

	public void setEventId( int eventId )
	{
		this.eventId = eventId;
	}

	public String toString( )
	{
		StringBuffer buf = new StringBuffer( );
		
		buf.append( "Title=" + title + ", Desc=" + description + 
				    ", EventCreateDate=" + eventCreateDate + ", EventDate=" + eventDate +
				    ", Duration=" + eventDuration + ", Location=" + location + 
				    ", EventImageName=" + eventImageName + 
				    ", ReplyDueDate=" + replyDueDate + ", CreatorId=" + creatorId );
		
		for( String invitedFriend : invitedContacts )
		{
			buf.append(" friend = " + invitedFriend );
		}
		
		return buf.toString( );
	}
}
