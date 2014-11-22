package com.jpw.interfaces;

import com.jpw.client.*;
import com.jpw.dto.*;

import java.util.*;
import java.sql.*;

public interface EventDAO 
{
	public boolean createEvent( Event event );
	
	public List<ClientEvent> getUserEventsOverTimePeriod( String userId, Timestamp startTime, Timestamp stopTime );
	
	public List<ClientEvent> getEventById( String userId, int eventId );
	
	public List<ClientEvent> getPreviousEventById( String userId, int eventId );
	
	public List<ClientEvent> getNextEventById( String userId, int eventId );
	
	public boolean handleResponseToInvitation( String userId, int eventId, String response );
	
	//public List<ClientEventInvitationList> getEventUserStatusesByEventId( String userId, int eventId );
	
	public boolean addCommentToEvent( EventComment eventComment, int eventId );
	
	public List<ClientEventComment> getEventComments( int eventId );
	
	public EventChange getEventChange( int eventId );
	
	public boolean updateEvent( EventUpdate eventUpdate );
	
	public List<ClientEvent> getEventInvitationStatuses( int eventId, String userId );
}
