package com.jpw.dao;

import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.service.*;

public class MyHibernateUtil
{
	private static final SessionFactory sessionFactory;
	
	
	static
	{
		try
		{
			Configuration configuration = new Configuration( );
			
			configuration.configure( );
			
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        

		     sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			
			
			
			
			// sessionFactory = new AnnotationConfiguration( ).configure( ).buildSessionFactory( );
			System.out.println( "SESSION OK!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" );
		}
		catch( Throwable e )
		{
			System.err.println( "Initial SessionFactory creation failed." );
			
			throw new ExceptionInInitializerError( e );
		}
	}
	
	public static SessionFactory getSessionFactory( )
	{
		return sessionFactory;
	}
}
