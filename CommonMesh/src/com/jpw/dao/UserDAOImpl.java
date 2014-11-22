package com.jpw.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jpw.app.UserCredentials;
import com.jpw.dto.Event;
import com.jpw.dto.User;
import com.jpw.interfaces.UserDAO;

public class UserDAOImpl implements UserDAO 
{
	private Connection connection = null;
	
	public UserDAOImpl( Connection transactionConnection )
	{
		this.connection = transactionConnection;
	}

	@Override
	public User getUser( Integer userId ) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<User> getAllUsers( ) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createUser( User user) 
	{
		System.out.println("UserDAO.createUser called");
		
		boolean success = false;
		
		
		Session session = MyHibernateUtil.getSessionFactory( ).openSession( );
		Transaction transaction = null;
		
		try
		{
			transaction = session.beginTransaction( );
			session.save( user );
			transaction.commit( );
			success = true;
			System.out.println( "UserDAOImpl.createUser succeeded." );
		}
		catch( Throwable e )
		//catch( HibernateException e )
		{
			if( transaction != null )
			{
				transaction.rollback( );
			}
			
			System.err.println( "UserDAOImpl.createUser transaction failed." );
			e.printStackTrace( );
		}
		finally
		{
			session.close( );
		}
		
		return success;
		
/*
		
		String sql = "INSERT INTO user (firstName, lastName, username, password, gender, createDate, dateOfBirth, zipCode) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps = null;
		
		try
		{
			ps = connection.prepareStatement( sql );
			
			ps.setString( 1, user.getFirstName( ) );
			ps.setString( 2, user.getLastName( ) );
			ps.setString( 3, user.getUsername( ) );
			ps.setString( 4, user.getPassword( ) );
			ps.setString( 5, user.getGender( ) );
			ps.setDate( 6, user.getCreateDate( ) );
			ps.setDate( 7, user.getDateOfBirth( ) );
			ps.setString( 8, user.getZipCode( ) );
			
			ps.executeUpdate( );
			
			System.out.println( "insert done.");
			success = true;
		}
		catch( SQLException e )
		{
			System.out.print( "UserDAO.createUser:  Update failed:  " );
			e.printStackTrace( );
		}
		finally
		{
			DAOFactory.close( ps, connection );
		}
		
		return success;
		*/
	}

	@Override
	public void modifyUser( User user ) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser( Integer userId ) 
	{
		// TODO Auto-generated method stub

	}

	public boolean authenticateUser( UserCredentials userCredentials )
	{
		boolean authenticated = false;
		
		String userId = userCredentials.getUserId( );
		
		if( userId != null && userId.equals( "dummyName" ) )
		{
			//TODO  Need a better way to allow new users to be created without authentication.
			authenticated = true;
		}
		else
		{

			PreparedStatement ps = null;
			String preparedQuery = "SELECT password FROM user WHERE userId=?";

			try
			{
				ps = connection.prepareStatement( preparedQuery );
				ps.setString( 1, userCredentials.getUserId( ) );
				ResultSet resultSet = ps.executeQuery( );

				System.out.println( "query done." );

				if( resultSet.next( ) )
				{
					String password = resultSet.getString( "password"  );

					System.out.println( "table password = " + password + ", entered password = " + userCredentials.getPassword( ) );

					if( password.equals(userCredentials.getPassword( ) ) )
					{
						authenticated = true;
					}
				}
				else
				{
					System.out.println( "Query for username " + userCredentials.getPassword( ) + " is empty." );
				}
			}
			catch( SQLException e )
			{
				System.out.print( "UserDAO.authenticateUser:  query failed:  " );
				e.printStackTrace( );
			}
			finally
			{
				DAOFactory.close( ps, connection );
			}
		}

		return authenticated;
	}
}
