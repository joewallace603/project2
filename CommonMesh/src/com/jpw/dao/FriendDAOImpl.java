package com.jpw.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.*;

import com.jpw.dto.*;
import com.jpw.interfaces.FriendDAO;

public class FriendDAOImpl implements FriendDAO
{
	protected Connection connection = null;
	
	public FriendDAOImpl( Connection transactionConnection )
	{
		this.connection = transactionConnection;
	}
	
	@Override
	@SuppressWarnings( "unchecked" )
	public List<User> getUsers( String userId )
	{
		System.out.println( "IN FriendDAOImple.getUsers:  from user = " + userId ); 

		List<User> users = new ArrayList<User>( );

		Session session = MyHibernateUtil.getSessionFactory( ).openSession( );
		Transaction transaction = null;

		try
		{
			transaction = session.beginTransaction( );
			
			Query query = session.createQuery( "FROM User" );
			
			users = (List<User>)query.list( );
			
			for( User user : users )
			{
				System.out.println( "User = " + user );
			}
			transaction.commit( );
		}
		//catch( HibernateException e )
		catch( Throwable e )
		{
			if( transaction != null )
			{
				transaction.rollback( );
			}

			System.err.println( "FriendDAOImpl.getUserFriends - Query failed.  Rolling back.  " );
			e.printStackTrace( );
		}
		finally
		{
			session.close( );
		}

		return users;
	}
}
