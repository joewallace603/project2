package com.jpw.interfaces;

import com.jpw.client.*;
import com.jpw.dto.*;

import java.util.*;
import java.sql.*;

public interface EventManager 
{
	public boolean createEvent( ClientEventForm clientEventForm );
	
	
	public List<ClientEvent> getUserEventsOverTimePeriod( String userId, Timestamp startTime, Timestamp stopTime );
	
	public List<ClientEvent> getEventById( String eventId, String userId );
	
	public List<ClientEvent> getPreviousEventById( String eventId, String userId );
	
	public List<ClientEvent> getNextEventById( String eventId, String userId );
	
	public boolean handleResposeToInvitation( String userId, String eventId, String response );
	
	//public List<ClientEventInvitationList> getEventUserStatusesByEventId( String userId, String eventId );
	
	public boolean addCommentToEvent( ClientEventComment clientEventComment );
	
	public List<ClientEventComment> getEventComments( String eventId );
	
	public boolean updateEvent( ClientEventForm clientEventForm );
	
	public List<ClientEvent> getEventInvitationStatuses( String eventId, String userId );
}
