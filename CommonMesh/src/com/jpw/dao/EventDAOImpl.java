package com.jpw.dao;

import java.sql.*;
import java.util.*;

import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.transform.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jpw.client.*;
import com.jpw.dto.*;
import com.jpw.interfaces.EventDAO;
import com.jpw.ws.WSEvents;

public class EventDAOImpl implements EventDAO 
{
	protected Connection connection = null;
	private static final Logger logger = LoggerFactory.getLogger( EventDAOImpl.class );
	
	public EventDAOImpl( Connection transactionConnection )
	{
		this.connection = transactionConnection;
	}
	
	@Override
	public boolean createEvent( Event event ) 
	{
		System.out.println( "IN EventDAO:createEvent" );
		
		boolean success = false;
		
		Session session = MyHibernateUtil.getSessionFactory( ).openSession( );
		Transaction transaction = null;
		
		try
		{
			transaction = session.beginTransaction( );
			Integer eventId = (Integer)session.save( event );
			
			transaction.commit( );
			success = true;
			System.out.println( "EventDAOImpl.createEvent succeeded.  EventId = " + eventId );
		}
		catch( Throwable e )
		//catch( HibernateException e )
		{
			if( transaction != null )
			{
				transaction.rollback( );
			}
			
			System.err.println( "EventDAOImpl.createEvent transaction failed." );
			e.printStackTrace( );
		}
		finally
		{
			session.close( );
		}
		
		return success;
		
	}
	
	@Override
	@SuppressWarnings( "unchecked" )
	public List<ClientEvent> getEventById( String userId, int eventId )
	{
		System.out.println( "IN EventDAOImple.getEventById:  eventId = " + eventId );
		
		List<ClientEvent> clientEvents = new ArrayList<ClientEvent>( );
		
		Session session = MyHibernateUtil.getSessionFactory( ).openSession( );
		Transaction transaction = null;
		
		try
		{
			transaction = session.beginTransaction( );
			
			Criteria criteria = session.createCriteria( Event.class );
			
			criteria.add( Restrictions.eq("eventId", eventId ) );
			
			List<Event> events = (List<Event>)criteria.list( );
			
			for( Event event : events )
			{
				ClientEvent clientEvent = DAODTOHelper.createClientEventWithCommentsAndInvitationStatuses( event, userId );
//				ClientEvent clientEvent = new ClientEvent( event );
//				
//				clientEvent.addEventUserStatuses( event.getEventUserStatuses( ), userId);
//				clientEvent.addEventComments( event.getEventComments( ) );
				
				clientEvents.add( clientEvent );
			}
			
		}
		//catch( HibernateException e )
		catch( Throwable e )
		{
			if( transaction != null )
			{
				transaction.rollback( );
			}
			
			System.err.println( "EventDAOImpl.getUserEvents - Query failed.  Rolling back.  " );
			e.printStackTrace( );
		}
		finally
		{
			session.close( );
		}
		
		return clientEvents;
	}

