package com.jpw.app;

import java.sql.*;

public class EventCreationData
{
	private String title;
	private String location;
	private String description;
	private Date createDate;
	private String creator;
	private int eventId;
	
	//comment

	public int getEventId( )
	{
		return eventId;
	}

	public void setEventId( int eventId )
	{
		this.eventId = eventId;
	}

	public String getCreator( )
	{
		return creator;
	}

	public void setCreator( String creator )
	{
		this.creator = creator;
	}

	public String getTitle( )
	{
		return title;
	}

	public void setTitle( String title )
	{
		this.title = title;
	}

	public String getLocation( )
	{
		return location;
	}

	public void setLocation( String location )
	{
		this.location = location;
	}

	public String getDescription( )
	{
		return description;
	}

	public void setDescription( String description )
	{
		this.description = description;
	}

	public Date getCreateDate( )
	{
		return createDate;
	}

	public void setCreateDate( Date createDate )
	{
		this.createDate = createDate;
	}

	public String toString( )
	{
		StringBuffer buf = new StringBuffer( );
		buf.append( "EventDetail:" );
		buf.append( "    Event ID:" + eventId );
		buf.append( "       Title:  " + title );
		buf.append( "        Date:  " + createDate );
		buf.append( "    Location:  " + location );
		buf.append( "        Desc:  " + description );
		buf.append( "     Creator:  " + creator );

		return buf.toString();
	}
}
