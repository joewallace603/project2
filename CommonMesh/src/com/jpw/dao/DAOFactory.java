package com.jpw.dao;

import java.sql.*;

import com.jpw.interfaces.*;
import com.jpw.interfaces.*;

import java.sql.*;

public class DAOFactory 
{
	private static final String username = "sqladmin";
	private static final String password = "db4Me88";
	private static final String dbURL = "jdbc:mysql://localhost/myapp";
	
	private static Connection createConnection( )
	{
		Connection connection = null;
		
		try
		{
			Class.forName( "com.mysql.jdbc.Driver" );
			connection = DriverManager.getConnection(
					dbURL,
					username,
					password);
			
			System.out.println("jdbc connection made:  dburl = " + dbURL + ", username = " + username + ", password = " + password );
		}
		catch( Exception e )
		{
			System.out.println( "Connection to " + dbURL + " failed:  " );
			e.printStackTrace();
		}
		
		return connection;
	}

	public static UserDAO getUserDAO( )
	{
		Connection transactionConnection = createConnection( );
		
		return new UserDAOImpl( transactionConnection );
	}
	
	public static FriendDAO getFriendDAO( )
	{
		Connection transactionConnection = createConnection( );
		
		return new FriendDAOImpl( transactionConnection );
	}
	
	public static EventDAO getEventDAO( )
	{
		Connection transactionConnection = createConnection( );
		
		return new EventDAOImpl( transactionConnection );
	}
	
	public static void close( Statement statement, Connection connection )
	{
		if( statement != null )
		{
			try 
			{
				statement.close( );
			} 
			catch ( SQLException e )
			{
				System.out.println( "Unable to close ResultSet" );
				e.printStackTrace( );
			}
		}
		
		if( connection != null )
		{
			try
			{
				connection.close( );
			}
			catch( SQLException e )
			{
				System.out.println( "Unable to close connection" );
				e.printStackTrace( );
			}
		}
	}
	
}