	@Override
	@SuppressWarnings( "unchecked" )
	public List<ClientEvent> getUserEventsOverTimePeriod( String userId, Timestamp fromTime, Timestamp toTime )
	{
		logger.info( "IN getUserEventsOverTimePeriod:  userId={}, fromTime={}, toTime={}.", fromTime, toTime );
				
		List<ClientEvent> clientEvents = new ArrayList<ClientEvent>( );
		
		Session session = MyHibernateUtil.getSessionFactory( ).openSession( );
		Transaction transaction = null;
		
		try
		{
			transaction = session.beginTransaction( );
			
			Criteria criteria = session.createCriteria( Event.class, "event" );
			
			criteria.createAlias( "event.eventUserStatuses", "eventUserStatus" );
			criteria.createAlias( "eventUserStatus.user", "user" );
			
			Criterion eventRangeCriterion = Restrictions.between( "event.eventDate", fromTime, toTime );
			Criterion userIdCriterion = Restrictions.eq( "user.userId", userId );
			 
			/*
			Criteria criteria = session.createCriteria( User.class, "user" );
			
			Criterion userIdCriterion = Restrictions.eq( "user.userId", userId );
			
			criteria.createAlias( "user.eventUserStatuses", "eventUserStatus" );
			criteria.createAlias( "eventUserStatus.event", "event" );
			Criterion eventRangeCriterion = Restrictions.between( "event.eventDate", fromTime, toTime );
			*/
			
			Criterion completeCriterion = Restrictions.conjunction( ).add( userIdCriterion ).add( eventRangeCriterion );
			
			criteria.add( completeCriterion );
			criteria.addOrder( Order.asc( "event.eventDate" ) );
			//criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			
			
			List<Event>events = (List<Event>)criteria.list( );
			
			logger.info( "getUserEventsOverTimePeriod:  found {} events with criteria {}.", events.size(), criteria );
			
			for( Event event : events )
			{
				logger.info( "getUserEventsOverTimePeriod:  event = {}.", event.getTitle( ) );
				
				//TODO consider returning event without invitation statuses and comments for month view
				ClientEvent clientEvent = DAODTOHelper.createClientEventWithCommentsAndInvitationStatuses( event, userId );
				clientEvents.add( clientEvent );
			}
			
			transaction.commit();
		}
		//catch( HibernateException e )
		catch( Throwable e )
		{
			if( transaction != null )
			{
				transaction.rollback( );
			}
			
			logger.error( "getUserEventsOverTimePeriod - Query failed.  Rolling back.  {}", e );
			e.printStackTrace( );
		}
		finally
		{
			session.close( );
		}
		
		return clientEvents;
	}
	
	@SuppressWarnings( "unchecked" )
	public boolean handleResponseToInvitation( String userId, int eventId, String response )
	{
		System.out.println( "IN EventDAOImple.handleResponseToInvitation:  userId = " + userId +
				"eventId = " + eventId + ", response = " + response );

		boolean success = false;

		Session session = MyHibernateUtil.getSessionFactory( ).openSession( );
		Transaction transaction = null;
		
		try
		{
			transaction = session.beginTransaction( );
			
			Criteria criteria = session.createCriteria( Event.class );
			
			criteria.add( Restrictions.eq("eventId", eventId ) );
			
			List<Event> events = (List<Event>)criteria.list( );
			
			if( events.size( ) > 0 )
			{
				Set<EventUserStatus> eventUserStatuses = events.get( 0 ).getEventUserStatuses( );
				
				Iterator<EventUserStatus> it = eventUserStatuses.iterator( );
				
				while( it.hasNext( ) )
				{
					EventUserStatus eventUserStatus = it.next( );
					
					if( eventUserStatus.getUser( ).getUserId( ).equals( userId ) )
					{
						eventUserStatus.setUserEventStatus( response );
						
						session.update( events.get( 0 ) );
						
						success = true;
					}
				}
			}
			
			transaction.commit( );
				
		}
		catch( Exception e )
		{
			if( transaction != null )
			{
				transaction.rollback( );
			}
			
			System.err.println( "EventDAOImpl.handleResponseToInvitation - Update failed.  Rolling back.  " );
			e.printStackTrace( );
		}
		finally
		{
			session.close( );
		}
		
		return success;
	}
	
