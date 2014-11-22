package com.jpw.dto;

public class InvitationStatus
{
	private String userId;
	
	private String status;
	
	private int invitationStatusId;
	
	public InvitationStatus( )
	{
		
	}
	
	public InvitationStatus( String userId, String status, int invitationStatusId )
	{
		this.userId = userId;
		this.status = status;
		this.invitationStatusId = invitationStatusId;
	}

	public String getUserId( )
	{
		return userId;
	}

	public void setUserId( String userId )
	{
		this.userId = userId;
	}

	public String getStatus( )
	{
		return status;
	}

	public void setStatus( String status )
	{
		this.status = status;
	}

	public int getInvitationStatusId( )
	{
		return invitationStatusId;
	}

	public void setInvitationStatusId( int invitationStatusId )
	{
		this.invitationStatusId = invitationStatusId;
	}
}
