package com.jpw.dao;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jpw.app.UserInvitationStatus;
import com.jpw.client.*;
import com.jpw.dto.Event;
import com.jpw.dto.EventComment;
import com.jpw.dto.EventUpdate;
import com.jpw.dto.EventUserStatus;
import com.jpw.dto.User;

public class DAODTOHelper
{
	private static final Logger logger = LoggerFactory.getLogger( DAODTOHelper.class );
	
	public static Event createNewEventFromClientEventForm( ClientEventForm clientEventForm )
	{
		Event event = new Event( );
		
		event.setTitle( clientEventForm.getTitle( ) );
		
		event.setDescription( clientEventForm.getDescription( ) );
		
		event.setEventCreateDate( clientEventForm.getEventCreateDate( ) );
		
		event.setEventDate( clientEventForm.getEventDate( ) );
		
		event.setEventDuration( clientEventForm.getEventDuration( ) );
		
		event.setLocation( clientEventForm.getLocation( ) );
		
		event.setEventImageName( clientEventForm.getEventImageName( ) );
		
		event.setReplyDueDate( clientEventForm.getReplyDueDate( ) );
		
		event.setCreatorId( clientEventForm.getCreatorId( ) );
		
		
		Set<String> invitedContactIds = clientEventForm.getInvitedContacts( );
		Set<EventUserStatus> eventUserStatuses = new HashSet<EventUserStatus>( );
		
		for( String invitedContactId : invitedContactIds )
		{
			System.out.println( "invited friend = " + invitedContactId );
			EventUserStatus eventUserStatus = new EventUserStatus( );
			
			eventUserStatus.setEvent( event );
			eventUserStatus.setUserEventStatus( UserInvitationStatus.NO_RESPONSE.toString( ) );
 			
			eventUserStatus.setUser( new User( invitedContactId));
			System.out.println( "eventUserStatus = " + eventUserStatus );
			
			eventUserStatuses.add( eventUserStatus );
		}
		
		event.setEventUserStatuses( eventUserStatuses );
		
		return event;
	}
	