	/*
	@SuppressWarnings( "unchecked" )
	public List<ClientEventInvitationList> getEventUserStatusesByEventId( String userId, int eventId )
	{
		
		// TODO  remove this method.  Replaced by getClientEventInvitationList( )
		System.out.println( "IN EventDAOImple.getEventUserStatusesByEventId:  userId = " + userId + ", eventId = " + eventId );

		Session session = MyHibernateUtil.getSessionFactory( ).openSession( );
		Transaction transaction = null;
		
		List<ClientEventInvitationList> clientEventInvitationLists = new ArrayList<ClientEventInvitationList>( );
		
		try
		{
			transaction = session.beginTransaction( );

			Criteria criteria = session.createCriteria( Event.class );

			criteria.add( Restrictions.eq("eventId", eventId ) );

			List<Event>events = (List<Event>)criteria.list( );

			if( events.size( ) == 1 )
			{
				Set<EventUserStatus> eventUserStatuses = events.get( 0 ).getEventUserStatuses( );
				ClientEventInvitationList clientEventInvitationList = 
						DAODTOHelper.getClientEventInvitationList( eventUserStatuses, eventId );
				clientEventInvitationLists.add( clientEventInvitationList );
//				Set<EventUserStatus> eventUserStatuses = events.get( 0 ).getEventUserStatuses( );
//				ClientEvent clientEvent = new ClientEvent( eventUserStatuses, userId );
//				clientEvents.add( clientEvent );

			}
			
			transaction.commit( );
		}
		catch( Exception e )
		{
			if( transaction != null )
			{
				transaction.rollback( );
			}

			System.err.println( "EventDAOImpl.getEventUserStatusesByEventId - failed.  Rolling back.  " );
			e.printStackTrace( );
		}
		finally
		{
			session.close( );
		}
		
		return clientEventInvitationLists;
	}
	*/
	
	@SuppressWarnings( "unchecked" )
	public boolean addCommentToEvent( EventComment eventComment, int eventId )
	{
		System.out.println( "IN EventDAOImple.addCommentToEvent:  eventId = " + eventId +
				"eventComment = " + eventComment );

		boolean success = false;

		Session session = MyHibernateUtil.getSessionFactory( ).openSession( );
		Transaction transaction = null;
		
		try
		{
			transaction = session.beginTransaction( );
			
			Criteria criteria = session.createCriteria( Event.class );
			
			criteria.add( Restrictions.eq("eventId", eventId ) );
			
			List<Event> events = (List<Event>)criteria.list( );
			
			if( events.size( ) > 0 )
			{
				Event event = events.get( 0 );
				List<EventComment> eventComments = event.getEventComments( );
				
				eventComment.setEvent( event );

				eventComments.add( eventComment );

				session.update( events.get( 0 ) );

				success = true;
			}

			transaction.commit( );
				
		}
		catch( Exception e )
		{
			if( transaction != null )
			{
				transaction.rollback( );
			}
			
			System.err.println( "EventDAOImpl.handleResponseToInvitation - Update failed.  Rolling back.  " );
			e.printStackTrace( );
		}
		finally
		{
			session.close( );
		}
		
		return success;
	}
	
	@SuppressWarnings( "unchecked" )
	public List<ClientEventComment> getEventComments( int eventId )
	{
		System.out.println( "EventDAO.getEventComments for eventId = " + eventId );
		
		List<ClientEventComment> clientEventComments = new ArrayList<ClientEventComment>( );System.out.println( "IN EventDAOImple.getEventUserStatusesByEventId:  eventId = " + eventId );

		Session session = MyHibernateUtil.getSessionFactory( ).openSession( );
		Transaction transaction = null;
		
		try
		{
			transaction = session.beginTransaction( );

			Criteria criteria = session.createCriteria( Event.class );

			criteria.add( Restrictions.eq("eventId", eventId ) );

			List<Event>events = (List<Event>)criteria.list( );

			if( events.size( ) > 0 )
			{
				List<EventComment> eventComments = events.get( 0 ).getEventComments( );
				
				//TODO  populate client object in DAO to take advantage of lazy loading.  Need to this elsewhere too.
				for( EventComment eventComment : eventComments )
				{
					ClientEventComment clientEventComment = new ClientEventComment( eventComment );
					clientEventComments.add( clientEventComment );
				}
			}
			
			transaction.commit( );
		}
		catch( Exception e )
		{
			if( transaction != null )
			{
				transaction.rollback( );
			}

			System.err.println( "EventDAOImpl.getEventComments - failed.  Rolling back.  " );
			e.printStackTrace( );
		}
		finally
		{
			session.close( );
		}
		
		return clientEventComments;
	}
	
	public List<ClientEvent> getPreviousEventById( String userId, int eventId )
	{
		return getAdjacentEventById( userId, eventId, false );
	}
	
