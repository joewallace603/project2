package com.jpw.client;

import java.util.*;

public class ClientEventInvitationList
{
	private int eventId;
	
	private Set<ClientContact> going = new HashSet<ClientContact>( );
	
	private Set<ClientContact> tentative = new HashSet<ClientContact>( );
	
	private Set<ClientContact> declined = new HashSet<ClientContact>( );
	
	private Set<ClientContact> hasNotResponded = new HashSet<ClientContact>( );
	
	public ClientEventInvitationList( )
	{
		
	}
	
	public ClientEventInvitationList( int eventId, Set<ClientContact> going, Set<ClientContact> tentative,
			Set<ClientContact> declined, Set<ClientContact> hasNotResponded )
	{
		this.eventId = eventId;
		this.going = going;
		this.tentative = tentative;
		this.declined = declined;
		this.hasNotResponded = hasNotResponded;
	}

	public Set<ClientContact> getGoing( )
	{
		return going;
	}

	public void setGoing( Set<ClientContact> going )
	{
		this.going = going;
	}

	public Set<ClientContact> getTentative( )
	{
		return tentative;
	}

	public void setTentative( Set<ClientContact> tentative )
	{
		this.tentative = tentative;
	}

	public Set<ClientContact> getDeclined( )
	{
		return declined;
	}

	public void setDeclined( Set<ClientContact> declined )
	{
		this.declined = declined;
	}

	public Set<ClientContact> getHasNotResponded( )
	{
		return hasNotResponded;
	}

	public void setHasNotResponded( Set<ClientContact> hasNotResponded )
	{
		this.hasNotResponded = hasNotResponded;
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
		buf.append( "ClientEventInvitationList:  " );
		buf.append( "   number going = " + going.size( ) );
		buf.append( "   number tentative = " + tentative.size( ) );
		buf.append( "   number declined = " + declined.size( ) );
		buf.append( "   number not responded = " + hasNotResponded.size( ) );
		
		return buf.toString( );
	}
}
