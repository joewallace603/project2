package com.jpw.dto;

import java.sql.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jpw.client.ClientEventComment;

@Entity
@Table( name = "EventComment" )
public class EventComment
{
	@Id
	@GeneratedValue
	@Column( name = "EventCommentId" )
	private int eventCommentId;

	@ManyToOne
	@JoinColumn( name = "EventId" )
	private Event event;
	
	@ManyToOne
	@JoinColumn( name = "UserId" )
	private User user;
	
	@Column( name = "Comment")
	private String comment;
	
	@Column( name = "Date")
	private Timestamp date;
	
	public EventComment( )
	{
		
	}
	
	public EventComment( ClientEventComment clientEventComment )
	{
		this.user = new User( );
		user.setUserId( clientEventComment.getUserId( ) );
		this.comment = clientEventComment.getComment( );
		this.date = clientEventComment.getDate( );
	}
	
	public User getUser( )
	{
		return user;
	}

	public void setUser( User user )
	{
		this.user = user;
	}

	public String getComment( )
	{
		return comment;
	}

	public void setComment( String comment )
	{
		this.comment = comment;
	}

	public Timestamp getDate( )
	{
		return date;
	}

	public void setDate( Timestamp date )
	{
		this.date = date;
	}

	public int getEventCommentId( )
	{
		return eventCommentId;
	}

	public void setEventCommentId( int eventCommentId )
	{
		this.eventCommentId = eventCommentId;
	}

	public Event getEvent( )
	{
		return event;
	}

	public void setEvent( Event event )
	{
		this.event = event;
	}

	public String toString( )
	{
		StringBuffer buf = new StringBuffer( );
		buf.append( "Commentor: " + user.getFirstName( ) + " " + user.getLastName( ) 
				+ ", date: " + date + ", comment: " + comment );
		if( event != null )
		{
			buf.append ( ", eventId: " + event.getEventId( ));
		}
		return buf.toString( );
	}
}