	public List<ClientEvent> getNextEventById( String userId, int eventId )
	{
		return getAdjacentEventById( userId, eventId, true );
	}
	
	@SuppressWarnings( "unchecked" )
	private List<ClientEvent> getAdjacentEventById( String userId, int eventId, boolean next )
	{
		System.out.println( "IN EventDAOImple.getAdjacentEventById:  userId = " + userId + ", eventId = " + eventId 
				+ ", next = " + next );
		
		List<ClientEvent> clientEvents = new ArrayList<ClientEvent>( );
		
		Session session = MyHibernateUtil.getSessionFactory( ).openSession( );
		Transaction transaction = null;
		
		try
		{
			transaction = session.beginTransaction( );

			//			String queryString;
			Criteria criteria = session.createCriteria( Event.class );
			criteria.add( Restrictions.eqOrIsNull( "eventId", eventId ) );
			List<Event> events = criteria.list( );

			if( events.size( ) > 0 ) 
			{
				Timestamp eventDate = events.get( 0 ).getEventDate( );
				
				Criteria criteria2 = session.createCriteria( Event.class );

				if( next )
				{
					criteria2.setProjection( Projections.min("eventDate" ) );
					criteria2.add( Restrictions.gt( "eventDate", eventDate ) );
					//				queryString = "FROM event as e WHERE e.eventId" = (select min(e.eventId) from e where e.eventId > " 
					//						+ eventId + ")";
				}
				else
				{
					criteria2.setProjection( Projections.max("eventDate" ) );
					criteria2.add( Restrictions.lt( "eventDate", eventDate ) );
					//				queryString = "FROM event as e WHERE e.eventId = (select max(e.eventId) from e where e.eventId < " 
					//		              + eventId + ")";
				}

                
				List<Timestamp> timestamps = (List<Timestamp>)criteria2.list( );
				
				System.out.println( "Num timestamps = " + timestamps.size( ) );
				
				if( timestamps.size( ) > 0 )
				{
					
					Criteria criteria3 = session.createCriteria( Event.class );

					criteria3.add( Restrictions.eq("eventDate", timestamps.get( 0 ) ) );

					List<Event>events2 = (List<Event>)criteria3.list( );
                    

					for( Event event : events2 )
					{
						System.out.println( "Event:  " + event );
//						ClientEvent clientEvent = new ClientEvent( event );
//						
						ClientEvent clientEvent = DAODTOHelper.createClientEventWithCommentsAndInvitationStatuses( event, userId );
//
//						clientEvent.addEventUserStatuses( event.getEventUserStatuses( ), userId);
//						clientEvent.addEventComments( event.getEventComments( ) );

						clientEvents.add( clientEvent );
					}
				}
			}
		}
		//catch( HibernateException e )
		catch( Throwable e )
		{
			if( transaction != null )
			{
				transaction.rollback( );
			}
			
			System.err.println( "EventDAOImpl.getUserEvents - Query failed.  Rolling back.  " );
			e.printStackTrace( );
		}
		finally
		{
			session.close( );
		}
		
		return clientEvents;
	}
	
