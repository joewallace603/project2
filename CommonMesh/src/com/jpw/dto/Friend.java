package com.jpw.dto;

public class Friend
{
	private String displayName;
	private String imageName;
	private int userID;
	
	public String getDisplayName( )
	{
		return displayName;
	}
	public void setDisplayName( String displayName )
	{
		this.displayName = displayName;
	}
	public String getImageName( )
	{
		return imageName;
	}
	public void setImageName( String imageName )
	{
		this.imageName = imageName;
	}
	public int getUserID( )
	{
		return userID;
	}
	public void setUserID( int userID )
	{
		this.userID = userID;
	}
	
	public String toString( )
	{
		return "displayName = " + displayName + ", userId = " + userID;
	}
}
