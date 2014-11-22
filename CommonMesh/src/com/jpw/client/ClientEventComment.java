package com.jpw.client;

import java.sql.*;

import com.jpw.dto.EventComment;

public class ClientEventComment
{
	private String userId;
	
	private String firstName;
	
	private String lastName;
	
	private int eventId;
	
	private String comment;
	
	private Timestamp date;
	
	public ClientEventComment( )
	{
		
	}
	
	public ClientEventComment( EventComment eventComment )
	{
		this.userId = eventComment.getUser( ).getUserId( );
		this.firstName = eventComment.getUser( ).getFirstName( );
		this.lastName = eventComment.getUser( ).getLastName( );
		this.eventId = eventComment.getEvent( ).getEventId( );
		this.comment = eventComment.getComment( );
		this.date = eventComment.getDate( );
	}

	public String getUserId( )
	{
		return userId;
	}

	public void setUserId( String userId )
	{
		this.userId = userId;
	}

	public int getEventId( )
	{
		return eventId;
	}

	public void setEventId( int eventId )
	{
		this.eventId = eventId;
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
	
	public String getFirstName( )
	{
		return firstName;
	}

	public void setFirstName( String firstName )
	{
		this.firstName = firstName;
	}

	public String getLastName( )
	{
		return lastName;
	}

	public void setLastName( String lastName )
	{
		this.lastName = lastName;
	}

	public String toString( )
	{
		StringBuffer buf = new StringBuffer( );
		buf .append( "CientComment:  eventId = " + eventId + ", userId = " + userId + ", comment = " +
				comment + ", date = " + date );
		
		return buf.toString( );
				
	}
}