	@Override
	@SuppressWarnings( "unchecked" )
	public EventChange getEventChange( int eventId )
	{
		System.out.println( "IN EventDAOImple.getEventDateLocation:  eventId = " + eventId );
		
		EventChange eventChange = new EventChange( );
		
		Session session = MyHibernateUtil.getSessionFactory( ).openSession( );
		Transaction transaction = null;
		
		try
		{
			transaction = session.beginTransaction( );
			
			Criteria criteria = session.createCriteria( Event.class );
			
			
			criteria.add( Restrictions.eq("eventId", eventId ) );
			
			List<Event> events = (List<Event>)criteria.list( );
			
			if( events.size( ) > 0 )
			{
				Event event = events.get( 0 );
				eventChange.setDate( event.getEventDate( ) );
				eventChange.setLocation( event.getLocation( ) );
				Set<InvitationStatus> invitationStatuses = new HashSet<InvitationStatus>( );
				eventChange.setInvitationStatuses( invitationStatuses );
				
				Set<EventUserStatus> eventUserStatuses = event.getEventUserStatuses( );
				
				for( EventUserStatus eventUserStatus : eventUserStatuses )
				{
					InvitationStatus invitationStatus = new InvitationStatus( eventUserStatus.getUser( ).getUserId( ), 
																			  eventUserStatus.getUserEventStatus( ), 
																			  eventUserStatus.getEventStatusId( ) );
					invitationStatuses.add( invitationStatus );
				}
			}
		}
		//catch( HibernateException e )
		catch( Throwable e )
		{
			if( transaction != null )
			{
				transaction.rollback( );
			}
			
			System.err.println( "EventDAOImpl.getEventDateLocation - Query failed.  Rolling back.  " );
			e.printStackTrace( );
		}
		finally
		{
			session.close( );
		}
		
		return eventChange;
	}
	
	@Override
	@SuppressWarnings( "unchecked" )
	public boolean updateEvent( EventUpdate eventUpdate )
	{
		System.out.println( "IN EventDAO:updateEvent" );
		logger.info( "IN updateEvent" );
		
		boolean success = false;
		
		Session session = MyHibernateUtil.getSessionFactory( ).openSession( );
		Transaction transaction = null;
		
		try
		{
			transaction = session.beginTransaction( );
			
			Criteria criteria = session.createCriteria( Event.class );
			
			criteria.add( Restrictions.eq( "eventId", eventUpdate.getEventId( ) ) );
			
			List<Event> events = (List<Event>)criteria.list( );
			
			if( events.size( ) > 0 )
			{
				Event event = events.get( 0 );
				
				event = DAODTOHelper.updateEventEntity( event, eventUpdate );
//				Set<EventUserStatus>eventUserStatuses = event.getEventUserStatuses( );
//				eventUserStatuses.clear( );
//				eventUserStatuses.addAll( updatedEvent.getEventUserStatuses( ) );
				
				session.update( event );

				transaction.commit( );
				success = true;
				
				logger.info( "updateEvent succeeded." );
				System.out.println( "EventDAOImpl.upateEvent succeeded." );
			}
		}
		catch( Throwable e )
		//catch( HibernateException e )
		{
			if( transaction != null )
			{
				transaction.rollback( );
			}
			
			logger.error( "updateEvent transaction failed.", e );
			System.err.println( "EventDAOImpl.updatgeEvent transaction failed." );
			e.printStackTrace( );
		}
		finally
		{
			session.close( );
		}
		
		return success;
	}

	@Override
	@SuppressWarnings( "unchecked" )
	public List<ClientEvent> getEventInvitationStatuses( int eventId, String userId )
	{
		logger.info( "IN getClientEventInvitationList( ) for event {}.  ", eventId );
		
		Session session = MyHibernateUtil.getSessionFactory( ).openSession( );
		Transaction transaction = null;
		
		List<ClientEvent> clientEvents = new ArrayList<ClientEvent>( );
		
		try
		{
			transaction = session.beginTransaction( );

			Criteria criteria = session.createCriteria( Event.class );

			criteria.add( Restrictions.eq("eventId", eventId ) );

			List<Event>events = (List<Event>)criteria.list( );

			if( events.size( ) > 0 )
			{
				Set<EventUserStatus> eventUserStatuses = events.get( 0 ).getEventUserStatuses( );
				
				ClientEvent clientEvent = 
						DAODTOHelper.getEventInvitationStatuses( eventUserStatuses, eventId, userId );
				
				clientEvents.add( clientEvent );
			}
			
			transaction.commit( );
		}
		catch( Exception e )
		{
			if( transaction != null )
			{
				transaction.rollback( );
			}

			logger.error( "getEventInvitationStatuses failed for event = {} and user {}.  Rolling back. ", eventId,  userId );
			e.printStackTrace( );
		}
		finally
		{
			session.close( );
		}
		
		return clientEvents;
	}
}