	public static Event updateEventEntity( Event event, EventUpdate eventUpdate )
	{
		logger.info( "IN updateEventEntity: eventUpdate = {}", eventUpdate );
		event.setEventId( eventUpdate.getEventId( ) );
		
		event.setTitle( eventUpdate.getTitle( ) );
		
		event.setDescription( eventUpdate.getDescription( ) );
		
		event.setEventCreateDate( eventUpdate.getEventCreateDate( ) );
		
		event.setEventDate( eventUpdate.getEventDate( ) );
		
		event.setEventDuration( eventUpdate.getEventDuration( ) );
		
		event.setLocation( eventUpdate.getLocation( ) );
		
		event.setEventImageName( eventUpdate.getEventImageName( ) );
		
		event.setReplyDueDate( eventUpdate.getReplyDueDate( ) );
		
		event.setCreatorId( eventUpdate.getCreatorId( ) );
		
		Set<EventUserStatus> eventUserStatuses = event.getEventUserStatuses( );
		
		for( Iterator<EventUserStatus> it = eventUserStatuses.iterator( ); it.hasNext( ); )
		{
			EventUserStatus eventUserStatus = it.next( );
			
			String userId = eventUserStatus.getUser( ).getUserId( );
			
			if( eventUpdate.getUsersToNotifyDateTimeLocationChange( ).contains( userId ) )
			{
				if( eventUpdate.isDateTimeChange( ) && eventUpdate.isLocationChange( ) )
				{
					eventUserStatus.setUserEventStatus( UserInvitationStatus.DATE_TIME_LOCATION_CHANGE.toString( ) );
					logger.info( "updateEventEntity:  user {} event status is {}.",
							UserInvitationStatus.DATE_TIME_LOCATION_CHANGE.toString( ) );
				}
				else if( eventUpdate.isDateTimeChange( ) )
				{
					eventUserStatus.setUserEventStatus( UserInvitationStatus.DATE_TIME_CHANGE.toString( ) );
					logger.info( "updateEventEntity:  user {} event status is {}.",
							UserInvitationStatus.DATE_TIME_CHANGE.toString( ) );
				}
				else if( eventUpdate.isLocationChange( ) )
				{
					eventUserStatus.setUserEventStatus( UserInvitationStatus.LOCATION_CHANGE.toString( ) );
					logger.info( "updateEventEntity:  user {} event status is {}.",
							UserInvitationStatus.LOCATION_CHANGE.toString( ) );
				}
			}
			else if( eventUpdate.getUsersToUninvite( ).contains( userId ) )
			{
				logger.info( "updateEventEntity:  uninviting {}", userId );
				it.remove( );
			}
		}
		
		Set<String> usersToInvite = eventUpdate.getUsersToInvite( );
		
		for( String userToInvite : usersToInvite)
		{
			logger.info( "updateEventEntity:  inviting {}", userToInvite );
			EventUserStatus eventUserStatus = new EventUserStatus( );
			eventUserStatus.setEvent( event );
			eventUserStatus.setUser( new User( userToInvite ) );
			eventUserStatus.setUserEventStatus( UserInvitationStatus.NO_RESPONSE.toString( ) );
			
			eventUserStatuses.add( eventUserStatus );
		}
		
		return event;
	}
	
	
	public static ClientEvent createClientEventWithCommentsAndInvitationStatuses( Event event, String userId )
	{
		ClientEvent clientEvent = new ClientEvent( event );
		
		Set<ClientContact> going = new HashSet<ClientContact>( );
		
		Set<ClientContact> tentative = new HashSet<ClientContact>( );
		
		Set<ClientContact> declined = new HashSet<ClientContact>( );
		
		Set<ClientContact> hasNotResponded = new HashSet<ClientContact>( );
		
		ClientEventInvitationList clientEventInvitationList = new ClientEventInvitationList( event.getEventId( ), going, tentative, declined, hasNotResponded );
		
		clientEvent.setClientEventInvitationList( clientEventInvitationList );
		
		Set<EventUserStatus> eventUserStatuses = event.getEventUserStatuses( );
		
		for( EventUserStatus eventUserStatus : eventUserStatuses )
		{
			if( userId.equals( eventUserStatus.getUser( ).getUserId( ) ) )
			{
				clientEvent.setUserEventStatus( eventUserStatus.getUserEventStatus( ) );
			}
			ClientContact clientContact = new ClientContact( eventUserStatus.getUser( ) );
			
			UserInvitationStatus userInvitationStatus = UserInvitationStatus.valueOf( eventUserStatus.getUserEventStatus( ) );
			
			switch( userInvitationStatus )
			{
				case NO_RESPONSE:
					hasNotResponded.add( clientContact );
					break;
				case DECLINED:
					declined.add( clientContact );
					break;
				case TENTATIVE:
					tentative.add( clientContact );
					break;
				case ACCEPTED:
					going.add( clientContact );
					break;
				case DATE_TIME_LOCATION_CHANGE:
				case DATE_TIME_CHANGE:
				case LOCATION_CHANGE:
					hasNotResponded.add( clientContact );
					break;
				default:
					hasNotResponded.add( clientContact );
			}

		}
		
		List<EventComment> eventComments = event.getEventComments( );
	
		List<ClientEventComment> clientEventComments = new ArrayList<ClientEventComment>( );
		
		for( EventComment eventComment : eventComments )
		{
			
			ClientEventComment clientEventComment = new ClientEventComment( eventComment );
			clientEventComments.add( clientEventComment );
		}
		
		clientEvent.setClientEventComments( clientEventComments );
		
		return clientEvent;
	}
	
	public static ClientEvent getEventInvitationStatuses(
			Set<EventUserStatus> eventUserStatuses, int eventId, String userId )
	{
		Set<ClientContact> going = new HashSet<ClientContact>( );
		
		Set<ClientContact> tentative = new HashSet<ClientContact>( );
		
		Set<ClientContact> declined = new HashSet<ClientContact>( );
		
		Set<ClientContact> hasNotResponded = new HashSet<ClientContact>( );
		
		ClientEventInvitationList clientEventInvitationList = 
				new ClientEventInvitationList( eventId, going, tentative, declined, hasNotResponded );
		
		ClientEvent clientEvent = new ClientEvent( );
		
		clientEvent.setClientEventInvitationList( clientEventInvitationList );
		
		for( EventUserStatus eventUserStatus : eventUserStatuses )
		{
			if( userId.equals( eventUserStatus.getUser( ).getUserId( ) ) )
			{
				clientEvent.setUserEventStatus( eventUserStatus.getUserEventStatus( ) );
			}
			
			ClientContact clientContact = new ClientContact( eventUserStatus.getUser( ) );
			
			UserInvitationStatus userInvitationStatus = UserInvitationStatus.valueOf( eventUserStatus.getUserEventStatus( ) );
			
			switch( userInvitationStatus )
			{
				case NO_RESPONSE:
					hasNotResponded.add( clientContact );
					break;
				case DECLINED:
					declined.add( clientContact );
					break;
				case TENTATIVE:
					tentative.add( clientContact );
					break;
				case ACCEPTED:
					going.add( clientContact );
					break;
				case DATE_TIME_LOCATION_CHANGE:
				case DATE_TIME_CHANGE:
				case LOCATION_CHANGE:
					hasNotResponded.add( clientContact );
					break;
				default:
					hasNotResponded.add( clientContact );
			}

		}
		
		return clientEvent;
	}
}
