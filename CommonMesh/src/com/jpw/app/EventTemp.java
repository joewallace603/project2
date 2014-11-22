package com.jpw.app;

import java.util.*;

public class EventTemp
{
	// ToDo alot of these members don't belong in the Event object. They can be
	// obtained
	// elsewhere...
	private int eventId;
	private String title;
	private String desc;
	private Date date;
	private int status;
	private String location;
	private List<String> going;
	private List<String> out;
	private List<String> maybe;
	private List<String> invited;
	private int numberOfComments;
	private String image;
	private Date reminder;
	private String creator;

	public Date getReminder( )
	{
		return reminder;
	}

	public void setReminder( Date reminder )
	{
		this.reminder = reminder;
	}

	public String getCreator( )
	{
		return creator;
	}

	public void setCreator( String creator )
	{
		this.creator = creator;
	}

	public String getImage( )
	{
		return image;
	}

	public void setImage( String image )
	{
		this.image = image;
	}

	public String getTitle( )
	{
		return title;
	}

	public void setTitle( String title )
	{
		this.title = title;
	}

	public String getDesc( )
	{
		return desc;
	}

	public void setDesc( String desc )
	{
		this.desc = desc;
	}

	public Date getDate( )
	{
		return date;
	}

	public void setDate( Date date )
	{
		this.date = date;
	}

	public int getStatus( )
	{
		return status;
	}

	public void setStatus( int status )
	{
		this.status = status;
	}

	public String getLocation( )
	{
		return location;
	}

	public void setLocation( String location )
	{
		this.location = location;
	}

	public List<String> getGoing( )
	{
		return going;
	}

	public void setGoing( List<String> in )
	{
		this.going = in;
	}

	public List<String> getOut( )
	{
		return out;
	}

	public void setOut( List<String> out )
	{
		this.out = out;
	}

	public List<String> getMaybe( )
	{
		return maybe;
	}

	public void setMaybe( List<String> maybe )
	{
		this.maybe = maybe;
	}

	public List<String> getInvited( )
	{
		return invited;
	}

	public void setInvited( List<String> invited )
	{
		this.invited = invited;
	}

	public int getNumberOfComments( )
	{
		return numberOfComments;
	}

	public void setNumberOfComments( int numberOfComments )
	{
		this.numberOfComments = numberOfComments;
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

		buf.append( "Event:" );
		buf.append( "                   ID: " + eventId );
		buf.append( "                Title: " + title );
		buf.append( "                 Date: " + date );
		buf.append( "             Location: " + location );
		buf.append( "                 desc: " + desc );
		buf.append( "        User's Status: " + status );
		buf.append( "         People Going: " + going );
		buf.append( "         People Maybe: " + maybe );
		buf.append( "     People Not Going: " + out );
		buf.append( "       People Invited: " + invited );
		buf.append( "   Number of Comments: " + numberOfComments );
		buf.append( "           Image Name: " + image );
		buf.append( "    Reminder for User: " + reminder );
		buf.append( "              Creator: " + creator );

		return buf.toString( );
	}

}
