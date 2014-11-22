package com.jpw.manager;

import com.jpw.client.*;
import com.jpw.dao.*;
import com.jpw.dto.*;
import com.jpw.interfaces.*;
import com.jpw.app.*;

import java.sql.*;
import java.util.*;

public class EventManagerImpl implements EventManager
{

	@Override
	public boolean createEvent( ClientEventForm clientEventForm )
	{
		EventDAO eventDAO = DAOFactory.getEventDAO( );
		
		Event event = DAODTOHelper.createNewEventFromClientEventForm( clientEventForm );
		
		System.out.println( "EventManager.createEvent:  " + event );

		boolean success = eventDAO.createEvent( event );

		return success;
	}

	@Override
	public List<ClientEvent> getUserEventsOverTimePeriod( String userId, Timestamp startTime, Timestamp stopTime )
	{
		EventDAO eventDAO = DAOFactory.getEventDAO( );
		
		List<ClientEvent> clientEvents = eventDAO.getUserEventsOverTimePeriod( userId, startTime, stopTime );
		
		return clientEvents;
	}
	
	@Override
	public List<ClientEvent> getEventById( String eventIdString, String userId )
	{
		EventDAO eventDAO = DAOFactory.getEventDAO( );
		
		int eventId = Integer.parseInt( eventIdString );
		
		List<ClientEvent> clientEvents = eventDAO.getEventById( userId, eventId );
		
		return clientEvents;
	}
	
	public List<ClientEvent> getPreviousEventById( String eventIdString, String userId )
	{
		EventDAO eventDAO = DAOFactory.getEventDAO( );
		
		int eventId = Integer.parseInt( eventIdString );
		
		List<ClientEvent> clientEvents = eventDAO.getPreviousEventById( userId, eventId );
		
		return clientEvents;
	}
	
	public List<ClientEvent> getNextEventById( String eventIdString, String userId )
	{
		EventDAO eventDAO = DAOFactory.getEventDAO( );
		
		int eventId = Integer.parseInt( eventIdString );
		
		List<ClientEvent> clientEvents = eventDAO.getNextEventById( userId, eventId );
		
		return clientEvents;
	}
	
	@Override
	public boolean handleResposeToInvitation( String userId, String eventIdString, String invitationResponse )
	{
		EventDAO eventDAO = DAOFactory.getEventDAO( );
		
		boolean success = false;
		
		try
		{
			int eventId = Integer.parseInt( eventIdString );
			UserInvitationStatus statusEnum = UserInvitationStatus.valueOf( invitationResponse );
			success = eventDAO.handleResponseToInvitation( userId, eventId, statusEnum.toString( ) );
		}
		catch( IllegalArgumentException e )
		{
			System.out.println( "EventManager.handleResponseToInvitation:  Client provided invalid invitation response = " + invitationResponse );
			e.printStackTrace( );
		}
		
		return success;
	}
/*	
	@Override
	public List<ClientEventInvitationList> getEventUserStatusesByEventId( String userId, String eventIdString )
	{
		int eventId = Integer.parseInt( eventIdString );
		
		EventDAO eventDAO = DAOFactory.getEventDAO( );

		List<ClientEventInvitationList> clientEventInvitationLists = eventDAO.getEventUserStatusesByEventId( userId, eventId );

		return clientEventInvitationLists;
	}
	*/
	
	public boolean addCommentToEvent( ClientEventComment clientEventComment )
	{
		EventDAO eventDAO = DAOFactory.getEventDAO( );
		
		EventComment eventComment = new EventComment( clientEventComment );
		
		boolean success = eventDAO.addCommentToEvent( eventComment, clientEventComment.getEventId( ) );
		
		return success;
	}
	
	public List<ClientEventComment> getEventComments( String eventIdString )
	{
		int eventId = Integer.parseInt( eventIdString );
		
		EventDAO eventDAO = DAOFactory.getEventDAO( );
		
		List<ClientEventComment> clientEventComments = eventDAO.getEventComments( eventId );

		return clientEventComments;
	}

	public boolean updateEvent( ClientEventForm clientEventForm )
	{
		boolean success = false;
		
		int eventId = clientEventForm.getEventId( );
		
		if( eventId > 0 )
		{
			System.out.println( "event id = " + eventId );
			
			EventDAO eventDAO = DAOFactory.getEventDAO( );
			
			EventUpdate eventUpdate = new EventUpdate( clientEventForm );
			
			EventChange eventChange = eventDAO.getEventChange( eventId );
			
			if( !clientEventForm.getEventDate( ).equals( eventChange.getDate( ) ) )
			{
				eventUpdate.setDateTimeChange( true );
			}
			
			if( !clientEventForm.getLocation( ).matches( eventChange.getLocation( ) ) )
			{
				eventUpdate.setLocationChange( true );
			}
			
			Set<String> newInvitedContacts = clientEventForm.getInvitedContacts( );
			
			Set<InvitationStatus> invitationStatuses = eventChange.getInvitationStatuses( );
			
			for( Iterator<InvitationStatus> it = invitationStatuses.iterator( ); it.hasNext( ); )
			{
				InvitationStatus invitationStatus = it.next( );
				String userId = invitationStatus.getUserId( );
//				int eventUserStatusId = invitationStatus.getInvitationStatusId( );
				
				if( newInvitedContacts.contains( userId ) )
				{
					newInvitedContacts.remove( userId );
					
					if( eventUpdate.isDateTimeChange( ) || eventUpdate.isLocationChange( ) )
					{
						System.out.println( "notify " + userId + " of critical change." );
						// existing invitee needs notification of event date, time or location change
						eventUpdate.getUsersToNotifyDateTimeLocationChange( ).add( userId );
					}
					else
					{
						System.out.println( "keep " + userId + " as is." );
						// existing invitee remains invited no change.
					}
					
				}
				else
				{
					System.out.println( userId + " is no longer invited." );
					eventUpdate.getUsersToUninvite( ).add( userId );
				}
			}
			
			for( Iterator<String> it = newInvitedContacts.iterator( ); it.hasNext( ); )
			{
				// Invitation has now been extended to this user.
				String userId = it.next( );
				
				System.out.println( " a new invitation has been extended to " + userId );
				
				eventUpdate.getUsersToInvite( ).add( userId );
			}
			
			System.out.println( "EventManager:  updateEvent with: " + eventUpdate );
			
			success = eventDAO.updateEvent( eventUpdate );
		}
		else
		{
			System.out.println( "invalid event id" );
			ResourceBundle errorMessages = ResourceBundle.getBundle( "com.jpw.manager.ErrorMessages" );
			throw( new IllegalArgumentException( errorMessages.getString( "INVALID_EVENT_ID" ) ) );
		}
		
		return success;
	}

	@Override
	public List<ClientEvent> getEventInvitationStatuses( String eventIdString, String userId )
	{
		int eventId = Integer.parseInt( eventIdString );
		
		EventDAO eventDAO = DAOFactory.getEventDAO( );
		
		List<ClientEvent> clientEvents = eventDAO.getEventInvitationStatuses( eventId, userId );

		return clientEvents;
	}
}
