package com.jpw.dto;

import java.sql.*;
import java.util.*;

public class EventChange
{
	private Timestamp date = null;;
	
	private String location = "";
	
	private Set<InvitationStatus> invitationStatuses = new HashSet<InvitationStatus>( );

	public Timestamp getDate( )
	{
		return date;
	}

	public void setDate( Timestamp date )
	{
		this.date = date;
	}

	public String getLocation( )
	{
		return location;
	}

	public void setLocation( String location )
	{
		this.location = location;
	}

	public Set<InvitationStatus> getInvitationStatuses( )
	{
		return invitationStatuses;
	}

	public void setInvitationStatuses( Set<InvitationStatus> invitationStatuses )
	{
		this.invitationStatuses = invitationStatuses;
	}
	
	public String toString( )
	{
		return "EventChange:  date=" + date + ", location=" + location + ", # of invitees=" + invitationStatuses.size( );
	}
}
