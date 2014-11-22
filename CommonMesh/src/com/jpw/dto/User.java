package com.jpw.dto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table( name = "User" )
public class User
{
	@Id
	//@GeneratedValue
	@Column( name = "UserId" )
	private String userId;
	
	@Column( name = "FirstName" )
	private String firstName;
	
	@Column( name = "LastName" )
	private String lastName;
	
	@Column( name = "UserImageName" )
	private String userImageName;
	
	@Column( name = "Password" )
	private String password;
	
	@Column( name = "ZipCode" )
	private String zipCode;
	
	@Column( name = "IsMale" )
	private boolean isMale;
	
	@Column( name = "DateOfBirth" )
	private Date dateOfBirth;
	
	@Column( name = "CreateDate" )
	private Date createDate;
	
	@OneToMany( mappedBy = "user" )
	@OrderBy( "eventId" )
	private List<EventUserStatus> eventUserStatuses = new ArrayList<EventUserStatus>( );
	
	@OneToMany( mappedBy = "user" )
	@OrderBy( "date" )
	private List<EventComment> eventComments = new ArrayList<EventComment>( );
	
//	@OneToMany( cascade = CascadeType.ALL, mappedBy = "user" )
//	private Set<EventStatusesOfUser> eventStatusesOfUser;
	
	public User( )
	{
		
	}
	
	public User( String userId )
	{
		this.userId = userId;
	}

	public Date getCreateDate( )
	{
		return createDate;
	}

	public void setCreateDate( Date createDate )
	{
		this.createDate = createDate;
	}

	public String getUserId( )
	{
		return userId;
	}

	public void setUserId( String userId )
	{
		this.userId = userId;
	}

	public String getUserImageName( )
	{
		return userImageName;
	}

	public void setUserImageName( String userImageName )
	{
		this.userImageName = userImageName;
	}

	public String getPassword( )
	{
		return password;
	}

	public void setPassword( String userPassword )
	{
		this.password = userPassword;
	}

	public String getZipCode( )
	{
		return zipCode;
	}

	public void setZipCode( String zipcode )
	{
		this.zipCode = zipcode;
	}

	public boolean getIsMale( )
	{
		return isMale;
	}

	public void setIsMale( boolean isMale )
	{
		this.isMale = isMale;
	}

	public Date getDateOfBirth( )
	{
		return dateOfBirth;
	}

	public void setDateOfBirth( Date dateOfBirth )
	{
		this.dateOfBirth = dateOfBirth;
	}

	public String getLastName( )
	{
		return lastName;
	}

	public void setLastName( String lastName )
	{
		this.lastName = lastName;
	}
	
	public String getFirstName( )
	{
		return firstName;
	}

	public void setFirstName( String firstName )
	{
		this.firstName = firstName;
	}

	public List<EventUserStatus> getEventUserStatuses( )
	{
		return eventUserStatuses;
	}

	public void setEventUserStatuses( List<EventUserStatus> eventUserStatuses )
	{
		this.eventUserStatuses = eventUserStatuses;
	}

	public String toString( )
	{
		StringBuffer buf = new StringBuffer( );
		buf.append( "UserId=" + userId + ", FirstName=" + firstName + ", LastName=" + lastName + 
				", UserImageName=" + userImageName + ", Password=" + password + 
				", Gender=" + isMale + ", DateOfBirth=" + dateOfBirth + ", ZipCode=" + zipCode +
				", CreateDate=" + createDate ); // + ", EventUserStatuses=" + eventUserStatuses);
		return buf.toString( );
	}

//	public Set<EventStatusesOfUser> getEventStatusesOfUser( )
//	{
//		return eventStatusesOfUser;
//	}
//
//	public void setEventStatusesOfUser( Set<EventStatusesOfUser> eventStatusesOfUser )
//	{
//		this.eventStatusesOfUser = eventStatusesOfUser;
//	}
}
