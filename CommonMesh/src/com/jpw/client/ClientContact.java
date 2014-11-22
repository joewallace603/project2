package com.jpw.client;

import com.jpw.dto.User;

public class ClientContact
{
	private String firstName;
	private String lastName;
	private String imageName;
	private String userId;
	
	public ClientContact( )
	{
		
	}
	
	public ClientContact( User user )
	{
		this.firstName = user.getFirstName( );
		this.lastName = user.getLastName( );
		this.imageName = user.getUserImageName( );
		this.userId = user.getUserId( );
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
	public String getImageName( )
	{
		return imageName;
	}
	public void setImageName( String imageName )
	{
		this.imageName = imageName;
	}
	public String getUserId( )
	{
		return userId;
	}
	public void setUserId( String userId )
	{
		this.userId = userId;
	}
}
