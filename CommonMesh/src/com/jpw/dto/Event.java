package com.jpw.dto;

import javax.persistence.*;

import com.jpw.client.ClientEventForm;

import java.sql.*;
import java.util.*;

@Entity
@Table( name = "Event" )
public class Event
{
	@Id
	@GeneratedValue
	@Column( name="EventId")
	private Integer eventId;
	
	@Column( name ="Title" )
	private String title;
	
	@Column( name = "Description" )
	private String description;
	
	@Column( name = "EventCreateDate" )
	private Timestamp eventCreateDate;
	
	@Column( name = "EventDate")
	private Timestamp eventDate;
	
	@Column( name = "EventDuration")
	private Integer eventDuration;
	
	@Column( name = "Location" )
	private String location;
	
	@Column( name = "EventImageName" )
	private String eventImageName;
	
	@Column( name = "ReplyDueDate" )
	private Timestamp replyDueDate;
	
	@Column( name = "CreatorId" )
	private String creatorId;
	
	// Consider moving data transfer of Event to ClientEvent in the DAO object to allow for lazy loading
	
	@OneToMany( cascade = CascadeType.ALL, mappedBy = "event", orphanRemoval = true )
	private Set<EventUserStatus> eventUserStatuses = new HashSet<EventUserStatus>( );
	
	@OneToMany( cascade = CascadeType.ALL, mappedBy = "event" )
	@OrderBy( "date")
	private List<EventComment> eventComments = new ArrayList<EventComment>( );
	
	public Event( )
	{
		
	}
	
	public Event( ClientEventForm clientEventForm )
	{
		
	}
	
	public int getEventId( )
	{
		int retval = eventId == null? -1 : eventId;
		return retval;
	}
	
	public void setEventId( int eventId )
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

	public Timestamp getEventDate( )
	{
		return eventDate;
	}

	public void setEventDate( Timestamp eventDate )
	{
		this.eventDate = eventDate;
	}

	public Timestamp getEventCreateDate( )
	{
		return eventCreateDate;
	}

	public void setEventCreateDate( Timestamp eventCreateDate )
	{
		this.eventCreateDate = eventCreateDate;
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

		public Integer getEventDuration( )
	{
		return eventDuration;
	}

	public void setEventDuration( Integer eventDuration )
	{
		this.eventDuration = eventDuration;
	}

	public List<EventComment> getEventComments( )
	{
		return eventComments;
	}

	public void setEventComments( List<EventComment> eventComments )
	{
		this.eventComments = eventComments;
	}

	public String toString( )
	{
		StringBuffer buf = new StringBuffer( );
		
		buf.append( "EventId=" + eventId + ", Title=" + title + ", Desc=" + description + 
				    ", EventCreateDate=" + eventCreateDate + ", EventDate=" + eventDate +
				    ", Duration=" + eventDuration + ", Location=" + location + 
				    ", EventImageName=" + eventImageName + 
				    ", ReplyDueDate=" + replyDueDate + ", CreatorId=" + creatorId +				    
				    ", Number of EventUserStatuses=" + eventUserStatuses.size( ) );
		
		return buf.toString( );
	}

	public Set<EventUserStatus> getEventUserStatuses( )
	{
		return eventUserStatuses;
	}

	public void setEventUserStatuses( Set<EventUserStatus> eventUserStatuses )
	{
		this.eventUserStatuses = eventUserStatuses;
	}
}
