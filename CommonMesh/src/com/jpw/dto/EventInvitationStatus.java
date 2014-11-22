package com.jpw.dto;

import com.jpw.app.*;
import javax.persistence.*;

@Entity
@Table( name = "EventInvitationStatus" )
public class EventInvitationStatus
{
	
	@Column( name = "EventId" )
	private int eventId;
	
	@Id
	@Column( name = "UserId" )
	private String userId;
	
	@Column( name = "UserInvitationStatus" )
	private UserInvitationStatus userInvitationStatus;
	
	@Column( name = "UserHasHiddenEvent" )
	private boolean userHasHiddenEvent;
	
	public int getEventId( )
	{
		return eventId;
	}
	public void setEventId( int eventId )
	{
		this.eventId = eventId;
	}
	public String getUserId( )
	{
		return userId;
	}
	public void setUserId( String userId )
	{
		this.userId = userId;
	}
	public UserInvitationStatus getUserInvitationStatus( )
	{
		return userInvitationStatus;
	}
	public void setUserInvitationStatus( UserInvitationStatus userInvitationStatus )
	{
		this.userInvitationStatus = userInvitationStatus;
	}
	public boolean isUserHasHiddenEvent( )
	{
		return userHasHiddenEvent;
	}
	public void setUserHasHiddenEvent( boolean userHasHiddenEvent )
	{
		this.userHasHiddenEvent = userHasHiddenEvent;
	}
	
}
